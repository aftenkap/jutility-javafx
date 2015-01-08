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

import javafx.beans.property.ObjectProperty;

import org.jutility.common.datatype.table.ITable;
import org.jutility.common.datatype.table.Table;
import org.jutility.javafx.control.TableDataView;



/**
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.1
 * @param <T>
 *            the content type of the {@link ITable}.
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
