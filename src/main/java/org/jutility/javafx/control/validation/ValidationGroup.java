package org.jutility.javafx.control.validation;


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
    private final Map<Node, ValidationGroup>              subGroups;

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
        this.subGroups = new WeakHashMap<>();
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
    public boolean registerSubValidation(final Node node,
            ValidationSupport validationSupport) {

        this.subValidators.put(node, validationSupport);

        validationSupport.invalidProperty().addListener(
                new WeakInvalidationListener(observable -> {

                    this.validate();
                }));

        return true;
    }

    /**
     * Registers the validation group of a sub node.
     * 
     * @param node
     *            the sub node.
     * @param validationGroup
     *            the validation group.
     * @return {@code true}, if the support was registered successfully;
     *         {@code false} otherwise.
     */
    public boolean registerSubValidation(final Node node,
            ValidationGroup validationGroup) {

        this.subGroups.put(node, validationGroup);

        validationGroup.invalidProperty().addListener(
                new WeakInvalidationListener(observable -> {

                    this.validate();
                }));

        return true;
    }

    /**
     * Removes all sub validation for the provided {@link Node}.
     * 
     * @param node
     *            the {@link Node}.
     * @return {@code true}, if a removal took place; {@code false} otherwise.
     */
    public boolean removeSubValidation(final Node node) {

        boolean removed = false;

        if (this.subValidators.containsKey(node)) {

            this.subValidators.remove(node);
            removed = true;
        }
        if (this.subGroups.containsKey(node)) {

            this.subGroups.remove(node);
            removed = true;
        }

        return removed;
    }

    private void validate() {

        boolean isValid = true;

        List<ValidationResult> validationResults = new ArrayList<>();

        for (ValidationSupport validationSupport : this.subValidators.values()) {

            isValid = isValid && !validationSupport.isInvalid();
            validationResults.add(validationSupport.getValidationResult());
        }

        for (ValidationGroup validationGroup : this.subGroups.values()) {

            isValid = isValid && !validationGroup.isInvalid();
            validationResults.add(validationGroup.getValidationResult());
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
