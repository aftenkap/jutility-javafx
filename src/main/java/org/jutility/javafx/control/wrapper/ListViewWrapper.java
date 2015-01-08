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

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.StringConverter;


/**
 * The {@link ListViewWrapper} class wraps a {@link ListView} in a
 * {@link GridPane} with six surrounding {@link Node Nodes}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 * 
 * @param <T>
 *            the content type of the {@link ListView}.
 */
public class ListViewWrapper<T>
        extends ControlWrapper<ListView<T>> {

    private final ObjectProperty<StringConverter<T>> converterProperty;

    /**
     * Returns the converter property.
     * 
     * @return the converter property.
     */
    public ObjectProperty<StringConverter<T>> converterProperty() {

        return this.converterProperty;
    }

    /**
     * Returns the value of the converter property.
     * 
     * @return the value of the converter property.
     */
    public StringConverter<T> getConverter() {

        return this.converterProperty.get();
    }

    /**
     * Sets the value of the converter property.
     * 
     * @param value
     *            the value of the converter property.
     */
    public void setConverter(final StringConverter<T> value) {

        this.converterProperty.set(value);
    }

    /**
     * The underlying data model for the ListView. Note that it has a generic
     * type that must match the type of the ListView itself.
     * 
     * @return the items property.
     */
    public ObjectProperty<ObservableList<T>> itemsProperty() {

        return this.getWrapped().itemsProperty();
    }

    /**
     * This Node is shown to the user when the {@link ListView} has no content
     * to show. This may be the case because the table model has no data in the
     * first place or that a filter has been applied to the list model,
     * resulting in there being nothing to show the user.
     * 
     * @return the placeholder property.
     */
    public final ObjectProperty<Node> placeholderProperty() {

        return this.getWrapped().placeholderProperty();
    }

    /**
     * The SelectionModel provides the API through which it is possible to
     * select single or multiple items within a ListView, as well as inspect
     * which items have been selected by the user. Note that it has a generic
     * type that must match the type of the ListView itself.
     * 
     * @return the selection model property.
     */
    public final ObjectProperty<MultipleSelectionModel<T>> selectionModelProperty() {

        return this.getWrapped().selectionModelProperty();
    }

    /**
     * The FocusModel provides the API through which it is possible to both get
     * and set the focus on a single item within a ListView. Note that it has a
     * generic type that must match the type of the ListView itself.
     * 
     * @return the focus model property.
     */
    public final ObjectProperty<FocusModel<T>> focusModelProperty() {

        return this.getWrapped().focusModelProperty();
    }

    /**
     * The orientation of the ListView - this can either be horizontal or
     * vertical.
     * 
     * @return the orientatio property.
     */
    public final ObjectProperty<Orientation> orientationProperty() {

        return this.getWrapped().orientationProperty();
    }

    /**
     * Setting a custom cell factory has the effect of deferring all cell
     * creation, allowing for total customization of the cell. Internally, the
     * ListView is responsible for reusing ListCells - all that is necessary is
     * for the custom cell factory to return from this function a ListCell which
     * might be usable for representing any item in the ListView.
     * 
     * Refer to the Cell class documentation for more detail.
     * 
     * @return the cell factory property.
     */
    public final ObjectProperty<Callback<ListView<T>, ListCell<T>>> cellFactoryProperty() {

        return this.getWrapped().cellFactoryProperty();
    }



    /**
     * Specifies whether this control has cells that are a fixed height (of the
     * specified value). If this value is less than or equal to zero, then all
     * cells are individually sized and positioned. This is a slow operation.
     * Therefore, when performance matters and developers are not dependent on
     * variable cell sizes it is a good idea to set the fixed cell size value.
     * Generally cells are around 24px, so setting a fixed cell size of 24 is
     * likely to result in very little difference in visuals, but a improvement
     * to performance. To set this property via CSS, use the -fx-fixed-cell-size
     * property. This should not be confused with the -fx-cell-size property.
     * The difference between these two CSS properties is that -fx-cell-size
     * will size all cells to the specified size, but it will not enforce that
     * this is the only size (thus allowing for variable cell sizes, and
     * preventing the performance gains from being possible). Therefore, when
     * performance matters use -fx-fixed-cell-size, instead of -fx-cell-size. If
     * both properties are specified in CSS, -fx-fixed-cell-size takes
     * precedence.
     * 
     * @return the fixedCellSize property
     */
    public final DoubleProperty fixedCellSizeProperty() {

        return this.getWrapped().fixedCellSizeProperty();
    }

    /**
     * Specifies whether this ListView is editable - only if the ListView and
     * the ListCells within it are both editable will a ListCell be able to go
     * into their editing state.
     * 
     * @return the editable property.
     */
    public final BooleanProperty editableProperty() {

        return this.getWrapped().editableProperty();
    }

    /**
     * A property used to represent the index of the item currently being edited
     * in the ListView, if editing is taking place, or -1 if no item is being
     * edited.
     * 
     * It is not possible to set the editing index, instead it is required that
     * you call edit(int).
     * 
     * @return the editingIndex property.
     */
    public final ReadOnlyIntegerProperty editingIndexProperty() {

        return this.getWrapped().editingIndexProperty();
    }


    /**
     * This event handler will be fired when the user successfully initiates
     * editing.
     * 
     * @return the onEditStart property.
     */
    public final ObjectProperty<EventHandler<ListView.EditEvent<T>>> onEditStartProperty() {

        return this.getWrapped().onEditStartProperty();
    }

    /**
     * This property is used when the user performs an action that should result
     * in their editing input being persisted.
     * 
     * The EventHandler in this property should not be called directly - instead
     * call ListCell.commitEdit(java.lang.Object) from within your custom
     * ListCell. This will handle firing this event, updating the view, and
     * switching out of the editing state.
     * 
     * @return the onEditCommit property.
     */
    public final ObjectProperty<EventHandler<ListView.EditEvent<T>>> onEditCommitProperty() {

        return this.getWrapped().onEditCommitProperty();
    }

    /**
     * This event handler will be fired when the user cancels editing a cell.
     * 
     * @return the onEditCancel property.
     */
    public final ObjectProperty<EventHandler<ListView.EditEvent<T>>> onEditCancelProperty() {

        return this.getWrapped().onEditCancelProperty();
    }

    /**
     * Called when there's a request to scroll an index into view using
     * scrollTo(int) or #scrollTo(S)
     * 
     * @return the onScrollTo property.
     */
    public ObjectProperty<EventHandler<ScrollToEvent<Integer>>> onScrollToProperty() {

        return this.getWrapped().onScrollToProperty();
    }



    /**
     * Creates a new instance of the {@link ListViewWrapper} class. <br>
     * </br> Internally, creates a default ListView which will display contents
     * stacked vertically. As no ObservableList is provided in this constructor,
     * an empty ObservableList is created, meaning that it is legal to directly
     * call getItems() if so desired. However, as noted elsewhere, this is not
     * the recommended approach (instead call
     * setItems(javafx.collections.ObservableList)). Refer to the ListView class
     * documentation for details on the default state of other properties.
     */
    public ListViewWrapper() {

        this(null);
    }

    /**
     * Creates a new instance of the {@link ListViewWrapper} class.
     * 
     * Internally, creates a default ListView which will stack the contents
     * retrieved from the provided ObservableList vertically. Attempts to add a
     * listener to the ObservableList, such that all subsequent changes inside
     * the list will be shown to the user.
     * 
     * Refer to the ListView class documentation for details on the default
     * state of other properties.
     * 
     * @param items
     *            the initial list of items presented by the {@link ListView}.
     */
    public ListViewWrapper(final ObservableList<T> items) {

        super(new ListView<>(items));

        this.converterProperty = new SimpleObjectProperty<>();
    }


    /**
     * Creates a new instance of the {@link ListViewWrapper} class.
     * 
     * Internally, creates a default ListView which will stack the contents
     * retrieved from the provided ObservableList vertically. Attempts to add a
     * listener to the ObservableList, such that all subsequent changes inside
     * the list will be shown to the user.
     * 
     * Refer to the ListView class documentation for details on the default
     * state of other properties.
     * 
     * @param items
     *            the initial list of items presented by the {@link ListView}.
     * @param converter
     *            the initial string converter.
     */
    public ListViewWrapper(final ObservableList<T> items,
            final StringConverter<T> converter) {

        super(items == null ? new ListView<>() : new ListView<>(items));

        this.converterProperty = new SimpleObjectProperty<>(converter);

        this.setupEventHandlers();
    }

    /**
     * Sets the underlying data model for the ListView. Note that it has a
     * generic type that must match the type of the ListView itself.
     * 
     * @param value
     *            the data model.
     */
    public void setItems(ObservableList<T> value) {

        this.getWrapped().setItems(value);
    }

    /**
     * Returns an ObservableList that contains the items currently being shown
     * to the user. This may be null if
     * setItems(javafx.collections.ObservableList) has previously been called,
     * however, by default it is an empty ObservableList.
     * 
     * @return An ObservableList containing the items to be shown to the user,
     *         or null if the items have previously been set to null.
     */
    public ObservableList<T> getItems() {

        return this.getWrapped().getItems();
    }


    /**
     * Sets the value of the property placeholder.
     * 
     * @param value
     *            the placeholder.
     */
    public final void setPlaceholder(Node value) {

        this.getWrapped().setPlaceholder(value);
    }

    /**
     * Gets the value of the property placeholder.
     * 
     * @return the placeholder.
     */
    public final Node getPlaceholder() {

        return this.getWrapped().getPlaceholder();
    }

    /**
     * Sets the MultipleSelectionModel to be used in the ListView. Despite a
     * ListView requiring a MultipleSelectionModel, it is possible to configure
     * it to only allow single selection (see
     * MultipleSelectionModel.setSelectionMode
     * (javafx.scene.control.SelectionMode) for more information).
     * 
     * @param value
     *            the selection model.
     */
    public final void setSelectionModel(MultipleSelectionModel<T> value) {

        this.getWrapped().setSelectionModel(value);
    }

    /**
     * Returns the currently installed selection model.
     * 
     * @return the selection model.
     */
    public final MultipleSelectionModel<T> getSelectionModel() {

        return this.getWrapped().getSelectionModel();
    }

    /**
     * Sets the FocusModel to be used in the ListView.
     * 
     * @param value
     *            the focus model.
     */
    public final void setFocusModel(FocusModel<T> value) {

        this.getWrapped().setFocusModel(value);
    }

    /**
     * Returns the currently installed FocusModel.
     * 
     * @return the focus model.
     */
    public final FocusModel<T> getFocusModel() {

        return this.getWrapped().getFocusModel();
    }

    /**
     * Sets the orientation of the ListView, which dictates whether it scrolls
     * vertically or horizontally.
     * 
     * @param value
     *            the orientation.
     */
    public final void setOrientation(Orientation value) {

        this.getWrapped().setOrientation(value);
    }

    /**
     * Returns the current orientation of the ListView, which dictates whether
     * it scrolls vertically or horizontally.
     * 
     * @return the orientation.
     */
    public final Orientation getOrientation() {

        return this.getWrapped().getOrientation();
    }

    /**
     * Sets a new cell factory to use in the ListView. This forces all old
     * ListCell's to be thrown away, and new ListCell's created with the new
     * cell factory.
     * 
     * @param value
     *            the cell factory.
     */
    public final void setCellFactory(Callback<ListView<T>, ListCell<T>> value) {

        this.getWrapped().setCellFactory(value);
    }

    /**
     * Returns the current cell factory.
     * 
     * @return the cell factory.
     */
    public final Callback<ListView<T>, ListCell<T>> getCellFactory() {

        return this.getWrapped().getCellFactory();
    }

    /**
     * Sets the new fixed cell size for this control. Any value greater than
     * zero will enable fixed cell size mode, whereas a zero or negative value
     * (or Region.USE_COMPUTED_SIZE) will be used to disabled fixed cell size
     * mode.
     * 
     * @param value
     *            The new fixed cell size value, or a value less than or equal
     *            to zero (or Region.USE_COMPUTED_SIZE) to disable.
     */
    public final void setFixedCellSize(double value) {

        this.getWrapped().setFixedCellSize(value);
    }

    /**
     * Returns the fixed cell size value. A value less than or equal to zero is
     * used to represent that fixed cell size mode is disabled, and a value
     * greater than zero represents the size of all cells in this control.
     * 
     * @return A double representing the fixed cell size of this control, or a
     *         value less than or equal to zero if fixed cell size mode is
     *         disabled.
     */
    public final double getFixedCellSize() {

        return this.getWrapped().getFixedCellSize();
    }

    /**
     * Sets the value of the property editable.
     * 
     * @param value
     *            whether or not the {@link ListView} is editable.
     */
    public final void setEditable(boolean value) {

        this.getWrapped().setEditable(value);
    }

    /**
     * Gets the value of the property editable.
     * 
     * @return whether or not the {@link ListView} is editable.
     */
    public final boolean isEditable() {

        return this.getWrapped().isEditable();
    }

    /**
     * Returns the index of the item currently being edited in the ListView, or
     * -1 if no item is being edited.
     * 
     * @return the editing index.
     */
    public final int getEditingIndex() {

        return this.getWrapped().getEditingIndex();
    }

    /**
     * Sets the EventHandler that will be called when the user begins an edit.
     * This is a convenience method - the same result can be achieved by calling
     * addEventHandler(ListView.EDIT_START_EVENT, eventHandler).
     * 
     * @param value
     *            the onEditStart event handler.
     */
    public final void setOnEditStart(EventHandler<ListView.EditEvent<T>> value) {

        this.getWrapped().setOnEditStart(value);
    }


    /**
     * Returns the EventHandler that will be called when the user begins an
     * edit.
     * 
     * @return the onEditStart event handler.
     */
    public final EventHandler<ListView.EditEvent<T>> getOnEditStart() {

        return this.getWrapped().getOnEditStart();
    }

    /**
     * Sets the EventHandler that will be called when the user has completed
     * their editing. This is called as part of the
     * ListCell.commitEdit(java.lang.Object) method. This is a convenience
     * method - the same result can be achieved by calling
     * addEventHandler(ListView.EDIT_START_EVENT, eventHandler).
     * 
     * @param value
     *            the onEditCommit event handler.
     */
    public final void setOnEditCommit(EventHandler<ListView.EditEvent<T>> value) {

        this.getWrapped().setOnEditCommit(value);
    }


    /**
     * Returns the EventHandler that will be called when the user commits an
     * edit.
     * 
     * @return the onEditCommit event handler.
     */
    public final EventHandler<ListView.EditEvent<T>> getOnEditCommit() {

        return this.getWrapped().getOnEditCommit();
    }

    /**
     * Sets the EventHandler that will be called when the user cancels an edit.
     * 
     * @param value
     *            the onEditCancel event handler.
     */
    public final void setOnEditCancel(EventHandler<ListView.EditEvent<T>> value) {

        this.getWrapped().setOnEditCancel(value);
    }

    /**
     * Returns the EventHandler that will be called when the user cancels an
     * edit.
     * 
     * @return the onEditCancel event handler.
     */
    public final EventHandler<ListView.EditEvent<T>> getOnEditCancel() {

        return this.getWrapped().getOnEditCancel();
    }

    /**
     * Instructs the ListView to begin editing the item in the given index, if
     * the ListView is editable. Once this method is called, if the current
     * cellFactoryProperty() is set up to support editing, the Cell will switch
     * its visual state to enable for user input to take place.
     * 
     * @param itemIndex
     *            itemIndex - The index of the item in the ListView that should
     *            be edited.
     */
    public void edit(int itemIndex) {

        this.getWrapped().edit(itemIndex);
    }

    /**
     * Scrolls the ListView such that the item in the given index is visible to
     * the end user.
     * 
     * @param index
     *            The index that should be made visible to the user, assuming of
     *            course that it is greater than, or equal to 0, and less than
     *            the size of the items list contained within the given
     *            ListView.
     */
    public void scrollTo(int index) {

        this.getWrapped().scrollTo(index);
    }

    /**
     * Scrolls the TableView so that the given object is visible within the
     * viewport.
     * 
     * @param object
     *            The object that should be visible to the user.
     */
    public void scrollTo(T object) {

        this.getWrapped().scrollTo(object);
    }

    /**
     * Sets the value of the property onScrollTo.
     * 
     * @param value
     */
    public void setOnScrollTo(EventHandler<ScrollToEvent<Integer>> value) {

        this.getWrapped().setOnScrollTo(value);
    }

    /**
     * Gets the value of the property onScrollTo.
     * 
     * @return the onScrollTo event handler
     */
    public EventHandler<ScrollToEvent<Integer>> getOnScrollTo() {

        return this.getWrapped().getOnScrollTo();
    }

    /**
     * The CssMetaData associated with this class, which may include the
     * CssMetaData of its super classes.
     * 
     * @return the CssMetaData associated with this class
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {

        return ListView.getClassCssMetaData();
    }

    /**
     * Returns an unmodifiable list of the controls css styleable properties
     * 
     * @return an unmodifiable list of the controls css styleable properties
     */
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {

        return this.getWrapped().getControlCssMetaData();
    }

    private void setupEventHandlers() {

        this.converterProperty().addListener(
                (observable, oldValue, newValue) -> {

                    this.updateCellFactory();
                });
    }

    private void updateCellFactory() {

        this.setCellFactory((param) -> {

            return new TextFieldListCell<>(this.getConverter());
        });
    }
}
