package org.jutility.javafx.control.labeled;


// @formatter:off
/*
 * #%L 
 * jutility-javafx 
 * %% 
 * Copyright (C) 2013 - 2014 jutility.org 
 * %% 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. 
 * #L%
 */

//@formatter:on


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.jutility.javafx.control.wrapper.ComboBoxWrapper;



/**
 * The {@link LabeledComboBox} class provides a {@link ComboBox} with a freely
 * positionable {@link Label}.
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * @param <T>
 *            the content type of the {@link ComboBox}.
 * 
 */
public class LabeledComboBox<T>
        extends ComboBoxWrapper<T>
        implements ILabeledControl {

    private final ObjectProperty<Label> label;

    private final ObjectProperty<Pos>   labelPosition;


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
    public ObjectProperty<Pos> labelPositionProperty() {

        return this.labelPosition;
    }

    @Override
    public Pos getLabelPosition() {

        return this.labelPosition.get();
    }

    @Override
    public void setLabelPosition(final Pos labelPosition) {

        this.labelPosition.set(labelPosition);
    }

    /**
     * Creates a new instance of the {@link LabeledComboBox} class with the
     * provided label text, positioning the {@link Label} to the left of the
     * {@link TextField}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     */
    public LabeledComboBox(final String labelText) {

        this(labelText, Pos.CENTER_LEFT);
    }

    /**
     * Creates a new instance of the {@link LabeledComboBox} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Pos Position}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired {@link Pos Position} of the {@link Label}.
     */
    public LabeledComboBox(final String labelText, final Pos position) {

        this(null, labelText, position);
    }

    /**
     * Creates a new instance of the {@link LabeledComboBox} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Pos Position}.
     * @param items
     *            the initial items of the {@link ComboBox}.
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired {@link Pos Position} of the {@link Label}.
     */
    public LabeledComboBox(final ObservableList<T> items, final String labelText,
            final Pos position) {

        this(items, new Label(labelText), position);
    }

    /**
     * Creates a new instance of the {@link LabeledComboBox} class with the
     * provided {@link Label}, positioning the {@link Label} to the left of the
     * {@link TextField}.
     * 
     * @param label
     *            the {@link Label}.
     */
    public LabeledComboBox(final Label label) {

        this(label, Pos.CENTER_LEFT);
    }

    /**
     * Creates a new instance of the {@link LabeledComboBox} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Pos Position}.
     * 
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired {@link Pos Position} of the {@link Label}.
     */
    public LabeledComboBox(final Label label, final Pos position) {

        this(null, label, position);
    }

    /**
     * Creates a new instance of the {@link LabeledComboBox} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided
     * {@link Pos Position}.
     * @param items
     *            the initial items of the {@link ComboBox}.
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired
     *            {@link Pos Position} of the {@link Label}.
     */
    public LabeledComboBox(final ObservableList<T> items, final Label label,
            final Pos position) {

        super(items);

        this.label = new SimpleObjectProperty<>(label);
        this.labelPosition = new SimpleObjectProperty<>(position);

        this.addNode(label, position);

        if (label != null) {

            label.setLabelFor(this.getWrappedControl());
        }

        this.setUpEventHandlers();
    }

    private void setUpEventHandlers() {

        this.label.addListener((observable, oldValue, newValue) -> {

            if (oldValue != null) {

                this.getNodes().remove(oldValue);
            }
            if (newValue != null) {

                this.getLabel().setLabelFor(this.getWrappedControl());
            }

            this.addNode(newValue, this.getLabelPosition());
        });


        this.labelPosition.addListener((observable, oldValue, newValue) -> {

            this.removeNode(this.getLabel(), oldValue);

            if (newValue != null) {

                this.addNode(this.getLabel(), newValue);
            }
        });
    }
}
