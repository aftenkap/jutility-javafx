package org.jutility.javafx.control.labeled;


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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.jutility.javafx.control.wrapper.TextFieldWrapper;


/**
 * The {@link LabeledTextField} class provides a {@TextField} with a
 * freely positionable {@link Label}.
 * 
 * @author Peter J. Radics
 * 
 * @version 0.1.2
 * @since 0.1.0
 */
public class LabeledTextField
        extends TextFieldWrapper
        implements ILabeledControl {

    private final ObjectProperty<Label>    label;

    private final ObjectProperty<Position> labelPosition;


    @Override
    public ObjectProperty<Label> labelProperty() {

        return this.label;
    }

    @Override
    public Label getLabel() {

        return this.label.get();
    }

    @Override
    public void setLabel(final Label label) {

        this.label.set(label);
    }

    @Override
    public ObjectProperty<Position> labelPositionProperty() {

        return this.labelPosition;
    }

    @Override
    public Position getLabelPosition() {

        return this.labelPosition.get();
    }

    @Override
    public void setLabelPosition(final Position labelPosition) {

        this.labelPosition.set(labelPosition);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided label text, positioning the {@link Label} to the left of the
     * {@link TextField}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     */
    public LabeledTextField(final String labelText) {

        this(labelText, Position.WEST);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided
     * {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     * Position}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired
     *            {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     *            Position} of the {@link Label}.
     */
    public LabeledTextField(final String labelText, final Position position) {

        this(labelText, position, null);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided
     * {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     * Position}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired
     *            {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     *            Position} of the {@link Label}.
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public LabeledTextField(final String labelText, final Position position,
            final String text) {

        this(new Label(labelText), position, text);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} to the left of the
     * {@link TextField}.
     * 
     * @param label
     *            the {@link Label}.
     */
    public LabeledTextField(final Label label) {

        this(label, Position.WEST);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided
     * {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     * Position}.
     * 
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired
     *            {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     *            Position} of the {@link Label}.
     */
    public LabeledTextField(final Label label, final Position position) {

        this(label, position, null);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided
     * {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     * Position}.
     * 
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired
     *            {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     *            Position} of the {@link Label}.
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public LabeledTextField(final Label label, final Position position,
            final String text) {

        super(text);

        this.label = new SimpleObjectProperty<>(label);
        this.labelPosition = new SimpleObjectProperty<>(position);

        this.addNode(label, position);

        if (label != null) {

            label.setLabelFor(this.getWrapped());
        }

        this.setUpEventHandlers();
    }

    private void setUpEventHandlers() {

        this.label.addListener((observable, oldValue, newValue) -> {

            if (oldValue != null) {

                this.getNodes().remove(oldValue);
            }
            if (newValue != null) {

                this.getLabel().setLabelFor(this.getWrapped());
            }

            this.addNode(newValue, this.getLabelPosition());
        });


        this.labelPosition.addListener((observable, oldValue, newValue) -> {

            this.removeNode(this.getLabel(), oldValue);

            if (newValue != null) {

                this.addNode(this.getLabel(), newValue);
            }
        });

        this.wrapped().addListener((observable, oldValue, newValue) -> {

            if (this.getLabel() != null && newValue != null) {

                this.getLabel().setLabelFor(newValue);
            }
        });
    }

    @Override
    public void requestFocus() {

        this.getWrapped().requestFocus();
    }

    @Override
    protected ObservableList<Node> getNodes() {

        return super.getNodes();
    }

    @Override
    protected void addNode(Node node, Position position) {

        super.addNode(node, position);
    }

    @Override
    protected Node removeNode(Node nodeToRemove, Position position) {

        return super.removeNode(nodeToRemove, position);
    }
}
