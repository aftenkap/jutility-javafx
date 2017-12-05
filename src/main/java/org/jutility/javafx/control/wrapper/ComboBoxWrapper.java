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
 * The {@code ComboBoxWrapper} class wraps a {@link ComboBox} in a
 * {@link GridPane} with six surrounding {@link Node Nodes}.
 * 
 * @param <T>
 *            the content type of the {@link ComboBox}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
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

        return this.getWrappedControl().buttonCellProperty();
    }

    /**
     * Providing a custom cell factory allows for complete customization of the
     * rendering of items in the ComboBox.
     * 
     * @return the cellFactory property.
     */
    public ObjectProperty<Callback<ListView<T>, ListCell<T>>> cellFactoryProperty() {

        return this.getWrappedControl().cellFactoryProperty();
    }

    /**
     * Converts the user-typed input (when the ComboBox is editable) to an
     * object of type T, such that the input may be retrieved via the value
     * property.
     * 
     * @return the converter property.
     */
    public ObjectProperty<StringConverter<T>> converterProperty() {

        return this.getWrappedControl().converterProperty();
    }

    /**
     * The editor for the ComboBox.
     * 
     * @return the editor property.
     */
    public ReadOnlyObjectProperty<TextField> editorProperty() {

        return this.getWrappedControl().editorProperty();
    }

    /**
     * The list of items to show within the ComboBox popup.
     * 
     * @return the items property.
     */
    public ObjectProperty<ObservableList<T>> itemsProperty() {

        return this.getWrappedControl().itemsProperty();
    }

    /**
     * This Node is shown to the user when the ComboBox has no content to show.
     * 
     * @return the placeholder property.
     */
    public ObjectProperty<Node> placeholderProperty() {

        return this.getWrappedControl().placeholderProperty();
    }

    /**
     * The selection model for the ComboBox.
     * 
     * @return the selectionModel property.
     */
    public ObjectProperty<SingleSelectionModel<T>> selectionModelProperty() {

        return this.getWrappedControl().selectionModelProperty();
    }


    /**
     * The maximum number of rows to be visible in the ComboBox popup when it is
     * showing.
     * 
     * @return the visibleRowCount property.
     */
    public IntegerProperty visibleRowCountProperty() {

        return this.getWrappedControl().visibleRowCountProperty();
    }


    /**
     * Creates a new instance of the {@code ComboBoxWrapper} class.
     * <p>
     * Internally, creates a default ComboBox instance with an empty items list
     * and default selection model.
     * </p>
     */
    public ComboBoxWrapper() {

        this(null);
    }

    /**
     * Creates a new instance of the {@code ComboBoxWrapper} class.
     * <p>
     * Internally, creates a default ComboBox instance with the provided items
     * list and a default selection model.
     * </p>
     * 
     * @param items
     *            the data model.
     */
    public ComboBoxWrapper(ObservableList<T> items) {

        super(items == null ? new ComboBox<>() : new ComboBox<>(items));
    }


    /**
     * Gets the value of the property buttonCell.
     * 
     * @return the value of the property buttonCell.
     */
    public ListCell<T> getButtonCell() {

        return this.getWrappedControl().getButtonCell();
    }


    /**
     * Sets the value of the property buttonCell.
     * 
     * @param value
     *            the value of the property buttonCell.
     */
    public void setButtonCell(ListCell<T> value) {

        this.getWrappedControl().setButtonCell(value);
    }

    /**
     * Gets the value of the property cellFactory.
     * 
     * @return the value of the property cellFactory.
     */
    public Callback<ListView<T>, ListCell<T>> getCellFactory() {

        return this.getWrappedControl().getCellFactory();
    }



    /**
     * Sets the value of the property cellFactory.
     * 
     * @param value
     *            the value of the property cellFactory.
     */
    public void setCellFactory(Callback<ListView<T>, ListCell<T>> value) {

        this.getWrappedControl().setCellFactory(value);
    }

    /**
     * Gets the value of the property converter.
     * 
     * @return the value of the property converter.
     */
    public StringConverter<T> getConverter() {

        return this.getWrappedControl().getConverter();
    }



    /**
     * Sets the value of the property converter.
     * 
     * @param value
     *            the value of the property converter.
     */
    public void setConverter(StringConverter<T> value) {

        this.getWrappedControl().setConverter(value);
    }

    /**
     * Gets the value of the property editor.
     * 
     * @return the value of the property editor.
     */
    public TextField getEditor() {

        return this.getWrappedControl().getEditor();
    }

    /**
     * Gets the value of the property items.
     * 
     * @return the value of the property items.
     */
    public ObservableList<T> getItems() {

        return this.getWrappedControl().getItems();
    }



    /**
     * Sets the value of the property items.
     * 
     * @param value
     *            the value of the property items.
     */
    public void setItems(ObservableList<T> value) {

        this.getWrappedControl().setItems(value);
    }


    /**
     * Gets the value of the property placeholder.
     * 
     * @return the value of the property placeholder.
     */
    public Node getPlaceholder() {

        return this.getWrappedControl().getPlaceholder();
    }



    /**
     * Sets the value of the property placeholder.
     * 
     * @param value
     *            the value of the property placeholder.
     */
    public void setPlaceholder(Node value) {

        this.getWrappedControl().setPlaceholder(value);
    }

    /**
     * Gets the value of the property selectionModel.
     * 
     * @return the value of the property selectionModel.
     */
    public SingleSelectionModel<T> getSelectionModel() {

        return this.getWrappedControl().getSelectionModel();
    }


    /**
     * Sets the value of the property selectionModel.
     * 
     * @param value
     *            the value of the property selectionModel.
     */
    public void setSelectionModel(SingleSelectionModel<T> value) {

        this.getWrappedControl().setSelectionModel(value);
    }


    /**
     * Gets the value of the property visibleRowCount.
     * 
     * @return the value of the property visibleRowCount.
     */
    public int getVisibleRowCount() {

        return this.getWrappedControl().getVisibleRowCount();
    }


    /**
     * Sets the value of the property visibleRowCount.
     * 
     * @param value
     *            the value of the property visibleRowCount.
     */
    public void setVisibleRowCount(int value) {

        this.getWrappedControl().setVisibleRowCount(value);
    }
}
