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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javafx.beans.WeakInvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

import org.controlsfx.validation.ValidationResult;


/**
 * @author Peter J. Radics
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ValidationGroup {

    private final Map<Node, ValidationSupport>            subValidators;

    private final BooleanProperty                         invalidProperty;
    private final ReadOnlyObjectWrapper<ValidationResult> validationResultProperty;

    /**
     * Returns the invalid property.
     * 
     * @return the invalid property.
     */
    public ReadOnlyBooleanProperty invalidProperty() {

        return this.invalidProperty;
    }

    /**
     * Returns the value of the invalid property.
     * 
     * @return the value of the invalid property.
     */
    public Boolean isInvalid() {

        return this.invalidProperty.getValue();
    }


    /**
     * Returns the value of the validation result property.
     * 
     * @return the value of the validation result property.
     */
    public ValidationResult getValidationResult() {

        return this.validationResultProperty.get();
    }

    /**
     * Returns the validation result property.
     * 
     * @return the validation result property.
     */
    public ReadOnlyObjectProperty<ValidationResult> validationResultProperty() {

        return this.validationResultProperty.getReadOnlyProperty();
    }

    /**
     * Creates a new instance of the {@link ValidationGroup} class.
     */
    public ValidationGroup() {

        this.invalidProperty = new SimpleBooleanProperty();
        this.validationResultProperty = new ReadOnlyObjectWrapper<>();
        this.subValidators = new WeakHashMap<>();
    }


    /**
     * Registers the validation support of a sub node.
     * 
     * @param node
     *            the sub node.
     * @param validationSupport
     *            the validation support.
     * @return {@code true}, if the support was registered successfully;
     *         {@code false} otherwise.
     */
    public boolean registerSubValidationSupport(final Node node,
            ValidationSupport validationSupport) {

        this.subValidators.put(node, validationSupport);

        validationSupport.invalidProperty().addListener(
                new WeakInvalidationListener(observable -> {

                    this.validate();
                }));

        return true;
    }

    private void validate() {

        boolean isValid = true;

        List<ValidationResult> validationResults = new ArrayList<>();

        for (ValidationSupport validationSupport : this.subValidators.values()) {

            isValid = isValid && !validationSupport.isInvalid();
            validationResults.add(validationSupport.getValidationResult());
        }
        this.invalidProperty.set(!isValid);
        this.validationResultProperty.set(ValidationResult
                .fromResults(validationResults));
    }

    /**
     * Issues a request for re-decoration of decorated controls.
     */
    public void redecorate() {

        for (ValidationSupport validationSupport : this.subValidators.values()) {

            validationSupport.redecorate();
        }
    }

    /**
     * Issues a request for re-validation and re-decoration of decorated
     * controls.
     */
    public void revalidate() {

        for (ValidationSupport validationSupport : this.subValidators.values()) {

            validationSupport.revalidate();
        }
    }
}
