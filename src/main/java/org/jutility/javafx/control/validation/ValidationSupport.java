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


import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.beans.Observable;
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
 * <p>
 * <pre>
 * ValidationSupport validationSupport = new ValidationSupport();
 * validationSupport.registerValidator(textField,
 *         Validator.createEmptyValidator(&quot;Text is required&quot;));
 * validationSupport.registerValidator(combobox,
 *         Validator.createEmptyValidator(&quot;ComboBox Selection
 *         required&quot;));
 * validationSupport.registerValidator(checkBox,
 *         (Control c, Boolean newValue) -&gt; ValidationResult.fromErrorIf(c,
 *                 &quot;Checkbox should be checked&quot;, !newValue));
 * </pre>
 * <p>
 * validationResultProperty provides an ability to react on overall validation
 * result changes:
 * <p>
 * <pre>
 * validationSupport.validationResultProperty().addListener(
 *         (o, oldValue, newValue) -&gt; messageList.getItems().setAll(
 *                 newValue.getMessages()));
 * </pre>
 * <p>
 * Standard JavaFX UI controls are supported out of the box. There is also an
 * ability to add support for custom controls. To do that
 * "observable value extractor" should be added for specific controls. Such
 * "extractor" consists of two functional interfaces: a {@link Predicate} to
 * check the applicability of the control and a {@link Callback} to extract
 * control's observable value. Here is an sample of internal registration of
 * such "extractor" for a few controls :
 * <p>
 * <pre>
 * ValueExtractor.addObservableValueExtractor(c -&gt; c instanceof
 * TextInputControl,
 *         c -&gt; ((TextInputControl) c).textProperty());
 * ValueExtractor.addObservableValueExtractor(c -&gt; c instanceof ComboBox,
 *         c -&gt; ((ComboBox&lt;?&gt;) c).valueProperty());
 * </pre>
 */
public class ValidationSupport {


    private static final String CTRL_REQUIRED_FLAG =
            "$org.controlsfx" + ".validation" + ".required$";
    //$NON-NLS-1$

    /**
     * Set control's required flag
     *
     * @param c
     *         control
     * @param required
     *         flag
     */
    public static void setRequired(final Control c, final boolean required) {

        c.getProperties()
                .put(ValidationSupport.CTRL_REQUIRED_FLAG, required);
    }

    /**
     * Check control's required flag
     *
     * @param c
     *         control
     *
     * @return true if required
     */
    public static boolean isRequired(final Control c) {

        final Object value = c.getProperties()
                .get(ValidationSupport.CTRL_REQUIRED_FLAG);
        return value instanceof Boolean ? (Boolean) value : false;
    }

    private final ObservableSet<Control>                   controls
            = FXCollections.observableSet();
    private final ObservableMap<Control, ValidationResult> validationResults
            = FXCollections.observableMap(
            new WeakHashMap<>());


    private final AtomicBoolean dataChanged = new AtomicBoolean(false);

    /**
     * Creates validation support instance
     */
    public ValidationSupport() {

        this.validationResultProperty()
                .addListener((o, oldValue, validationResult) -> {
                    this.invalidProperty.set(!validationResult.getErrors()
                            .isEmpty());
                    this.redecorate();
                });

        // notify validation result observers
        this.validationResults.addListener(
                (final MapChangeListener.Change<? extends Control, ? extends
                        ValidationResult> change) -> this
                        .validationResultProperty.set(
                        ValidationResult.fromResults(
                                this.validationResults.values())));


    }

    /**
     * Redecorates all known components Only decorations related to validation
     * are affected
     */
    // TODO needs optimization
    public void redecorate() {

        final Optional<ValidationDecoration> odecorator = Optional.ofNullable(
                this.getValidationDecorator());
        for (final Control target : this.getRegisteredControls()) {
            odecorator.ifPresent(decorator -> {
                decorator.removeDecorations(target);
                decorator.applyRequiredDecoration(target);
                if (this.dataChanged.get() && this.isErrorDecorationEnabled()) {
                    this.getHighestMessage(target)
                            .ifPresent(decorator::applyValidationDecoration);
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

    private final BooleanProperty errorDecorationEnabledProperty = new
            SimpleBooleanProperty(
            true) {

        @Override
        protected void invalidated() {

            ValidationSupport.this.redecorate();
        }
    };

    /**
     * Returns the error decoration enabled property.
     *
     * @return the error decoration enabled property.
     */
    public BooleanProperty errorDecorationEnabledProperty() {

        return this.errorDecorationEnabledProperty;
    }

    /**
     * Sets the value of the error decoration enabled property.
     *
     * @param enabled
     *         the value of the error decoration enabled property.
     */
    public void setErrorDecorationEnabled(final boolean enabled) {

        this.errorDecorationEnabledProperty.set(enabled);
    }

    private boolean isErrorDecorationEnabled() {

        return this.errorDecorationEnabledProperty.get();
    }



    private final ReadOnlyObjectWrapper<ValidationResult>
            validationResultProperty = new ReadOnlyObjectWrapper<>();


    /**
     * Retrieves current validation result
     *
     * @return validation result
     */
    public ValidationResult getValidationResult() {

        return this.validationResultProperty.get();
    }

    /**
     * Can be used to track validation result changes
     *
     * @return The Validation result property.
     */
    public ReadOnlyObjectProperty<ValidationResult> validationResultProperty() {

        return this.validationResultProperty.getReadOnlyProperty();
    }

    private final BooleanProperty invalidProperty = new SimpleBooleanProperty();

    /**
     * Returns current validation state.
     *
     * @return true if there is at least one error
     */
    public Boolean isInvalid() {

        return this.invalidProperty.get();
    }

    /**
     * Validation state property
     *
     * @return validation state property
     */
    public ReadOnlyBooleanProperty invalidProperty() {

        return this.invalidProperty;
    }


    private final ObjectProperty<ValidationDecoration>
            validationDecoratorProperty = new
            SimpleObjectProperty<ValidationDecoration>(
            this, "validationDecorator",
            new GraphicValidationDecoration()) { //$NON-NLS-1$

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
            ValidationSupport.this.redecorate();
        }
    };

    /**
     * @return The Validation decorator property
     */
    public ObjectProperty<ValidationDecoration> validationDecoratorProperty() {

        return this.validationDecoratorProperty;
    }

    /**
     * Returns current validation decorator
     *
     * @return current validation decorator or null if none
     */
    public ValidationDecoration getValidationDecorator() {

        return this.validationDecoratorProperty.get();
    }

    /**
     * Sets new validation decorator
     *
     * @param decorator
     *         new validation decorator. Null value is valid - no decoration
     *         will occur
     */
    public void setValidationDecorator(final ValidationDecoration decorator) {

        this.validationDecoratorProperty.set(decorator);
    }


    /**
     * Registers {@link Validator} for specified control with additional
     * possiblity to mark control as required or not.
     *
     * @param <T>
     *         the type of the {@link Validator}.
     * @param c
     *         control to validate
     * @param required
     *         true if controls should be required
     * @param validator
     *         {@link Validator} to be used
     *
     * @return true if registration is successful
     */
    @SuppressWarnings("unchecked")
    public <T> boolean registerValidator(final Control c,
            final boolean required, final Validator<T> validator) {

        Optional.ofNullable(c)
                .ifPresent(ctrl -> ctrl.getProperties()
                        .addListener(
                                (MapChangeListener<Object, Object>)
                                        change -> {

                                    if (ValidationSupport
                                            .CTRL_REQUIRED_FLAG.equals(
                                            change.getKey())) {
                                        ValidationSupport.this.redecorate();
                                    }
                                }));

        ValidationSupport.setRequired(c, required);

        return ValueExtractor.getObservableValueExtractor(c)
                .map(e -> {

                    final ObservableValue<T> observable =
                            (ObservableValue<T>) e.call(
                            c);

                    final Consumer<T> updateResults = value -> Platform.runLater(() -> this.validationResults.put(c,
                            validator.apply(c, value)));

                    this.controls.add(c);

                    observable.addListener((o) -> {
                        this.dataChanged.set(true);
                        updateResults.accept(observable.getValue());

                        this.setupCollectionChangeHandler(observable.getValue(),
                                updateResults);
                    });

                    this.setupCollectionChangeHandler(observable.getValue(),
                            updateResults);


                    updateResults.accept(observable.getValue());

                    return e;

                })
                .isPresent();
    }

    private <T> void setupCollectionChangeHandler(final T newValue,
            final Consumer<T> updateResults) {

        if (newValue != null) {

            if (newValue instanceof ObservableList<?>) {

                final ObservableList<?> list = (ObservableList<?>) newValue;
                list.addListener(new WeakListChangeListener<>(
                        (final ListChangeListener.Change<?> change) -> {

                            this.dataChanged.set(true);
                            updateResults.accept(newValue);
                        }));
                list.addListener((final Observable observable) -> {

                    this.dataChanged.set(true);
                    updateResults.accept(newValue);
                });
            }
            else if (newValue instanceof ObservableSet<?>) {

                final ObservableSet<?> set = (ObservableSet<?>) newValue;
                set.addListener(new WeakSetChangeListener<>(
                        (final SetChangeListener.Change<?> change) -> {

                            this.dataChanged.set(true);
                            updateResults.accept(newValue);
                        }));

                set.addListener((final Observable observable) -> {

                    this.dataChanged.set(true);
                    updateResults.accept(newValue);
                });
            }
            else if (newValue instanceof ObservableMap<?, ?>) {
                final ObservableMap<?, ?> map = (ObservableMap<?, ?>) newValue;
                map.addListener(new WeakMapChangeListener<>(
                        (final MapChangeListener.Change<?, ?> change) -> {

                            this.dataChanged.set(true);
                            updateResults.accept(newValue);
                        }));
                map.addListener((final Observable observable) -> {

                    this.dataChanged.set(true);
                    updateResults.accept(newValue);
                });
            }
        }
    }

    /**
     * Registers {@link Validator} for specified control and makes control
     * required
     *
     * @param <T>
     *         the type of the {@link Validator}.
     * @param c
     *         control to validate
     * @param validator
     *         {@link Validator} to be used
     *
     * @return true if registration is successful
     */
    public <T> boolean registerValidator(final Control c,
            final Validator<T> validator) {

        return this.registerValidator(c, true, validator);
    }

    /**
     * Returns currently registered controls
     *
     * @return set of currently registered controls
     */
    public Set<Control> getRegisteredControls() {

        return Collections.unmodifiableSet(this.controls);
    }

    /**
     * Returns optional highest severity message for a control
     *
     * @param target
     *         control
     *
     * @return Optional highest severity message for a control
     */
    public Optional<ValidationMessage> getHighestMessage(final Control target) {

        return Optional.ofNullable(this.validationResults.get(target))
                .flatMap(result -> result.getMessages()
                        .stream()
                        .max(ValidationMessage.COMPARATOR));
    }
}
