package org.jutility.javafx.control.wrapper;


//@formatter:off
/*
* #%L
 * * jutility-javafx
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.StringConverter;


/**
 * The {@link ComboBoxWrapper} class wraps a {@link ComboBox} in a
 * {@link GridPane} with six surrounding {@link Node Nodes}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 * 
 * @param <T>
 *            the content type of the {@link ComboBox}.
 *
 */
public class ComboBoxWrapper<T>
        extends ComboBoxBaseWrapper<T, ComboBox<T>> {


    /**
     * The button cell is used to render what is shown in the ComboBox 'button'
     * area.
     * 
     * @return the buttonCell property.
     */
    public ObjectProperty<ListCell<T>> buttonCellProperty() {

        return this.getWrapped().buttonCellProperty();
    }

    /**
     * Providing a custom cell factory allows for complete customization of the
     * rendering of items in the ComboBox.
     * 
     * @return the cellFactory property.
     */
    public ObjectProperty<Callback<ListView<T>, ListCell<T>>> cellFactoryProperty() {

        return this.getWrapped().cellFactoryProperty();
    }

    /**
     * Converts the user-typed input (when the ComboBox is editable) to an
     * object of type T, such that the input may be retrieved via the value
     * property.
     * 
     * @return the converter property.
     */
    public ObjectProperty<StringConverter<T>> converterProperty() {

        return this.getWrapped().converterProperty();
    }

    /**
     * The editor for the ComboBox.
     * 
     * @return the editor property.
     */
    public ReadOnlyObjectProperty<TextField> editorProperty() {

        return this.getWrapped().editorProperty();
    }

    /**
     * The list of items to show within the ComboBox popup.
     * 
     * @return the items property.
     */
    public ObjectProperty<ObservableList<T>> itemsProperty() {

        return this.getWrapped().itemsProperty();
    }

    /**
     * This Node is shown to the user when the ComboBox has no content to show.
     * 
     * @return the placeholder property.
     */
    public ObjectProperty<Node> placeholderProperty() {

        return this.getWrapped().placeholderProperty();
    }

    /**
     * The selection model for the ComboBox.
     * 
     * @return the selectionModel property.
     */
    public ObjectProperty<SingleSelectionModel<T>> selectionModelProperty() {

        return this.getWrapped().selectionModelProperty();
    }


    /**
     * The maximum number of rows to be visible in the ComboBox popup when it is
     * showing.
     * 
     * @return the visibleRowCount property.
     */
    public IntegerProperty visibleRowCountProperty() {

        return this.getWrapped().visibleRowCountProperty();
    }


    /**
     * Creates a new instance of the {@link ComboBoxWrapper} class. <br/>
     * Internally, creates a default ComboBox instance with an empty items list
     * and default selection model.
     */
    public ComboBoxWrapper() {

        this(null);
    }

    /**
     * Creates a new instance of the {@link ComboBoxWrapper} class. <br/>
     * Internally, creates a default ComboBox instance with the provided items
     * list and a default selection model.
     * 
     * @param items
     *            the data model.
     */
    public ComboBoxWrapper(ObservableList<T> items) {

        super(items == null ? new ComboBox<T>() : new ComboBox<>(items));
    }


    /**
     * Gets the value of the property buttonCell.
     * 
     * @return the value of the property buttonCell.
     */
    public ListCell<T> getButtonCell() {

        return this.getWrapped().getButtonCell();
    }


    /**
     * Sets the value of the property buttonCell.
     * 
     * @param value
     *            the value of the property buttonCell.
     */
    public void setButtonCell(ListCell<T> value) {

        this.getWrapped().setButtonCell(value);
    }

    /**
     * Gets the value of the property cellFactory.
     * 
     * @return the value of the property cellFactory.
     */
    public Callback<ListView<T>, ListCell<T>> getCellFactory() {

        return this.getWrapped().getCellFactory();
    }



    /**
     * Sets the value of the property cellFactory.
     * 
     * @param value
     *            the value of the property cellFactory.
     */
    public void setCellFactory(Callback<ListView<T>, ListCell<T>> value) {

        this.getWrapped().setCellFactory(value);
    }

    /**
     * Gets the value of the property converter.
     * 
     * @return the value of the property converter.
     */
    public StringConverter<T> getConverter() {

        return this.getWrapped().getConverter();
    }



    /**
     * Sets the value of the property converter.
     * 
     * @param value
     *            the value of the property converter.
     */
    public void setConverter(StringConverter<T> value) {

        this.getWrapped().setConverter(value);
    }

    /**
     * Gets the value of the property editor.
     * 
     * @return the value of the property editor.
     */
    public TextField getEditor() {

        return this.getWrapped().getEditor();
    }

    /**
     * Gets the value of the property items.
     * 
     * @return the value of the property items.
     */
    public ObservableList<T> getItems() {

        return this.getWrapped().getItems();
    }



    /**
     * Sets the value of the property items.
     * 
     * @param value
     *            the value of the property items.
     */
    public void setItems(ObservableList<T> value) {

        this.getWrapped().setItems(value);
    }


    /**
     * Gets the value of the property placeholder.
     * 
     * @return the value of the property placeholder.
     */
    public Node getPlaceholder() {

        return this.getWrapped().getPlaceholder();
    }



    /**
     * Sets the value of the property placeholder.
     * 
     * @param value
     *            the value of the property placeholder.
     */
    public void setPlaceholder(Node value) {

        this.getWrapped().setPlaceholder(value);
    }

    /**
     * Gets the value of the property selectionModel.
     * 
     * @return the value of the property selectionModel.
     */
    public SingleSelectionModel<T> getSelectionModel() {

        return this.getWrapped().getSelectionModel();
    }


    /**
     * Sets the value of the property selectionModel.
     * 
     * @param value
     *            the value of the property selectionModel.
     */
    public void setSelectionModel(SingleSelectionModel<T> value) {

        this.getWrapped().setSelectionModel(value);
    }


    /**
     * Gets the value of the property visibleRowCount.
     * 
     * @return the value of the property visibleRowCount.
     */
    public int getVisibleRowCount() {

        return this.getWrapped().getVisibleRowCount();
    }


    /**
     * Sets the value of the property visibleRowCount.
     * 
     * @param value
     *            the value of the property visibleRowCount.
     */
    public void setVisibleRowCount(int value) {

        this.getWrapped().setVisibleRowCount(value);
    }



}
