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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.jutility.javafx.control.wrapper.TextFieldWrapper;


/**
 * The {@code LabeledTextField} class provides a {@link TextField} with a
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
     * Creates a new instance of the {@code LabeledTextField} class with the
     * provided label text, positioning the {@link Label} to the left of the
     * {@link TextField}.
     *
     * @param labelText
     *            the text of the {@link Label}.
     */
    public LabeledTextField(final String labelText) {

        this(labelText, Pos.CENTER_LEFT);
    }

    /**
     * Creates a new instance of the {@code LabeledTextField} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Pos Position}.
     *
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired {@link Pos Position} of the {@link Label}.
     */
    public LabeledTextField(final String labelText, final Pos position) {

        this(labelText, position, null);
    }

    /**
     * Creates a new instance of the {@code LabeledTextField} class with the
     * provided label text, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Pos Position}.
     *
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired {@link Pos Position} of the {@link Label}.
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public LabeledTextField(final String labelText, final Pos position,
            final String text) {

        this(new Label(labelText), position, text);
    }

    /**
     * Creates a new instance of the {@code LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} to the left of the
     * {@link TextField}.
     *
     * @param label
     *            the {@link Label}.
     */
    public LabeledTextField(final Label label) {

        this(label, Pos.CENTER_LEFT);
    }

    /**
     * Creates a new instance of the {@code LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Pos Position}.
     *
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired {@link Pos Position} of the {@link Label}.
     */
    public LabeledTextField(final Label label, final Pos position) {

        this(label, position, null);
    }

    /**
     * Creates a new instance of the {@code LabeledTextField} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided {@link Pos Position}.
     *
     * @param label
     *            the {@link Label}.
     * @param position
     *            the desired {@link Pos Position} of the {@link Label}.
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public LabeledTextField(final Label label, final Pos position,
            final String text) {

        super(text);

        this.label = new SimpleObjectProperty<>(label);
        this.labelPosition = new SimpleObjectProperty<>(position);

        this.addNode(label, position);

        if (label != null) {

            label.setLabelFor(this.getWrappedControl());
        }

        this.setupEventHandlers();
    }

    private void setupEventHandlers() {

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

        this.wrappedControlProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if ((this.getLabel() != null) && (newValue != null)) {

                        this.getLabel().setLabelFor(newValue);
                    }
                });
    }

}
