package org.jutility.javafx.control.labeled;

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


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.jutility.javafx.control.wrapper.TextFieldWrapper;

import static org.jutility.javafx.control.wrapper.WrapperBase.Position;


/**
 * The {@link LabeledTextField} class provides a {@TextField} with a
 * freely positionable {@link Label}.
 * 
 * @author Peter J. Radics
 * 
 */
public class LabeledTextField
        extends TextFieldWrapper {

    private final ObjectProperty<Label>    label;

    private final ObjectProperty<Position> labelPosition;


    /**
     * Returns the label property.
     * 
     * @return the label property.
     */
    public ObjectProperty<Label> label() {

        return this.label;
    }

    /**
     * Returns the {@link Label}.
     * 
     * @return the {@link Label}.
     */
    public Label getLabel() {

        return this.label.get();
    }

    /**
     * Sets the {@link Label}.
     * 
     * @param label
     *            the {@link Label}.
     */
    public void setLabel(final Label label) {

        this.label.set(label);
    }

    /**
     * Returns the label position property.
     * 
     * @return the label position property.
     */
    public ObjectProperty<Position> labelPosition() {

        return this.labelPosition;
    }

    /**
     * Returns the {@link Position} of the {@link Label}.
     * 
     * @return the {@link Position} of the {@link Label}.
     */
    public Position getLabelPosition() {

        return this.labelPosition.get();
    }

    /**
     * Sets the {@link Position} of the {@link Label}.
     * 
     * @param labelPosition
     *            the {@link Position} of the {@link Label}.
     */
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
    public LabeledTextField(String labelText) {

        this(labelText, Position.WEST);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Position}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired {@link Position} of the {@link Label}.
     */
    public LabeledTextField(String labelText, Position position) {

        this(labelText, position, null);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Position}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired {@link Position} of the {@link Label}.
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public LabeledTextField(String labelText, Position position, String text) {

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
    public LabeledTextField(Label label) {

        this(label, Position.WEST);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Position}.
     * 
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired {@link Position} of the {@link Label}.
     */
    public LabeledTextField(Label label, Position position) {

        this(label, position, null);
    }

    /**
     * Creates a new instance of the {@link LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Position}.
     * 
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired {@link Position} of the {@link Label}.
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public LabeledTextField(Label label, Position position, String text) {

        super(text);

        this.label = new SimpleObjectProperty<>(label);
        this.labelPosition = new SimpleObjectProperty<>(position);

        this.addNode(label, position);

        this.setUpEventHandlers();
    }

    private void setUpEventHandlers() {

        this.label.addListener(new ChangeListener<Label>() {

            @Override
            public void changed(ObservableValue<? extends Label> observable,
                    Label oldValue, Label newValue) {

                if (oldValue != null) {

                    LabeledTextField.this.getNodes().remove(oldValue);
                }

                LabeledTextField.this.addNode(newValue,
                        LabeledTextField.this.getLabelPosition());
            }
        });


        this.labelPosition.addListener(new ChangeListener<Position>() {

            @Override
            public void changed(ObservableValue<? extends Position> observable,
                    Position oldValue, Position newValue) {

                LabeledTextField.this.removeNode(
                        LabeledTextField.this.getLabel(), oldValue);

                if (newValue != null) {

                    LabeledTextField.this.addNode(
                            LabeledTextField.this.getLabel(), newValue);
                }
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
