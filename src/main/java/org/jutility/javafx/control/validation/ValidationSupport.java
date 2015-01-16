package org.jutility.javafx.control.validation;

/*
 * #%L
 * jutility-javafx
 * %%
 * Copyright (C) 2013 - 2015 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


/**
 * Copyright (c) 2014, ControlsFX All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of ControlsFX, any associated
 * website, nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.collections.WeakListChangeListener;
import javafx.collections.WeakMapChangeListener;
import javafx.collections.WeakSetChangeListener;
import javafx.scene.control.Control;
import javafx.util.Callback;

import org.controlsfx.tools.ValueExtractor;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.ValidationDecoration;


/**
 * Provides validation support for UI components. The idea is create an instance
 * of this class the component group, usually a panel.<br>
 * Once created, {@link Validator}s can be registered for components, to provide
 * the validation:
 * 
 * <pre>
 * ValidationSupport validationSupport = new ValidationSupport();
 * validationSupport.registerValidator(textField,
 *         Validator.createEmptyValidator(&quot;Text is required&quot;));
 * validationSupport.registerValidator(combobox,
 *         Validator.createEmptyValidator(&quot;ComboBox Selection required&quot;));
 * validationSupport.registerValidator(checkBox,
 *         (Control c, Boolean newValue) -&gt; ValidationResult.fromErrorIf(c,
 *                 &quot;Checkbox should be checked&quot;, !newValue));
 * </pre>
 * 
 * validationResultProperty provides an ability to react on overall validation
 * result changes:
 * 
 * <pre>
 * validationSupport.validationResultProperty().addListener(
 *         (o, oldValue, newValue) -&gt; messageList.getItems().setAll(
 *                 newValue.getMessages()));
 * </pre>
 * 
 * Standard JavaFX UI controls are supported out of the box. There is also an
 * ability to add support for custom controls. To do that
 * "observable value extractor" should be added for specific controls. Such
 * "extractor" consists of two functional interfaces: a {@link Predicate} to
 * check the applicability of the control and a {@link Callback} to extract
 * control's observable value. Here is an sample of internal registration of
 * such "extractor" for a few controls :
 * 
 * <pre>
 * ValueExtractor.addObservableValueExtractor(c -&gt; c instanceof TextInputControl,
 *         c -&gt; ((TextInputControl) c).textProperty());
 * ValueExtractor.addObservableValueExtractor(c -&gt; c instanceof ComboBox,
 *         c -&gt; ((ComboBox&lt;?&gt;) c).valueProperty());
 * </pre>
 * 
 */
public class ValidationSupport {


    private static final String CTRL_REQUIRED_FLAG = "$org.controlsfx.validation.required$"; //$NON-NLS-1$

    /**
     * Set control's required flag
     * 
     * @param c
     *            control
     * @param required
     *            flag
     */
    public static void setRequired(Control c, boolean required) {

        c.getProperties().put(CTRL_REQUIRED_FLAG, required);
    }

    /**
     * Check control's required flag
     * 
     * @param c
     *            control
     * @return true if required
     */
    public static boolean isRequired(Control c) {

        Object value = c.getProperties().get(CTRL_REQUIRED_FLAG);
        return value instanceof Boolean ? (Boolean) value : false;
    }

    private ObservableSet<Control>                   controls          = FXCollections
                                                                               .observableSet();
    private ObservableMap<Control, ValidationResult> validationResults = FXCollections
                                                                               .observableMap(new WeakHashMap<>());


    private AtomicBoolean                            dataChanged       = new AtomicBoolean(
                                                                               false);

    /**
     * Creates validation support instance
     */
    public ValidationSupport() {

        validationResultProperty().addListener(
                (o, oldValue, validationResult) -> {
                    invalidProperty
                            .set(!validationResult.getErrors().isEmpty());
                    redecorate();
                });

        // notify validation result observers
        validationResults
                .addListener((
                        MapChangeListener.Change<? extends Control, ? extends ValidationResult> change) -> validationResultProperty
                        .set(ValidationResult.fromResults(validationResults
                                .values())));


    }

    /**
     * Redecorates all known components Only decorations related to validation
     * are affected
     */
    // TODO needs optimization
    public void redecorate() {

        Optional<ValidationDecoration> odecorator = Optional
                .ofNullable(getValidationDecorator());
        for (Control target : getRegisteredControls()) {
            odecorator.ifPresent(decorator -> {
                decorator.removeDecorations(target);
                decorator.applyRequiredDecoration(target);
                if (dataChanged.get() && isErrorDecorationEnabled()) {
                    getHighestMessage(target).ifPresent(
                            msg -> decorator.applyValidationDecoration(msg));
                }
            });
        }
    }

    /**
     * Triggers revalidation of all components.
     */
    public void revalidate() {

        this.dataChanged.set(true);
        this.redecorate();
    }

    private BooleanProperty errorDecorationEnabledProperty = new SimpleBooleanProperty(
                                                                   true) {

                                                               protected void invalidated() {

                                                                   redecorate();
                                                               };
                                                           };

    public BooleanProperty errorDecorationEnabledProperty() {

        return errorDecorationEnabledProperty;
    }

    public void setErrorDecorationEnabled(boolean enabled) {

        errorDecorationEnabledProperty.set(enabled);
    }

    private boolean isErrorDecorationEnabled() {

        return errorDecorationEnabledProperty.get();
    }



    private ReadOnlyObjectWrapper<ValidationResult> validationResultProperty = new ReadOnlyObjectWrapper<>();


    /**
     * Retrieves current validation result
     * 
     * @return validation result
     */
    public ValidationResult getValidationResult() {

        return validationResultProperty.get();
    }

    /**
     * Can be used to track validation result changes
     * 
     * @return The Validation result property.
     */
    public ReadOnlyObjectProperty<ValidationResult> validationResultProperty() {

        return validationResultProperty.getReadOnlyProperty();
    }

    private BooleanProperty invalidProperty = new SimpleBooleanProperty();

    /**
     * Returns current validation state.
     * 
     * @return true if there is at least one error
     */
    public Boolean isInvalid() {

        return invalidProperty.get();
    }

    /**
     * Validation state property
     * 
     * @return validation state property
     */
    public ReadOnlyBooleanProperty invalidProperty() {

        return invalidProperty;
    }


    private ObjectProperty<ValidationDecoration> validationDecoratorProperty = new SimpleObjectProperty<ValidationDecoration>(
                                                                                     this,
                                                                                     "validationDecorator", new GraphicValidationDecoration()) { //$NON-NLS-1$

                                                                                 @Override
                                                                                 protected void invalidated() {

                                                                                     // when
                                                                                     // the
                                                                                     // decorator
                                                                                     // changes,
                                                                                     // rerun
                                                                                     // the
                                                                                     // decoration
                                                                                     // to
                                                                                     // update
                                                                                     // the
                                                                                     // visuals
                                                                                     // immediately.
                                                                                     redecorate();
                                                                                 }
                                                                             };

    /**
     * @return The Validation decorator property
     */
    public ObjectProperty<ValidationDecoration> validationDecoratorProperty() {

        return validationDecoratorProperty;
    }

    /**
     * Returns current validation decorator
     * 
     * @return current validation decorator or null if none
     */
    public ValidationDecoration getValidationDecorator() {

        return validationDecoratorProperty.get();
    }

    /**
     * Sets new validation decorator
     * 
     * @param decorator
     *            new validation decorator. Null value is valid - no decoration
     *            will occur
     */
    public void setValidationDecorator(ValidationDecoration decorator) {

        validationDecoratorProperty.set(decorator);
    }


    /**
     * Registers {@link Validator} for specified control with additional
     * possiblity to mark control as required or not.
     * 
     * @param c
     *            control to validate
     * @param required
     *            true if controls should be required
     * @param validator
     *            {@link Validator} to be used
     * @return true if registration is successful
     */
    @SuppressWarnings("unchecked")
    public <T> boolean registerValidator(final Control c, boolean required,
            final Validator<T> validator) {

        Optional.ofNullable(c).ifPresent(
                ctrl -> {
                    ctrl.getProperties().addListener(
                            new MapChangeListener<Object, Object>() {

                                @Override
                                public void onChanged(
                                        javafx.collections.MapChangeListener.Change<? extends Object, ? extends Object> change) {

                                    if (CTRL_REQUIRED_FLAG.equals(change
                                            .getKey())) {
                                        redecorate();
                                    }
                                }

                            });
                });

        setRequired(c, required);

        return ValueExtractor
                .getObservableValueExtractor(c)
                .map(e -> {

                    ObservableValue<T> observable = (ObservableValue<T>) e
                            .call(c);

                    Consumer<T> updateResults = value -> {
                        Platform.runLater(() -> validationResults.put(c,
                                validator.apply(c, value)));
                    };

                    controls.add(c);

                    observable.addListener((o) -> {
                        dataChanged.set(true);
                        updateResults.accept(observable.getValue());

                        setupCollectionChangeHandler(observable.getValue(),
                                updateResults);
                    });

                    this.setupCollectionChangeHandler(observable.getValue(),
                            updateResults);


                    updateResults.accept(observable.getValue());

                    return e;

                }).isPresent();
    }

    private <T> void setupCollectionChangeHandler(T newValue,
            Consumer<T> updateResults) {

        if (newValue != null) {

            if (newValue instanceof ObservableList<?>) {

                ((ObservableList<?>) newValue)
                        .addListener(new WeakListChangeListener<>(
                                (ListChangeListener.Change<? extends Object> change) -> {

                                    dataChanged.set(true);
                                    updateResults.accept(newValue);
                                }));
            }
            else if (newValue instanceof ObservableSet<?>) {

                ((ObservableSet<?>) newValue)
                        .addListener(new WeakSetChangeListener<>(
                                (SetChangeListener.Change<? extends Object> change) -> {

                                    dataChanged.set(true);
                                    updateResults.accept(newValue);
                                }));
            }
            else if (newValue instanceof ObservableMap<?, ?>) {

                ((ObservableMap<?, ?>) newValue)
                        .addListener(new WeakMapChangeListener<>(
                                (MapChangeListener.Change<? extends Object, ? extends Object> change) -> {

                                    dataChanged.set(true);
                                    updateResults.accept(newValue);
                                }));
            }
        }
    }

    /**
     * Registers {@link Validator} for specified control and makes control
     * required
     * 
     * @param c
     *            control to validate
     * @param validator
     *            {@link Validator} to be used
     * @return true if registration is successful
     */
    public <T> boolean registerValidator(final Control c,
            final Validator<T> validator) {

        return registerValidator(c, true, validator);
    }

    /**
     * Returns currently registered controls
     * 
     * @return set of currently registered controls
     */
    public Set<Control> getRegisteredControls() {

        return Collections.unmodifiableSet(controls);
    }

    /**
     * Returns optional highest severity message for a control
     * 
     * @param target
     *            control
     * @return Optional highest severity message for a control
     */
    public Optional<ValidationMessage> getHighestMessage(Control target) {

        return Optional.ofNullable(validationResults.get(target)).flatMap(
                result -> result.getMessages().stream()
                        .max(ValidationMessage.COMPARATOR));
    }
}
