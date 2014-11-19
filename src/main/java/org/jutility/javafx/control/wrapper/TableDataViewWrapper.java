package org.jutility.javafx.control.wrapper;


import java.util.List;

import javafx.beans.property.ObjectProperty;

import org.jutility.common.datatype.table.ITable;
import org.jutility.common.datatype.table.Table;
import org.jutility.javafx.control.TableDataView;



/**
 * 
 * @param <T>
 *            the content type.
 * 
 * @author Peter J. Radics
 * @version 0.1.1
 * @since 0.1.1
 *
 */
public class TableDataViewWrapper<T>
        extends TableViewWrapper<List<T>> {

    /**
     * Returns the table property.
     * 
     * @return the table property.
     */
    public ObjectProperty<ITable<T>> tableProperty() {

        return this.getWrapped().tableProperty();
    }

    /**
     * Returns the value of the table property.
     * 
     * @return the value of the table property.
     */
    public ITable<T> getTable() {

        return this.getWrapped().getTable();
    }

    /**
     * Sets the value of the table property.
     * 
     * @param table
     *            the value of the table property.
     */
    public void setTable(final ITable<T> table) {

        this.getWrapped().setTable(table);
    }

    @Override
    protected TableDataView<T> getWrapped() {

        if (super.getWrapped() instanceof TableDataView) {

            return (TableDataView<T>) super.getWrapped();
        }

        return null;
    }

    /**
     * @param wrapped
     */
    protected void setWrapped(TableDataView<T> wrapped) {

        super.setWrapped(wrapped);
    }

    /**
     * Creates a new instance of the {@link TableDataViewWrapper} class.
     */
    public TableDataViewWrapper() {

        this(null);
    }

    /**
     * Creates a new instance of the {@link TableDataViewWrapper} class.
     * 
     * @param table
     *            the table.
     */
    public TableDataViewWrapper(Table<T> table) {

        super(new TableDataView<>(table));

        this.setupEventHandlers();
    }



    private void setupEventHandlers() {

        //
    }
}
