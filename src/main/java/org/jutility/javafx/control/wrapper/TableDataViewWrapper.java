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


import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.util.StringConverter;

import org.jutility.common.datatype.table.ITable;
import org.jutility.javafx.control.TableDataView;



/**
 * The {@code TableDataViewWrapper} class provides a {@link ControlWrapper
 * Wrapper} around a {@link TableDataView}.
 *
 * @param <T>
 *            the content type of the {@link ITable}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.1
 */
public class TableDataViewWrapper<T>
        extends ControlWrapper<TableDataView<T>> {


    /**
     * Returns the {@link ITable Table} property.
     *
     * @see TableDataViewWrapper#getTable()
     * @see TableDataViewWrapper#setTable(ITable)
     * @return the table property.
     */
    public ObjectProperty<ITable<T>> tableProperty() {

        return this.getWrappedControl().tableProperty();
    }

    /**
     * Returns the value of the {@link ITable Table} property.
     *
     * @return the value of the {@link ITable Table} property.
     */
    public ITable<T> getTable() {

        return this.getWrappedControl().getTable();
    }

    /**
     * Sets the value of the {@link ITable Table} property.
     *
     * @param table
     *            the value of the {@link ITable Table} property.
     */
    public void setTable(final ITable<T> table) {

        this.getWrappedControl().setTable(table);
    }


    /**
     * Returns the {@link StringConverter Converter} property.
     *
     * @see TableDataViewWrapper#getConverter()
     * @see TableDataViewWrapper#setConverter(StringConverter)
     *
     * @return the {@link StringConverter Converter} property.
     */
    public ObjectProperty<StringConverter<T>> converterProperty() {

        return this.getWrappedControl().converterProperty();
    }

    /**
     * Returns the value of the {@link StringConverter Converter} property.
     *
     * @return the value of the {@link StringConverter Converter} property.
     */
    public StringConverter<T> getConverter() {

        return this.getWrappedControl().getConverter();
    }

    /**
     * Sets the value of the {@link StringConverter Converter} property.
     *
     * @param value
     *            the value of the {@link StringConverter Converter} property.
     */
    public void setConverter(final StringConverter<T> value) {

        this.getWrappedControl().setConverter(value);
    }


    /**
     * This controls whether a menu button is available when the user clicks in
     * a designated space within the TableView, within which is a radio menu
     * item for each TableColumn in this table. This menu allows for the user to
     * show and hide all TableColumns easily.
     *
     * @see TableDataViewWrapper#isTableMenuButtonVisible()
     * @see TableDataViewWrapper#setTableMenuButtonVisible(boolean)
     *
     * @return the table menu button visible property.
     */
    public final BooleanProperty tableMenuButtonVisibleProperty() {

        return this.getWrappedControl().tableMenuButtonVisibleProperty();
    }

    /**
     * Sets the value of the table menu button visible property.
     *
     * @param value
     *            the value of the table menu button visible property.
     */
    public final void setTableMenuButtonVisible(final boolean value) {

        this.getWrappedControl().setTableMenuButtonVisible(value);
    }

    /**
     * Gets the value of the table menu button visible property.
     *
     * @return the value of the table menu button visible property.
     */
    public final boolean isTableMenuButtonVisible() {

        return this.getWrappedControl().isTableMenuButtonVisible();
    }


    /**
     * This is the function called when the user completes a column-resize
     * operation. The two most common policies are available as static functions
     * in the TableView class: {@link TableView#UNCONSTRAINED_RESIZE_POLICY
     * UNCONSTRAINED_RESIZE_POLICY} and
     * {@link TableView#CONSTRAINED_RESIZE_POLICY CONSTRAINED_RESIZE_POLICY}.
     *
     * @see #getColumnResizePolicy()
     * @see #setColumnResizePolicy(Callback)
     *
     * @return the column resize policy property.
     */
    @SuppressWarnings("rawtypes")
    public final ObjectProperty<Callback<TableView.ResizeFeatures, java.lang.Boolean>> columnResizePolicyProperty() {

        return this.getWrappedControl().columnResizePolicyProperty();
    }

    /**
     * Sets the value of the column resize policy property.
     *
     * @param callback
     *            the value of the column resize policy property.
     */
    public final void setColumnResizePolicy(
            @SuppressWarnings("rawtypes") final Callback<TableView.ResizeFeatures, java.lang.Boolean> callback) {

        this.getWrappedControl().setColumnResizePolicy(callback);
    }

    /**
     * Gets the value of the column resize policy property.
     *
     * @return the value of the column resize policy property.
     */
    @SuppressWarnings("rawtypes")
    public final Callback<TableView.ResizeFeatures, java.lang.Boolean> getColumnResizePolicy() {

        return this.getWrappedControl().getColumnResizePolicy();
    }


    /**
     * A function which produces a TableRow. The system is responsible for
     * reusing TableRows. Return from this function a TableRow which might be
     * usable for representing a single row in a TableView. Note that a TableRow
     * is not a TableCell. A TableRow is simply a container for a TableCell, and
     * in most circumstances it is more likely that you'll want to create custom
     * TableCells, rather than TableRows. The primary use case for creating
     * custom TableRow instances would most probably be to introduce some form
     * of column spanning support. <br>
     * You can create custom TableCell instances per column by assigning the
     * appropriate function to the cellFactory property in the TableColumn
     * class.
     *
     * @see #getRowFactory()
     * @see #setRowFactory(Callback)
     *
     * @return the row factory property
     */
    public final ObjectProperty<Callback<TableView<List<T>>, TableRow<List<T>>>> rowFactoryProperty() {

        return this.getWrappedControl().rowFactoryProperty();
    }

    /**
     * Sets the value of the row factory property.
     *
     * @param value
     *            the value of the row factory property.
     */
    public final void setRowFactory(
            final Callback<TableView<List<T>>, TableRow<List<T>>> value) {

        this.getWrappedControl().setRowFactory(value);
    }

    /**
     * Gets the value of the row factory property.
     *
     * @return the value of the row factory property.
     */
    public final Callback<TableView<List<T>>, TableRow<List<T>>> getRowFactory() {

        return this.getWrappedControl().getRowFactory();
    }


    /**
     * This Node is shown to the user when the table has no content to show.
     * This may be the case because the table model has no data in the first
     * place, that a filter has been applied to the table model, resulting in
     * there being nothing to show the user, or that there are no currently
     * visible columns.
     *
     * @see #getPlaceholder()
     * @see #setPlaceholder(Node)
     * @return the placeholder property.
     */
    public final ObjectProperty<Node> placeholderProperty() {

        return this.getWrappedControl().placeholderProperty();
    }

    /**
     * Sets the value of the placeholder property.
     *
     * @param value
     *            the value of the placeholder property.
     */
    public final void setPlaceholder(final Node value) {

        this.getWrappedControl().setPlaceholder(value);
    }

    /**
     * Gets the value of the placeholder property.
     *
     * @return the value of the placeholder property.
     */
    public final Node getPlaceholder() {

        return this.getWrappedControl().getPlaceholder();
    }


    /**
     * The SelectionModel provides the API through which it is possible to
     * select single or multiple items within a TableView, as well as inspect
     * which items have been selected by the user. Note that it has a generic
     * type that must match the type of the TableView itself.
     *
     * @see TableDataViewWrapper#getSelectionModel()
     * @return the selection model property.
     */
    public final ObjectProperty<TableView.TableViewSelectionModel<List<T>>> selectionModelProperty() {

        return this.getWrappedControl().selectionModelProperty();
    }

    /**
     * Sets the value of the selection model property.
     *
     * @param value
     *            the value of the selection model property.
     */
    public final void setSelectionModel(
            final TableView.TableViewSelectionModel<List<T>> value) {

        this.getWrappedControl().setSelectionModel(value);
    }

    /**
     * Gets the value of the selection model property.
     *
     * @return the value of the selection model property.
     */
    public final TableView.TableViewSelectionModel<List<T>> getSelectionModel() {

        return this.getWrappedControl().getSelectionModel();
    }


    /**
     * Represents the currently-installed TableView.TableViewFocusModel for this
     * TableView. Under almost all circumstances leaving this as the default
     * focus model will suffice.
     *
     * @see TableDataViewWrapper#getFocusModel()
     * @return the focus model property.
     */
    public final ObjectProperty<TableView.TableViewFocusModel<List<T>>> focusModelProperty() {

        return this.getWrappedControl().focusModelProperty();
    }

    /**
     * Sets the value of the focus model property.
     *
     * @param value
     *            the value of the focus model property.
     */
    public final void setFocusModel(
            final TableView.TableViewFocusModel<List<T>> value) {

        this.getWrappedControl().setFocusModel(value);
    }

    /**
     * Gets the value of the focus model property.
     *
     * @return the value of the focus model property.
     */
    public final TableView.TableViewFocusModel<List<T>> getFocusModel() {

        return this.getWrappedControl().getFocusModel();
    }


    /**
     * Specifies whether this TableView is editable - only if the TableView, the
     * TableColumn (if applicable) and the TableCells within it are both
     * editable will a TableCell be able to go into their editing state.
     *
     * @see #isEditable()
     * @see #setEditable(boolean)
     * @return the editable property.
     */
    public final BooleanProperty editableProperty() {

        return this.getWrappedControl().editableProperty();
    }

    /**
     * Sets the value of the editable property.
     *
     * @param value
     *            the value of the editable property.
     */
    public final void setEditable(final boolean value) {

        this.getWrappedControl().setEditable(value);
    }

    /**
     * Gets the value of the editable property.
     *
     * @return the value of the editable property.
     */
    public final boolean isEditable() {

        return this.getWrappedControl().isEditable();
    }


    /**
     * Represents the current cell being edited, or {@code null} if there is no
     * cell being edited.
     *
     * @see #getEditingCell()
     * @return the current cell being edited, or {@code null} if there is no
     *         cell being edited.
     *
     */
    public final ReadOnlyObjectProperty<TablePosition<List<T>, ?>> editingCellProperty() {

        return this.getWrappedControl().editingCellProperty();
    }

    /**
     * Gets the value of the property editingCell.
     *
     * @return the value of the property editingCell.
     */
    public final TablePosition<List<T>, ?> getEditingCell() {

        return this.getWrappedControl().getEditingCell();
    }


    /**
     * The TableColumns that are part of this TableView. As the user reorders
     * the TableView columns, this list will be updated to reflect the current
     * visual ordering. <br>
     * Note: to display any data in a TableView, there must be at least one
     * TableColumn in this ObservableList.
     *
     * @return the TableColumns that are part of this TableView
     */
    public final ObservableList<TableColumn<List<T>, ?>> getColumns() {

        return this.getWrappedControl().getColumns();
    }


    /**
     * The sortOrder list defines the order in which {@link TableColumn}
     * instances are sorted. An empty sortOrder list means that no sorting is
     * being applied on the TableView. If the sortOrder list has one TableColumn
     * within it, the TableView will be sorted using the
     * {@link TableColumn#sortTypeProperty() sortType} and
     * {@link TableColumn#comparatorProperty() comparator} properties of this
     * TableColumn (assuming {@link TableColumn#sortableProperty()
     * TableColumn.sortable} is true). If the sortOrder list contains multiple
     * TableColumn instances, then the TableView is firstly sorted based on the
     * properties of the first TableColumn. If two elements are considered
     * equal, then the second TableColumn in the list is used to determine
     * ordering. This repeats until the results from all TableColumn comparators
     * are considered, if necessary.
     *
     * @return An ObservableList containing zero or more TableColumn instances.
     */
    public final ObservableList<TableColumn<List<T>, ?>> getSortOrder() {

        return this.getWrappedControl().getSortOrder();
    }

    /**
     * Scrolls the TableView so that the given index is visible within the
     * viewport. Parameters: index - The index of an item that should be visible
     * to the user.
     *
     * @param index
     *            the index.
     */
    public void scrollTo(final int index) {

        this.getWrappedControl().scrollTo(index);
    }

    /**
     * Applies the currently installed resize policy against the given column,
     * resizing it based on the delta value provided.
     *
     * @param column
     *            the column.
     * @param delta
     *            the delta value.
     * @return {@code true}, if the column has been resized; {@code false}
     *         otherwise.
     */
    public boolean resizeColumn(final TableColumn<List<T>, ?> column,
            final double delta) {

        return this.getWrappedControl().resizeColumn(column, delta);
    }

    /**
     * Causes the cell at the given row/column view indexes to switch into its
     * editing state, if it is not already in it, and assuming that the
     * TableView and column are also editable.
     *
     * @param row
     *            the row index.
     * @param column
     *            the column index.
     */
    public void edit(final int row, final TableColumn<List<T>, ?> column) {

        this.getWrappedControl().edit(row, column);
    }

    /**
     * Returns an unmodifiable list containing the currently visible leaf
     * columns.
     *
     * @return an unmodifiable list containing the currently visible leaf
     *         columns.
     */
    public ObservableList<TableColumn<List<T>, ?>> getVisibleLeafColumns() {

        return this.getWrappedControl().getVisibleLeafColumns();
    }

    /**
     * Returns the position of the given column, relative to all other visible
     * leaf columns.
     *
     * @param column
     *            the column.
     * @return the position of the given column, relative to all other visible
     *         leaf columns.
     */
    public int getVisibleLeafIndex(final TableColumn<List<T>, ?> column) {

        return this.getWrappedControl().getVisibleLeafIndex(column);
    }

    /**
     * Returns the TableColumn in the given column index, relative to all other
     * visible leaf columns.
     *
     * @param column
     *            the column index.
     * @return the TableColumn in the given column index, relative to all other
     *         visible leaf columns.
     */
    public TableColumn<List<T>, ?> getVisibleLeafColumn(final int column) {

        return this.getWrappedControl().getVisibleLeafColumn(column);
    }



    @Override
    protected TableDataView<T> getWrappedControl() {

        return super.getWrappedControl();
    }

    /**
     * Sets the wrapped {@link TableDataView Control}.
     *
     * @param wrapped
     *            the wrapped {@link TableDataView Control}.
     */
    protected void setWrapped(final TableDataView<T> wrapped) {

        super.setWrappedControl(wrapped);
    }

    /**
     * Creates a new instance of the {@link TableDataViewWrapper} class.
     */
    public TableDataViewWrapper() {

        this(null);
    }

    /**
     * Creates a new instance of the {@link TableDataViewWrapper} class with the
     * provided {@link ITable Table}.
     *
     * @param table
     *            the {@link ITable Table}.
     */
    public TableDataViewWrapper(final ITable<T> table) {

        this(table, null);
    }

    /**
     * Creates a new instance of the {@link TableDataViewWrapper} class.
     *
     * @param table
     *            the table.
     * @param converter
     *            the initial string converter.
     */
    public TableDataViewWrapper(final ITable<T> table,
            final StringConverter<T> converter) {

        super(new TableDataView<>(table, converter));

        this.setupEventHandlers();
    }


    private void setupEventHandlers() {

        //
    }
}
