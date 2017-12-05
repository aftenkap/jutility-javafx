package org.jutility.javafx.control.wrapper;


//@formatter:off
/*
 * #%L
 * jutility-javafx
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
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
//@formatter:on

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Skinnable;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.ValidationDecoration;
import org.jutility.javafx.control.validation.ValidationSupport;


/**
 * The abstract {@code ControlWrapper} class provides base functionality for
 * wrapping a {@link Control} within a 3x3 {@link GridPane}.
 *
 * @param <T>
 *         the type of the {@link Control} to be wrapped.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public abstract class ControlWrapper<T extends Control>
        extends GridPane {

    private final ValidationSupport validationSupport;

    private final ObjectProperty<Node> topLeftNodeProperty;
    private final ObjectProperty<Node> topCenterNodeProperty;
    private final ObjectProperty<Node> topRightNodeProperty;
    private final ObjectProperty<Node> centerLeftNodeProperty;
    private final ObjectProperty<Node> centerRightNodeProperty;
    private final ObjectProperty<Node> bottomLeftNodeProperty;
    private final ObjectProperty<Node> bottomCenterNodeProperty;
    private final ObjectProperty<Node> bottomRightNodeProperty;

    private final ObjectProperty<T> wrappedControlProperty;

    private final ObservableList<Action> contextMenuActions;

    /**
     * Returns the {@link ValidationSupport}.
     *
     * @return the {@link ValidationSupport}.
     */
    public ValidationSupport validationSupport() {

        return this.validationSupport;
    }

    /**
     * Returns whether or not the state of the Control is valid.
     *
     * @return {@code true}, if the state of the Control is valid; {@code false}
     * otherwise.
     */
    public boolean isInvalid() {

        return this.validationSupport.isInvalid();
    }

    /**
     * Returns the invalid property of the control.
     *
     * @return the invalid property of the control.
     */
    public ReadOnlyBooleanProperty invalidProperty() {

        return this.validationSupport.invalidProperty();
    }

    /**
     * Can be used to track validation result changes
     *
     * @return the validation result property.
     */
    public ReadOnlyObjectProperty<ValidationResult> validationResultProperty() {

        return this.validationSupport.validationResultProperty();
    }

    /**
     * Returns the validation result.
     *
     * @return the validation result.
     */
    public ValidationResult getValidationResult() {

        return this.validationSupport.getValidationResult();
    }


    /**
     * Registers {@link Validator} for specified control with additional
     * possibility to mark control as required or not.
     *
     * @param <S>
     *         the type of the {@link Validator}.
     * @param required
     *         true if controls should be required
     * @param validator
     *         {@link Validator} to be used
     *
     * @return true if registration is successful
     */
    public <S> boolean registerValidator(final boolean required,
            final Validator<S> validator) {

        return this.validationSupport.registerValidator(
                this.getWrappedControl(), required, validator);
    }

    /**
     * Registers {@link Validator} for the control and makes control required
     *
     * @param <S>
     *         the type of the {@link Validator}.
     * @param validator
     *         {@link Validator} to be used
     *
     * @return true if registration is successful
     */
    public <S> boolean registerValidator(final Validator<S> validator) {

        return this.registerValidator(true, validator);
    }


    /**
     * Returns the error decoration enabled property.
     *
     * @return the error decoration enabled property.
     */
    public BooleanProperty errorDecorationEnabledProperty() {

        return this.validationSupport.errorDecorationEnabledProperty();
    }

    /**
     * Returns optional highest severity message for a control
     *
     * @return optional highest severity message for a control
     */
    public Optional<ValidationMessage> getHighestMessage() {

        return this.validationSupport.getHighestMessage(
                this.getWrappedControl());
    }

    /**
     * Returns the current validation decorator.
     *
     * @return the current validation decorator.
     */
    public ValidationDecoration getValidationDecorator() {

        return this.validationSupport.getValidationDecorator();
    }

    /**
     * Check control's required flag.
     *
     * @return whether or not this control is required.
     */
    public boolean isRequired() {

        return ValidationSupport.isRequired(this.getWrappedControl());
    }

    /**
     * Redecorates all known components Only decorations related to validation
     * are affected
     */
    public void redecorate() {

        this.validationSupport.redecorate();
    }

    /**
     * Sets the value of the property errorDecorationEnabled.
     *
     * @param enabled
     *         the value of the property errorDecorationEnabled.
     */
    public void setErrorDecorationEnabled(final boolean enabled) {

        this.validationSupport.setErrorDecorationEnabled(enabled);
    }

    /**
     * Set control's required flag.
     *
     * @param required
     *         whether or not this control is required.
     */
    public void setRequired(final boolean required) {

        ValidationSupport.setRequired(this.getWrappedControl(), required);
    }

    /**
     * Sets a new validation decorator
     *
     * @param decorator
     *         the new validation decorator
     */
    public void setValidationDecorator(final ValidationDecoration decorator) {

        this.validationSupport.setValidationDecorator(decorator);
    }

    /**
     * Returns the validation decorator property.
     *
     * @return the validation decorator property.
     */
    public ObjectProperty<ValidationDecoration> validationDecoratorProperty() {

        return this.validationSupport.validationDecoratorProperty();
    }



    /**
     * Returns the property containing the top left {@link Node}.
     *
     * @return the property containing the top left {@link Node}.
     */
    protected ObjectProperty<Node> topLeftNodeProperty() {

        return this.topLeftNodeProperty;
    }

    /**
     * Returns the top left {@link Node}.
     *
     * @return the top left {@link Node}.
     */
    protected Node getTopLeftNode() {

        return this.topLeftNodeProperty.get();
    }


    /**
     * Sets the top left {@link Node}.
     *
     * @param value
     *         the top left {@link Node}.
     */
    protected void setTopLeftNode(final Node value) {

        this.topLeftNodeProperty.set(value);
    }

    /**
     * Returns the property containing the top center {@link Node}.
     *
     * @return the property containing the top center {@link Node}.
     */
    protected ObjectProperty<Node> topCenterNodeProperty() {

        return this.topCenterNodeProperty;
    }

    /**
     * Returns the top center {@link Node}.
     *
     * @return the top center {@link Node}.
     */
    protected Node getTopCenterNode() {

        return this.topCenterNodeProperty.get();
    }

    /**
     * Sets the top center {@link Node}.
     *
     * @param value
     *         the the top center {@link Node}.
     */
    protected void setTopCenterNode(final Node value) {

        this.topCenterNodeProperty.set(value);
    }

    /**
     * Returns the property containing the top right {@link Node}.
     *
     * @return the property containing the top right {@link Node}.
     */
    protected ObjectProperty<Node> topRightNodeProperty() {

        return this.topRightNodeProperty;
    }

    /**
     * Returns the top right {@link Node}.
     *
     * @return the top right {@link Node}.
     */
    protected Node getTopRightNode() {

        return this.topRightNodeProperty.get();
    }


    /**
     * Sets the top right {@link Node}.
     *
     * @param value
     *         the top right {@link Node}.
     */
    protected void setTopRightNode(final Node value) {

        this.topRightNodeProperty.set(value);
    }



    /**
     * Returns the property containing the center left {@link Node}.
     *
     * @return the property containing the center left {@link Node}.
     */
    protected ObjectProperty<Node> centerLeftNodeProperty() {

        return this.centerLeftNodeProperty;
    }

    /**
     * Returns the center left {@link Node}.
     *
     * @return the center left {@link Node}.
     */
    protected Node getCenterLeftNode() {

        return this.centerLeftNodeProperty.get();
    }


    /**
     * Sets the center left {@link Node}.
     *
     * @param value
     *         the center left {@link Node}.
     */
    protected void setCenterLeftNode(final Node value) {

        this.centerLeftNodeProperty.set(value);
    }


    /**
     * Returns the property containing the center right {@link Node}.
     *
     * @return the property containing the center right {@link Node}.
     */
    protected ObjectProperty<Node> centerRightNodeProperty() {

        return this.centerRightNodeProperty;
    }

    /**
     * Returns the center right {@link Node}.
     *
     * @return the center right {@link Node}.
     */
    protected Node getCenterRightNode() {

        return this.centerRightNodeProperty.get();
    }


    /**
     * Sets the center right {@link Node}.
     *
     * @param value
     *         the center right {@link Node}.
     */
    protected void setCenterRightNode(final Node value) {

        this.centerRightNodeProperty.set(value);
    }


    /**
     * Returns the property containing the bottom left {@link Node}.
     *
     * @return the property containing the bottom left {@link Node}.
     */
    protected ObjectProperty<Node> bottomLeftNodeProperty() {

        return this.bottomLeftNodeProperty;
    }

    /**
     * Returns the bottom left {@link Node}.
     *
     * @return the bottom left {@link Node}.
     */
    protected Node getBottomLeftNode() {

        return this.bottomLeftNodeProperty.get();
    }


    /**
     * Sets the bottom left {@link Node}.
     *
     * @param value
     *         the bottom left {@link Node}.
     */
    protected void setBottomLeftNode(final Node value) {

        this.bottomLeftNodeProperty.set(value);
    }


    /**
     * Returns the property containing the bottom center {@link Node}.
     *
     * @return the property containing the bottom center {@link Node}.
     */
    protected ObjectProperty<Node> bottomCenterNodeProperty() {

        return this.bottomCenterNodeProperty;
    }

    /**
     * Returns the bottom center {@link Node}.
     *
     * @return the bottom center {@link Node}.
     */
    protected Node getBottomCenterNode() {

        return this.bottomCenterNodeProperty.get();
    }


    /**
     * Sets the bottom center {@link Node}.
     *
     * @param value
     *         the bottom center {@link Node}.
     */
    protected void setBottomCenterNode(final Node value) {

        this.bottomCenterNodeProperty.set(value);
    }

    /**
     * Returns the property containing the bottom right {@link Node}.
     *
     * @return the property containing the bottom right {@link Node}.
     */
    protected ObjectProperty<Node> bottomRightNodeProperty() {

        return this.bottomRightNodeProperty;
    }

    /**
     * Returns the bottom right {@link Node}.
     *
     * @return the bottom right {@link Node}.
     */
    protected Node getBottomRightNode() {

        return this.bottomRightNodeProperty.get();
    }


    /**
     * Sets the bottom right {@link Node}.
     *
     * @param value
     *         the bottom right {@link Node}.
     */
    protected void setBottomRightNode(final Node value) {

        this.bottomRightNodeProperty.set(value);
    }

    /**
     * Returns the property containing the wrapped {@link Node}.
     *
     * @return the property containing the wrapped {@link Node}.
     */
    protected ObjectProperty<T> wrappedControlProperty() {

        return this.wrappedControlProperty;
    }

    /**
     * Returns the wrapped {@link Control}.
     *
     * @return the wrapped{@link Control}.
     */
    protected T getWrappedControl() {

        return this.wrappedControlProperty.get();
    }


    /**
     * Sets the wrapped {@link Control}.
     *
     * @param value
     *         the wrapped {@link Control}.
     */
    protected void setWrappedControl(final T value) {

        this.wrappedControlProperty.set(value);
    }


    /**
     * Adds the provided {@link Node} at the desired {@link Pos Position}.
     *
     * @param node
     *         the {@link Node} to add.
     * @param position
     *         the desired {@link Pos Position}.
     */
    protected void addNode(final Node node, final Pos position) {

        switch (position) {

            case TOP_LEFT:
                this.setTopLeftNode(node);
                break;
            case TOP_CENTER:
                this.setTopCenterNode(node);
                break;
            case TOP_RIGHT:
                this.setTopRightNode(node);
                break;
            case CENTER_LEFT:
                this.setCenterLeftNode(node);
                break;
            case CENTER_RIGHT:
                this.setCenterRightNode(node);
                break;
            case BOTTOM_LEFT:
                this.setBottomLeftNode(node);
                break;
            case BOTTOM_CENTER:
                this.setBottomCenterNode(node);
                break;
            case BOTTOM_RIGHT:
                this.setBottomRightNode(node);
                break;
            default:
                throw new IllegalArgumentException(
                        "Cannot set node at position " + position);
        }
    }

    /**
     * Returns the {@link Node} at the provided {@link Pos Position}.
     *
     * @param position
     *         the desired {@link Pos Position}.
     *
     * @return the {@link Node} at the provided {@link Pos Position}.
     */
    protected Node getNode(final Pos position) {

        switch (position) {

            case TOP_LEFT:
                return this.getTopLeftNode();
            case TOP_CENTER:
                return this.getTopCenterNode();
            case TOP_RIGHT:
                return this.getTopRightNode();
            case CENTER_LEFT:
                return this.getCenterLeftNode();
            case CENTER:
                return this.getWrappedControl();
            case CENTER_RIGHT:
                return this.getCenterRightNode();
            case BOTTOM_LEFT:
                return this.getBottomLeftNode();
            case BOTTOM_CENTER:
                return this.getBottomCenterNode();
            case BOTTOM_RIGHT:
                return this.getBottomRightNode();
            default:
                return null;
        }
    }


    /**
     * Removes the provided {@link Node}.
     *
     * @param node
     *         the {@link Node} to remove.
     *
     * @return the removed {@link Node} or {@code null}, if no element was
     * found.
     */
    protected Node removeNode(final Node node) {

        if (node == null || !this.getChildren()
                .contains(node)) {

            return null;
        }

        if (node.equals(this.getTopLeftNode())) {

            return this.removeNode(Pos.TOP_LEFT);
        }
        if (node.equals(this.getTopCenterNode())) {

            return this.removeNode(Pos.TOP_CENTER);
        }
        if (node.equals(this.getTopRightNode())) {

            return this.removeNode(Pos.TOP_RIGHT);
        }
        if (node.equals(this.getCenterLeftNode())) {

            return this.removeNode(Pos.CENTER_LEFT);
        }
        if (node.equals(this.getCenterRightNode())) {

            return this.removeNode(Pos.CENTER_RIGHT);
        }
        if (node.equals(this.getBottomLeftNode())) {

            return this.removeNode(Pos.BOTTOM_LEFT);
        }
        if (node.equals(this.getBottomCenterNode())) {

            return this.removeNode(Pos.BOTTOM_CENTER);
        }
        if (node.equals(this.getBottomRightNode())) {

            return this.removeNode(Pos.BOTTOM_RIGHT);
        }
        if (node.equals(this.getWrappedControl())) {

            throw new IllegalArgumentException("Cannot removed wrapped node!");
        }

        return null;
    }

    /**
     * Removes the {@link Node} from the provided {@link Pos Position}.
     *
     * @param position
     *         the {@link Pos Position} of the {@link Node} to remove.
     *
     * @return the removed {@link Node} or {@code null}, if no element was found
     * at the provided {@link Pos Position}.
     */
    protected Node removeNode(final Pos position) {

        Node node = this.getNode(position);

        this.addNode(null, position);
        super.getChildren()
                .remove(node);

        return node;
    }


    /**
     * Removes the {@link Node} from the provided {@link Pos Position}.
     *
     * @param nodeToRemove
     *         the node to remove.
     * @param position
     *         the {@link Pos Position} of the {@link Node} to remove.
     *
     * @return the removed {@link Node} or {@code null}, if no element was found
     * at the provided {@link Pos Position}.
     */
    protected Node removeNode(final Node nodeToRemove, final Pos position) {

        Node node = this.getNode(position);
        if ((node != null) && node.equals(nodeToRemove)) {

            this.addNode(null, position);
            super.getChildren()
                    .remove(node);
        }

        return node;
    }


    /**
     * Provides access to the context menu actions.
     *
     * @return the context menu actions list.
     */
    public ObservableList<Action> contextMenuActions() {

        return this.contextMenuActions;
    }


    /**
     * The ContextMenu to show for this control.
     *
     * @return the ContextMenu to show for this control.
     *
     * @see #getContextMenu()
     * @see #setContextMenu(ContextMenu)
     */
    public final ObjectProperty<ContextMenu> contextMenuProperty() {

        return this.getWrappedControl()
                .contextMenuProperty();
    }


    /**
     * Gets the value of the context menu property
     *
     * @return the value of the context menu property
     */
    public final ContextMenu getContextMenu() {

        return this.getWrappedControl()
                .getContextMenu();
    }

    /**
     * Sets the value of the context menu property
     *
     * @param contextMenu
     *         the value of the context menu property.
     */
    public final void setContextMenu(final ContextMenu contextMenu) {

        this.getWrappedControl()
                .setContextMenu(contextMenu);
    }

    /**
     * The Tooltip for this control.
     *
     * @return the Tooltip for this control.
     *
     * @see #getTooltip()
     * @see #setTooltip(Tooltip)
     */
    public final ObjectProperty<Tooltip> tooltipProperty() {

        return this.getWrappedControl()
                .tooltipProperty();
    }


    /**
     * Gets the value of the tooltip property.
     *
     * @return the value of the tooltip property.
     */
    public final Tooltip getTooltip() {

        return this.tooltipProperty()
                .get();
    }

    /**
     * Sets the value of the tooltip property.
     *
     * @param value
     *         the value of the tooltip property.
     */
    public void setTooltip(final Tooltip value) {

        this.getWrappedControl()
                .setTooltip(value);
    }

    /**
     * Sets the value of the property tooltip.
     *
     * @param tooltip
     *         the value of the property tooltip.
     */
    public final void setContextMenu(final Tooltip tooltip) {

        this.tooltipProperty()
                .set(tooltip);
    }

    /**
     * Skin is responsible for rendering this Control. From the perspective of
     * the Control, the Skin is a black box. It listens and responds to changes
     * in state in a Control. There is a one-to-one relationship between a
     * Control and its Skin. Every Skin maintains a back reference to the
     * Control via the {@link Skin#getSkinnable()} method. <br>
     * A skin may be {@code null}.
     * <p>
     * Specified by: skinProperty in interface {@link Skinnable}
     *
     * @return the Skin property.
     *
     * @see #getSkin()
     * @see #setSkin(Skin)
     */
    public final ObjectProperty<Skin<?>> skinProperty() {

        return this.getWrappedControl()
                .skinProperty();
    }

    /**
     * Returns the skin.
     *
     * @return the Skin.
     */
    public final Skin<?> getSkin() {

        return this.getWrappedControl()
                .getSkin();
    }

    /**
     * Sets the skin.
     *
     * @param value
     *         the skin.
     */
    public void setSkin(final Skin<?> value) {

        this.getWrappedControl()
                .setSkin(value);
    }


    /**
     * Sets the horizontal grow priority for the wrapped control. If set, the
     * wrapper will use the priority to allocate the child additional horizontal
     * space if the wrapper is resized larger than it's preferred width. Setting
     * the value to null will remove the constraint.
     *
     * @param priority
     *         the horizontal grow priority.
     */
    public void setControlHGrow(final Priority priority) {

        GridPane.setHgrow(this.getWrappedControl(), priority);
    }

    /**
     * Sets the vertical grow priority for the wrapped control. If set, the
     * wrapper will use the priority to allocate the child additional vertical
     * space if the wrapper is resized larger than it's preferred height.
     * Setting the value to null will remove the constraint.
     *
     * @param priority
     *         the vertical grow priority.
     */
    public void setControlVGrow(final Priority priority) {

        GridPane.setVgrow(this.getWrappedControl(), priority);
    }

    /**
     * Creates a new instance of the {@code ControlWrapper} class.
     */
    public ControlWrapper() {

        this(null);

    }

    /**
     * Creates a new instance of the {@code ControlWrapper} class.
     *
     * @param wrappedControl
     *         the wrapped control.
     */
    public ControlWrapper(final T wrappedControl) {

        Objects.nonNull(wrappedControl);

        this.validationSupport = new ValidationSupport();

        this.topCenterNodeProperty = new SimpleObjectProperty<>("wrapper",
                "topCenterNode");
        this.topRightNodeProperty = new SimpleObjectProperty<>("wrapper",
                "topRightNode");
        this.centerRightNodeProperty = new SimpleObjectProperty<>("wrapper",
                "centerRightNode");
        this.bottomRightNodeProperty = new SimpleObjectProperty<>("wrapper",
                "bottomRightNode");
        this.bottomCenterNodeProperty = new SimpleObjectProperty<>("wrapper",
                "bottomCenterNode");
        this.bottomLeftNodeProperty = new SimpleObjectProperty<>("wrapper",
                "bottomLeftNode");
        this.centerLeftNodeProperty = new SimpleObjectProperty<>("wrapper",
                "centerLeftNode");
        this.topLeftNodeProperty = new SimpleObjectProperty<>("wrapper",
                "topLeftNode");

        this.wrappedControlProperty = new SimpleObjectProperty<>("wrapper",
                "wrappedControl");


        this.contextMenuActions = FXCollections.observableList(
                new LinkedList<Action>());

        GridPane.setHgrow(wrappedControl, Priority.SOMETIMES);
        GridPane.setVgrow(wrappedControl, Priority.SOMETIMES);

        this.setupEventHandlers();

        this.wrappedControlProperty.set(wrappedControl);
    }

    private void setupEventHandlers() {

        this.setUpEventHandler(this.topCenterNodeProperty);
        this.setUpEventHandler(this.topRightNodeProperty);
        this.setUpEventHandler(this.centerRightNodeProperty);
        this.setUpEventHandler(this.bottomRightNodeProperty);
        this.setUpEventHandler(this.bottomCenterNodeProperty);
        this.setUpEventHandler(this.bottomLeftNodeProperty);
        this.setUpEventHandler(this.centerLeftNodeProperty);
        this.setUpEventHandler(this.topLeftNodeProperty);
        this.setUpEventHandler(this.wrappedControlProperty);

        this.contextMenuActions.addListener(
                (final Change<? extends Action> change) -> this
                        .getWrappedControl()
                        .contextMenuProperty()
                        .set(ActionUtils.createContextMenu(
                                this.contextMenuActions())));
    }

    private void setUpEventHandler(final ObjectProperty<?> property) {

        property.addListener(
                (observable, oldValue, newValue) -> this.performLayout());

        property.addListener((observable) -> this.performLayout());
    }

    /**
     * Performs the layouting operations.
     */
    protected void performLayout() {

        final boolean topLeftNodePresent = this.getTopLeftNode() != null;
        final boolean topCenterNodePresent = this.getTopCenterNode() != null;
        final boolean topRightNodePresent = this.getTopRightNode() != null;

        final boolean centerLeftNodePresent = this.getCenterLeftNode() != null;
        final boolean centerRightNodePresent =
                this.getCenterRightNode() != null;

        final boolean bottomLeftNodePresent = this.getBottomLeftNode() != null;
        final boolean bottomCenterNodePresent =
                this.getBottomCenterNode() != null;
        final boolean bottomRightNodePresent =
                this.getBottomRightNode() != null;

        final boolean wrappedControlPresent = this.getWrappedControl() != null;


        final boolean topRowOccupied =
                topCenterNodePresent || topRightNodePresent
                || topLeftNodePresent;
        final boolean leftColumnOccupied =
                centerLeftNodePresent || topLeftNodePresent
                || bottomLeftNodePresent;

        int centerRow = 0;
        int centerCol = 0;

        if (topRowOccupied) {

            centerRow++;
        }
        if (leftColumnOccupied) {

            centerCol++;
        }


        this.getChildren()
                .clear();


        if (topLeftNodePresent) {

            super.add(this.getTopLeftNode(), 0, 0);
        }
        if (topCenterNodePresent) {

            super.add(this.getTopCenterNode(), centerCol, 0);
        }
        if (topRightNodePresent) {

            super.add(this.getTopRightNode(), centerCol + 1, 0);
        }

        if (centerLeftNodePresent) {

            super.add(this.getCenterLeftNode(), 0, centerRow);
        }


        if (wrappedControlPresent) {

            super.add(this.getWrappedControl(), centerCol, centerRow);
        }


        if (centerRightNodePresent) {

            super.add(this.getCenterRightNode(), centerCol + 1, centerRow);
        }

        if (bottomLeftNodePresent) {

            super.add(this.getBottomLeftNode(), 0, centerRow + 1);
        }
        if (bottomCenterNodePresent) {

            super.add(this.getBottomCenterNode(), centerCol, centerRow + 1);
        }
        if (bottomRightNodePresent) {

            super.add(this.getBottomLeftNode(), centerCol + 1, centerRow + 1);
        }

    }

    @Override
    public void add(final Node child, final int columnIndex,
            final int rowIndex) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of " + this.getClass()
                        .getCanonicalName() + " is disallowed!");
    }

    @Override
    public void add(final Node child, final int columnIndex, final int rowIndex,
            final int colspan, final int rowspan) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of " + this.getClass()
                        .getCanonicalName() + " is disallowed!");
    }

    @Override
    public void addColumn(final int columnIndex, final Node... children) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of " + this.getClass()
                        .getCanonicalName() + " is disallowed!");
    }

    @Override
    public void addRow(final int rowIndex, final Node... children) {

        throw new UnsupportedOperationException(
                "Read-write access to the children of " + this.getClass()
                        .getCanonicalName() + " is disallowed!");
    }


    /**
     * Returns the {@link Node Nodes} within this wrapper.
     *
     * @return the {@link Node Nodes} within this wrapper.
     */
    protected ObservableList<Node> getNodes() {

        return super.getChildren();
    }


    @Override
    public void requestFocus() {

        super.requestFocus();
        this.getWrappedControl()
                .requestFocus();
    }
}
