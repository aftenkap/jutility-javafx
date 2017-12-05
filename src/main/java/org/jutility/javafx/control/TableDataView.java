package org.jutility.javafx.control;



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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.util.StringConverter;

import org.jutility.common.datatype.table.ITable;


/**
 * The {@code TableDataView} class provides a control to display a
 * {@link ITable Table}.
 *
 * @param <T>
 *         the content type of the {@link ITable Table}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class TableDataView<T>
        extends TableView<List<T>> {


    private final ObjectProperty<ITable<T>>          tableProperty;
    private final TableCellFactory<T>                tableCellFactory;
    private final ObjectProperty<StringConverter<T>> converterProperty;

    /**
     * Returns the {@link ITable Table} property.
     *
     * @return the {@link ITable Table} property.
     *
     * @see TableDataView#getTable()
     * @see TableDataView#setTable(ITable)
     */
    public ObjectProperty<ITable<T>> tableProperty() {

        return this.tableProperty;
    }

    /**
     * Returns the value of the {@link ITable Table} property.
     *
     * @return the value of the {@link ITable Table} property.
     */
    public ITable<T> getTable() {

        return this.tableProperty.get();
    }

    /**
     * Sets the value of the {@link ITable Table} property.
     *
     * @param value
     *         the value of the {@link ITable Table} property.
     */
    public void setTable(final ITable<T> value) {

        this.tableProperty.set(value);
    }

    /**
     * Returns the {@link StringConverter} property.
     *
     * @return the {@link StringConverter} property.
     *
     * @see TableDataView#getConverter()
     * @see TableDataView#setConverter(StringConverter)
     */
    public ObjectProperty<StringConverter<T>> converterProperty() {

        return this.converterProperty;
    }

    /**
     * Returns the value of the {@link StringConverter Converter} property.
     *
     * @return the value of the {@link StringConverter Converter} property.
     */
    public StringConverter<T> getConverter() {

        return this.converterProperty.get();
    }

    /**
     * Sets the value of the {@link StringConverter Converter} property.
     *
     * @param value
     *         the value of the {@link StringConverter Converter} property.
     */
    public void setConverter(final StringConverter<T> value) {

        this.converterProperty.set(value);
    }

    /**
     * Creates a new instance of the {@link TableDataView} class.
     */
    public TableDataView() {

        this(null);
    }

    /**
     * Creates a new instance of the TableDataView class with the provided
     * {@link ITable Table} as data source.
     *
     * @param table
     *         the {@link ITable Table} data source.
     */
    public TableDataView(final ITable<T> table) {

        this(table, null);
    }

    /**
     * Creates a new instance of the {@link TableDataView} class with the
     * provided {@link ITable Table} as data source and the provided
     * {@link StringConverter}.
     *
     * @param table
     *         the {@link ITable Table} data source.
     * @param converter
     *         the initial {@link StringConverter}.
     */
    public TableDataView(final ITable<T> table,
            final StringConverter<T> converter) {

        super();

        this.tableProperty = new SimpleObjectProperty<>(table);
        this.converterProperty = new SimpleObjectProperty<>(converter);


        this.tableCellFactory = new TableCellFactory<>(converter);

        this.converterProperty.bindBidirectional(
                this.tableCellFactory.converterProperty());


        this.tableProperty.addListener((observable, oldValue, newValue) -> {

            this.getItems()
                    .clear();
            this.getColumns()
                    .clear();

            if (newValue != null) {

                this.setItems(
                        FXCollections.observableArrayList(newValue.getRows()));


                for (int i = 0; i < newValue.columns(); i++) {

                    final TableColumn<List<T>, T> column = new TableColumn<>(
                            "" + i);
                    column.setCellValueFactory(new TableCellValueFactory<>(i));
                    column.setCellFactory(this.tableCellFactory);
                    this.getColumns()
                            .add(column);
                }
            }

        });

        if (table != null) {

            this.setTable(table);
        }
    }

    /**
     * The {@code TableCellValueFactory} class provides a CellValueFactory for
     * cells of a {@link ITable Table}.
     *
     * @param <T>
     *         the content type of the {@link ITable Table}.
     *
     * @author Peter J. Radics
     * @version 0.1.2
     * @since 0.1.0
     */
    public static class TableCellValueFactory<T>
            implements
            Callback<CellDataFeatures<List<T>, T>, ObservableValue<T>> {

        private final int columnIndex;

        /**
         * Creates a new instance of the {@code TableCellValueFactory} class.
         *
         * @param columnIndex
         *         the column index of the cell.
         */
        public TableCellValueFactory(final int columnIndex) {

            this.columnIndex = columnIndex;
        }

        @Override
        public ObservableValue<T> call(
                final CellDataFeatures<List<T>, T> cellDataFeatures) {

            final List<T> row = cellDataFeatures.getValue();

            final T cellValue = row.get(this.columnIndex);

            return new ReadOnlyObjectWrapper<>(cellValue);
        }
    }



    /**
     * The {@code TableCellFactory} class provides a CellFactory for cells of a
     * {@link ITable Table}.
     *
     * @param <T>
     *         the content type of the {@link ITable Table}.
     *
     * @author Peter J. Radics
     * @version 0.1.2
     * @since 0.1.0
     */
    public static class TableCellFactory<T>
            implements
            Callback<TableColumn<List<T>, T>, TableCell<List<T>, T>> {


        private final ObjectProperty<StringConverter<T>> converterProperty;


        /**
         * Returns the {@link StringConverter} property.
         *
         * @return the {@link StringConverter} property.
         */
        public ObjectProperty<StringConverter<T>> converterProperty() {

            return this.converterProperty;
        }

        /**
         * Returns the {@link StringConverter}.
         *
         * @return the {@link StringConverter}.
         */
        public StringConverter<T> getConverter() {

            return this.converterProperty.get();
        }

        /**
         * Sets the value of the {@link StringConverter Converter} property.
         *
         * @param value
         *         the value of the {@link StringConverter Converter} property.
         */
        public void setConverter(final StringConverter<T> value) {

            this.converterProperty.set(value);
        }


        /**
         * Creates a new instance of the {@code TableCellFactory} class.
         */
        public TableCellFactory() {

            this(null);
        }

        /**
         * Creates a new instance of the {@code TableCellFactory} class.
         *
         * @param converter
         *         the initial {@link StringConverter}.
         */
        public TableCellFactory(final StringConverter<T> converter) {

            this.converterProperty = new SimpleObjectProperty<>(converter);
        }

        @Override
        public TableCell<List<T>, T> call(
                final TableColumn<List<T>, T> column) {


            return new TableCell<List<T>, T>() {

                @Override
                public void updateItem(final T item, final boolean empty) {


                    super.updateItem(item, empty);
                    if (TableCellFactory.this.getConverter() == null) {

                        if (item != null) {

                            this.setText(item.toString());
                        }
                        else {

                            this.setText(null);
                        }
                    }
                    else {

                        this.setText(TableCellFactory.this.getConverter()
                                .toString(item));
                    }
                }
            };
        }
    }
}
