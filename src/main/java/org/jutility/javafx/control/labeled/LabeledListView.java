package org.jutility.javafx.control.labeled;


//@formatter:off
/*
* #%L 
* jutility-javafx 
* %% 
* Copyright (C) 2013 - 2014 jutility.org 
* %% 
* Licensed under the Apache License, Version 2.0 (the "License"); you may not 
* use this file except in compliance with the License. You may obtain a copy of 
* the License at
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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import org.jutility.javafx.control.wrapper.ListViewWrapper;


/**
 * The {@link LabeledListView} class provides a {@link ListView} with a freely
 * positionable {@link Label}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 *
 * @param <T>
 *            the content type of the {@link ListView}.
 */
public class LabeledListView<T>
        extends ListViewWrapper<T>
        implements ILabeledControl {

    private final ObjectProperty<Label>    labelProperty;

    private final ObjectProperty<Position> labelPositionProperty;


    @Override
    public ObjectProperty<Label> labelProperty() {

        return this.labelProperty;
    }

    @Override
    public Label getLabel() {

        return this.labelProperty.get();
    }

    @Override
    public void setLabel(final Label label) {

        this.labelProperty.set(label);
    }

    @Override
    public ObjectProperty<Position> labelPositionProperty() {

        return this.labelPositionProperty;
    }

    @Override
    public Position getLabelPosition() {

        return this.labelPositionProperty.get();
    }

    @Override
    public void setLabelPosition(final Position labelPosition) {

        this.labelPositionProperty.set(labelPosition);
    }

    /**
     * Creates a new instance of the {@link LabeledListView} class with the
     * provided labelProperty text, positioning the {@link Label} to the left of
     * the {@link TextField}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     */
    public LabeledListView(final String labelText) {

        this(labelText, Position.WEST);
    }

    /**
     * Creates a new instance of the {@link LabeledListView} class with the
     * provided labelProperty text, positioning the {@link Label} relative to
     * the {@link TextField} according to the provided
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
    public LabeledListView(final String labelText, final Position position) {

        this(null, labelText, position);
    }

    /**
     * Creates a new instance of the {@link LabeledListView} class with the
     * provided labelProperty text, positioning the {@link Label} relative to
     * the {@link TextField} according to the provided
     * {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     * Position}.
     * 
     * @param items
     *            the initial items of the {@link ListView}.
     * @param labelText
     *            the text of the {@link Label}.
     * @param position
     *            the desired
     *            {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     *            Position} of the {@link Label}.
     */
    public LabeledListView(final ObservableList<T> items,
            final String labelText, final Position position) {

        this(items, null, labelText, position);
    }



    /**
     * Creates a new instance of the {@link LabeledListView} class with the
     * provided {@link Label}, positioning the {@link Label} relative to the
     * {@link TextField} according to the provided
     * {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     * Position}.
     * 
     * @param items
     *            the initial items of the {@link ListView}.
     * @param converter
     *            the string converter of the {@link ListView}.
     * @param labelText
     *            the text for the {@link Label}.
     * @param position
     *            the desired
     *            {@link org.jutility.javafx.control.wrapper.ControlWrapper.Position
     *            Position} of the {@link Label}.
     */
    public LabeledListView(final ObservableList<T> items,
            final StringConverter<T> converter, final String labelText,
            final Position position) {

        super(items, converter);

        Label label = null;

        if (labelText != null && !labelText.isEmpty()) {

            label = new Label(labelText);
            this.addNode(label, position);
            label.setLabelFor(this.getWrapped());

        }

        this.labelProperty = new SimpleObjectProperty<>(label);
        this.labelPositionProperty = new SimpleObjectProperty<>(position);


        this.setUpEventHandlers();
    }

    private void setUpEventHandlers() {

        this.labelProperty.addListener((observable, oldValue, newValue) -> {

            if (oldValue != null) {

                this.getNodes().remove(oldValue);
            }
            if (newValue != null) {

                this.getLabel().setLabelFor(this.getWrapped());
            }

            this.addNode(newValue, this.getLabelPosition());
        });


        this.labelPositionProperty
                .addListener((observable, oldValue, newValue) -> {

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
