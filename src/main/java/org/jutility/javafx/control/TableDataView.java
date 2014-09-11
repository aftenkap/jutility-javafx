package org.jutility.javafx.control;


import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
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
 * @author Peter J. Radics
 * 
 * @param <T>
 *            the content type.
 */
public class TableDataView<T>
        extends TableView<List<T>> {


    private final ObjectProperty<ITable<T>>          table;
    private final TableCellFactory<T>                tableCellFactory;
    private final ObjectProperty<StringConverter<T>> stringConverter;

    /**
     * Returns the table property.
     * 
     * @return the table property.
     */
    public ObjectProperty<ITable<T>> table() {

        return this.table;
    }

    /**
     * Returns the {@link ITable Table}.
     * 
     * @return the {@link ITable Table}.
     */
    public ITable<T> getTable() {

        return this.table.get();
    }

    /**
     * Sets the {@link ITable Table}.
     * 
     * @param table
     *            the {@link ITable Table}.
     */
    public void setTable(ITable<T> table) {

        this.table.set(table);
    }

    /**
     * Returns the {@link StringConverter} property.
     * 
     * @return the {@link StringConverter} property.
     */
    public ObjectProperty<StringConverter<T>> stringConverter() {

        return this.stringConverter;
    }

    /**
     * Returns the {@link StringConverter}.
     * 
     * @return the {@link StringConverter}.
     */
    public StringConverter<T> getStringConverter() {

        return this.stringConverter.get();
    }

    /**
     * Sets the {@link StringConverter}.
     * 
     * @param stringConverter
     *            the {@link StringConverter}.
     */
    public void setStringConverter(StringConverter<T> stringConverter) {

        this.stringConverter.set(stringConverter);
    }

    /**
     * Creates a new instance of the {@link TableDataView} class.
     */
    public TableDataView() {

        this(null);
    }

    /**
     * Creates a new instance of the TableDataView class with the provide
     * {@link ITable Table} as data source.
     * 
     * @param table
     *            the {@link ITable Table} data source.
     */
    public TableDataView(ITable<T> table) {

        this(table, null);
    }

    /**
     * Creates a new instance of the {@link TableDataView} class with the
     * provide {@link ITable Table} as data source and the provided
     * {@link StringConverter}.
     * 
     * @param table
     *            the {@link ITable Table} data source.
     * @param stringConverter
     *            the {@link StringConverter}.
     */
    public TableDataView(ITable<T> table, StringConverter<T> stringConverter) {

        super();

        this.table = new SimpleObjectProperty<>();
        this.stringConverter = new SimpleObjectProperty<>(
                stringConverter);


        this.tableCellFactory = new TableCellFactory<>();

        if (stringConverter != null) {

            this.tableCellFactory.setStringConverter(stringConverter);
        }
        this.stringConverter.bindBidirectional(this.tableCellFactory
                .stringConverter());


        this.table.addListener(new ChangeListener<ITable<T>>() {

            @Override
            public void changed(
                    ObservableValue<? extends ITable<T>> observable,
                    ITable<T> oldValue, ITable<T> newValue) {

                TableDataView.this.getItems().clear();
                TableDataView.this.getColumns().clear();

                if (newValue != null) {

                    TableDataView.this.setItems(FXCollections
                            .observableArrayList(newValue.getRows()));


                    for (int i = 0; i < newValue.columns(); i++) {

                        TableColumn<List<T>, T> column = new TableColumn<>(
                                "" + i);
                        column.setCellValueFactory(new TableCellValueFactory<T>(
                                i));
                        column.setCellFactory(TableDataView.this.tableCellFactory);
                        TableDataView.this.getColumns().add(column);
                    }
                }

            }
        });

        if (table != null) {

            this.setTable(table);
        }


    }

    private static class TableCellValueFactory<T>
            implements
            Callback<CellDataFeatures<List<T>, T>, ObservableValue<T>> {

        private final int columnIndex;

        public TableCellValueFactory(final int columnIndex) {

            this.columnIndex = columnIndex;
        }

        @Override
        public ObservableValue<T> call(
                CellDataFeatures<List<T>, T> cellDataFeatures) {

            List<T> row = cellDataFeatures.getValue();

            T cellValue = row.get(columnIndex);

            return new ReadOnlyObjectWrapper<>(cellValue);
        }
    }

    /**
     * @author Peter J. Radics
     * @version 0.1
     * 
     * @param <T>
     *            the content type.
     */
    private static class TableCellFactory<T>
            implements
            Callback<TableColumn<List<T>, T>, TableCell<List<T>, T>> {


        private final ObjectProperty<StringConverter<T>> stringConverter;


        /**
         * Returns the {@link StringConverter} property.
         * 
         * @return the {@link StringConverter} property.
         */
        public ObjectProperty<StringConverter<T>> stringConverter() {

            return this.stringConverter;
        }

        /**
         * Returns the {@link StringConverter}.
         * 
         * @return the {@link StringConverter}.
         */
        public StringConverter<T> getStringConverter() {

            return this.stringConverter.get();
        }

        /**
         * Sets the {@link StringConverter}.
         * 
         * @param stringConverter
         *            the {@link StringConverter}.
         */
        public void setStringConverter(final StringConverter<T> stringConverter) {

            this.stringConverter.set(stringConverter);
        }


        /**
         * Creates a new instance of the {@link TableCellFactory} class.
         */
        public TableCellFactory() {

            this.stringConverter = new SimpleObjectProperty<>();
        }

        @Override
        public TableCell<List<T>, T> call(
                TableColumn<List<T>, T> column) {

            TableCell<List<T>, T> cell = new TableCell<List<T>, T>() {

                @Override
                public void updateItem(T item, boolean empty) {


                    super.updateItem(item, empty);
                    if (TableCellFactory.this.getStringConverter() == null) {

                        if (item != null) {
                            
                            this.setText(item.toString());
                        }
                        else {
                            
                            this.setText(null);
                        }
                    }
                    else {
                        
                        this.setText(TableCellFactory.this.getStringConverter()
                                .toString(item));
                    }
                }
            };

            return cell;
        }
    }
}
