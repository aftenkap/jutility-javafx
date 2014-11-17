package org.jutility.javafx.control.wrapper;


import java.util.Collection;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.util.Callback;


/**
 * @param <S>
 * 
 * @author peter
 * @version
 * @since
 *
 */
public class TableViewWrapper<S>
        extends WrapperBase<TableView<S>> {

    /**
     * The underlying data model for the TableView. Note that it has a generic
     * type that must match the type of the TableView itself.
     * 
     * @see #getItems()
     * @see #setItems(ObservableList)
     * 
     * @return the items property.
     */
    public final ObjectProperty<ObservableList<S>> itemsProperty() {

        return this.getWrapped().itemsProperty();
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *            the value of the items property.
     */
    public final void setItems(ObservableList<S> value) {

        this.getWrapped().setItems(value);
    }

    /**
     * Gets the value of the items property.
     * 
     * @return the value of the items property.
     */
    public final ObservableList<S> getItems() {

        return this.getWrapped().getItems();
    }



    /**
     * This controls whether a menu button is available when the user clicks in
     * a designated space within the TableView, within which is a radio menu
     * item for each TableColumn in this table. This menu allows for the user to
     * show and hide all TableColumns easily.
     * 
     * @see #isTableMenuButtonVisible()
     * @see #setTableMenuButtonVisible(boolean)
     * 
     * @return the table menu button visible property.
     */
    public final BooleanProperty tableMenuButtonVisibleProperty() {

        return this.getWrapped().tableMenuButtonVisibleProperty();
    }

    /**
     * Sets the value of the table menu button visible property.
     * 
     * @param value
     *            the value of the table menu button visible property.
     */
    public final void setTableMenuButtonVisible(boolean value) {

        this.getWrapped().setTableMenuButtonVisible(value);
    }

    /**
     * Gets the value of the table menu button visible property.
     * 
     * @return the value of the table menu button visible property.
     */
    public final boolean isTableMenuButtonVisible() {

        return this.getWrapped().isTableMenuButtonVisible();
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

        return this.getWrapped().columnResizePolicyProperty();
    }

    /**
     * Sets the value of the column resize policy property.
     * 
     * @param callback
     *            the value of the column resize policy property.
     */
    public final void setColumnResizePolicy(
            @SuppressWarnings("rawtypes") Callback<TableView.ResizeFeatures, java.lang.Boolean> callback) {

        this.getWrapped().setColumnResizePolicy(callback);
    }

    /**
     * Gets the value of the column resize policy property.
     * 
     * @return the value of the column resize policy property.
     */
    @SuppressWarnings("rawtypes")
    public final Callback<TableView.ResizeFeatures, java.lang.Boolean> getColumnResizePolicy() {

        return this.getWrapped().getColumnResizePolicy();
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
    public final ObjectProperty<Callback<TableView<S>, TableRow<S>>> rowFactoryProperty() {

        return this.getWrapped().rowFactoryProperty();
    }

    /**
     * Sets the value of the row factory property.
     * 
     * @param value
     *            the value of the row factory property.
     */
    public final void setRowFactory(Callback<TableView<S>, TableRow<S>> value) {

        this.getWrapped().setRowFactory(value);
    }

    /**
     * Gets the value of the row factory property.
     * 
     * @return the value of the row factory property.
     */
    public final Callback<TableView<S>, TableRow<S>> getRowFactory() {

        return this.getWrapped().getRowFactory();
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

        return this.getWrapped().placeholderProperty();
    }

    /**
     * Sets the value of the placeholder property.
     * 
     * @param value
     *            the value of the placeholder property.
     */
    public final void setPlaceholder(Node value) {

        this.getWrapped().setPlaceholder(value);
    }

    /**
     * Gets the value of the placeholder property.
     * 
     * @return the value of the placeholder property.
     */
    public final Node getPlaceholder() {

        return this.getWrapped().getPlaceholder();
    }


    /**
     * The SelectionModel provides the API through which it is possible to
     * select single or multiple items within a TableView, as well as inspect
     * which items have been selected by the user. Note that it has a generic
     * type that must match the type of the TableView itself.
     * 
     * @see #getSelectionModel()
     * @see #setSelectionModel(TableView.TableViewSelectionModel)
     * @return the selection model property.
     */
    public final ObjectProperty<TableView.TableViewSelectionModel<S>> selectionModelProperty() {

        return this.getWrapped().selectionModelProperty();
    }

    /**
     * Sets the value of the selection model property.
     * 
     * @param value
     *            the value of the selection model property.
     */
    public final void setSelectionModel(
            TableView.TableViewSelectionModel<S> value) {

        this.getWrapped().setSelectionModel(value);
    }

    /**
     * Gets the value of the selection model property.
     * 
     * @return the value of the selection model property.
     */
    public final TableView.TableViewSelectionModel<S> getSelectionModel() {

        return this.getWrapped().getSelectionModel();
    }


    /**
     * Represents the currently-installed TableView.TableViewFocusModel for this
     * TableView. Under almost all circumstances leaving this as the default
     * focus model will suffice.
     * 
     * @see #getFocusModel()
     * @see #setFocusModel(TableViewFocusModel)
     * @return the focus model property.
     */
    public final ObjectProperty<TableViewFocusModel<S>> focusModelProperty() {

        return this.getWrapped().focusModelProperty();
    }

    /**
     * Sets the value of the focus model property.
     * 
     * @param value
     *            the value of the focus model property.
     */
    public final void setFocusModel(TableView.TableViewFocusModel<S> value) {

        this.getWrapped().setFocusModel(value);
    }

    /**
     * Gets the value of the focus model property.
     * 
     * @return the value of the focus model property.
     */
    public final TableView.TableViewFocusModel<S> getFocusModel() {

        return this.getWrapped().getFocusModel();
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

        return this.getWrapped().editableProperty();
    }

    /**
     * Sets the value of the editable property.
     * 
     * @param value
     *            the value of the editable property.
     */
    public final void setEditable(boolean value) {

        this.getWrapped().setEditable(value);
    }

    /**
     * Gets the value of the editable property.
     * 
     * @return the value of the editable property.
     */
    public final boolean isEditable() {

        return this.getWrapped().isEditable();
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
    public final ReadOnlyObjectProperty<TablePosition<S, ?>> editingCellProperty() {

        return this.getWrapped().editingCellProperty();
    }

    /**
     * Gets the value of the property editingCell.
     * 
     * @return the value of the property editingCell.
     */
    public final TablePosition<S, ?> getEditingCell() {

        return this.getWrapped().getEditingCell();
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
    public final ObservableList<TableColumn<S, ?>> getColumns() {

        return this.getWrapped().getColumns();
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
    public final ObservableList<TableColumn<S, ?>> getSortOrder() {

        return this.getWrapped().getSortOrder();
    }

    /**
     * Scrolls the TableView so that the given index is visible within the
     * viewport. Parameters: index - The index of an item that should be visible
     * to the user.
     * 
     * @param index
     *            the index.
     */
    public void scrollTo(int index) {

        this.getWrapped().scrollTo(index);
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
    public boolean resizeColumn(TableColumn<S, ?> column, double delta) {

        return this.getWrapped().resizeColumn(column, delta);
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
    public void edit(int row, TableColumn<S, ?> column) {

        this.getWrapped().edit(row, column);
    }

    /**
     * Returns an unmodifiable list containing the currently visible leaf
     * columns.
     * 
     * @return an unmodifiable list containing the currently visible leaf
     *         columns.
     */
    public ObservableList<TableColumn<S, ?>> getVisibleLeafColumns() {

        return this.getWrapped().getVisibleLeafColumns();
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
    public int getVisibleLeafIndex(TableColumn<S, ?> column) {

        return this.getWrapped().getVisibleLeafIndex(column);
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
    public TableColumn<S, ?> getVisibleLeafColumn(int column) {

        return this.getWrapped().getVisibleLeafColumn(column);
    }


    /**
     * Creates a new instance of the {@link TableViewWrapper} class.
     */
    public TableViewWrapper() {

        this(null);
    }

    /**
     * Creates a new instance of the {@link TableViewWrapper} class with the
     * provided items.
     * 
     * @param items
     *            the items.
     */
    public TableViewWrapper(final Collection<S> items) {

        super(new TableView<S>());

        if (items != null) {

            this.setItems(FXCollections.observableArrayList(items));
        }
    }
}
