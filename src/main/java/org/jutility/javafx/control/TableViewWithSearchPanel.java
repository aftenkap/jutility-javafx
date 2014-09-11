package org.jutility.javafx.control;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import org.jutility.common.reflection.ReflectionException;
import org.jutility.common.reflection.ReflectionUtils;
import org.jutility.javafx.events.MultiSelectionIndexChangedEvent;
import org.jutility.javafx.events.MultiSelectionItemChangedEvent;
import org.jutility.javafx.events.SelectedIndexChangedEvent;
import org.jutility.javafx.events.SelectedItemChangedEvent;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 *
 * @param <T>
 *            the content type.
 */
public class TableViewWithSearchPanel<T>
        extends VBox {


    private Class<T>                       clazz;
    private Text                           title;
    private TableView<T>                   tableView;

    private Map<String, TableColumn<T, ?>> titleColumnMap;
    private Map<String, String>            titlePropertyMap;

    private ObservableList<T>              items;

    private Collection<T>                  objects;



    private List<T>                        previousSelections;
    private List<Integer>                  previousSelectionIndices;


    private ContextMenu                    contextMenu;

    private final ObservableList<Action>   contextMenuActions;


    // private String filterString;
    // private SearchPanel searchPanel;


    // /**
    // * Creates a new {@link TableViewWithSearchPanel} with the provided title.
    // *
    // * @param title
    // * title of this panel
    // */
    // public TableViewWithSearchPanel(final String title) {
    //
    // this(title, null);
    // }

    private <S> TableColumn<T, S> createTableColumn(Class<S> propertyClass,
            StringConverter<S> converter, String title, String property) {

        TableColumn<T, S> column = null;
        Method method;
        try {

            method = ReflectionUtils.getMethodOrAccessor(this.clazz, property);
        }
        catch (ReflectionException e) {


            throw new RuntimeException("Could not retrieve accessor for "
                    + ReflectionUtils.buildSignature(null, this.clazz,
                            propertyClass, null, null) + "!", e);
        }

        if (method != null
                && ReflectionUtils.hasCompatibleReturnType(method,
                        propertyClass)) {

            column = this.createTableColumn(propertyClass, title, property);

            if (converter != null) {

                this.setStringConverter(column, converter);
            }
        }
        else {

            // TODOCUMENT
        }
        column.setMaxWidth(Double.MAX_VALUE);
        return column;
    }

    private <S> TableColumn<T, S> createTableColumn(Class<S> propertyClass,
            String title, String property) {

        TableColumn<T, S> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<T, S>(property));

        column.setMaxWidth(Double.MAX_VALUE);
        return column;
    }

    private <S> void setStringConverter(TableColumn<T, S> column,
            final StringConverter<S> converter) {

        column.setCellFactory(new Callback<TableColumn<T, S>, TableCell<T, S>>() {

            @Override
            public TableCell<T, S> call(TableColumn<T, S> param) {

                TableCell<T, S> cell = new TextFieldTableCell<>(converter);
                cell.setMaxWidth(Double.MAX_VALUE);
                return cell;
            }
        });
    }



    private void addTableColumn(String title, String property) {


        Method method;
        try {

            method = ReflectionUtils.getMethodOrAccessor(this.clazz, property);
        }
        catch (ReflectionException e) {


            throw new RuntimeException("Could not retrieve accessor for "
                    + ReflectionUtils.buildSignature(null, this.clazz, null,
                            null, null) + "!", e);
        }

        if (method == null) {
            throw new IllegalArgumentException("Class " + this.clazz
                    + " does not have a property matching " + property);
        }

        Class<?> propertyClass = method.getReturnType();


        TableColumn<T, ?> column = this.createTableColumn(propertyClass, title,
                property);

        this.titleColumnMap.put(title, column);
    }

    private void addTableColumnsToTable() {

        for (String title : titleColumnMap.keySet()) {

            TableColumn<T, ?> column = this.titleColumnMap.get(title);
            this.tableView.getColumns().add(column);

        }
    }


    /**
     * Creates a new {@link TableViewWithSearchPanel} with the provided title.
     * 
     * @param clazz
     * 
     * @param title
     *            title of this panel
     * @param titlePropertyMap
     */
    public TableViewWithSearchPanel(Class<T> clazz, final String title,
            Map<String, String> titlePropertyMap) {

        this.clazz = clazz;

        this.title = new Text(title);
        this.title.setFont(Font.font("verdana", 16));

        this.tableView = new TableView<>();
        this.tableView.setId(title);

        this.titlePropertyMap = titlePropertyMap;
        this.titleColumnMap = new LinkedHashMap<>();

        for (String key : titlePropertyMap.keySet()) {

            this.addTableColumn(key, titlePropertyMap.get(key));
        }

        this.addTableColumnsToTable();

        this.previousSelections = new ArrayList<>();
        this.previousSelectionIndices = new ArrayList<>();


        this.contextMenu = new ContextMenu();
        this.contextMenuActions = FXCollections
                .observableList(new LinkedList<Action>());

        this.contextMenuActions.addListener(new ListChangeListener<Action>() {

            @Override
            public void onChanged(Change<? extends Action> change) {

                TableViewWithSearchPanel.this.contextMenu.getItems().clear();

                TableViewWithSearchPanel.this.contextMenu = ActionUtils
                        .createContextMenu(TableViewWithSearchPanel.this.contextMenuActions);
            }
        });
        // searchPanel = new SearchPanel();
        //
        // searchPanel.getClose().addEventHandler(ActionEvent.ACTION,
        // new EventHandler<ActionEvent>() {
        //
        // @Override
        // public void handle(ActionEvent event) {
        //
        // if (TableViewWithSearchPanel.this.getChildren()
        // .contains(searchPanel)) {
        // TableViewWithSearchPanel.this.getChildren().remove(
        // TableViewWithSearchPanel.this.searchPanel);
        // }
        // }
        //
        // });
        //
        //
        // searchPanel.getFilterString().addListener(new
        // ChangeListener<String>() {
        //
        // @Override
        // public void changed(ObservableValue<? extends String> observable,
        // String oldVal, String newVal) {
        //
        // filterString = newVal;
        // populate(TableViewWithSearchPanel.this.objects);
        // }
        // });


        this.getChildren().add(this.title);
        this.getChildren().add(this.tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);

        // this.addEventHandler(KeyEvent.KEY_PRESSED,
        // new EventHandler<KeyEvent>() {
        //
        // @Override
        // public void handle(KeyEvent event) {
        //
        // if (event.getCode().toString() == "F"
        // && event.isControlDown()) {
        // if (!TableViewWithSearchPanel.this.getChildren()
        // .contains(searchPanel)) {
        // TableViewWithSearchPanel.this
        // .getChildren()
        // .add(TableViewWithSearchPanel.this.searchPanel);
        // searchPanel.setTextFocus();
        // }
        // }
        // }
        //
        // });

        tableView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<T>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends T> observable,
                            T oldValue, T newValue) {


                        SelectedItemChangedEvent event = new SelectedItemChangedEvent(
                                oldValue, newValue);
                        fireEvent(event);
                    }

                });
        tableView.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Number> observable,
                            Number oldValue, Number newValue) {

                        SelectedIndexChangedEvent event = new SelectedIndexChangedEvent(
                                oldValue, newValue);
                        fireEvent(event);

                    }

                });
        tableView.getSelectionModel().getSelectedIndices()
                .addListener(new ListChangeListener<Integer>() {

                    @Override
                    public void onChanged(Change<? extends Integer> changes) {

                        // List<Integer> oldValues =
                        // calculateOldSelection(changes);
                        List<Integer> oldValues = previousSelectionIndices;

                        List<Integer> newValues = calculateNewSelection(changes);
                        MultiSelectionIndexChangedEvent<Integer> event = new MultiSelectionIndexChangedEvent<>(
                                oldValues, newValues);
                        previousSelectionIndices = newValues;

                        fireEvent(event);
                    }

                });
        tableView.getSelectionModel().getSelectedItems()
                .addListener(new ListChangeListener<T>() {

                    @Override
                    public void onChanged(Change<? extends T> changes) {

                        // List<String> oldValues =
                        // calculateOldSelection(changes);
                        List<T> oldValues = previousSelections;
                        List<T> newValues = calculateNewSelection(changes);



                        MultiSelectionItemChangedEvent<T> event = new MultiSelectionItemChangedEvent<>(
                                oldValues, newValues);
                        previousSelections = newValues;


                        fireEvent(event);
                    }

                });


        this.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED,
                new EventHandler<ContextMenuEvent>() {

                    @Override
                    public void handle(ContextMenuEvent event) {

                        double screenX = event.getScreenX();
                        double screenY = event.getScreenY();
                        TableViewWithSearchPanel.this.contextMenu
                                .show(TableViewWithSearchPanel.this, screenX,
                                        screenY);


                    }


                });

    }


    /**
     * Populates the list view from a collection.
     * 
     * @param collection
     *            schema entries to populate
     */
    public void populate(Collection<T> collection) {

        this.objects = collection;

        if (this.objects != null && !this.objects.isEmpty()) {
            T cur = tableView.getSelectionModel().getSelectedItem();
            List<T> filteredList = new ArrayList<>();

            for (T entry : this.objects) {

                // if (filterString == null || filterString == "") {

                filteredList.add(entry);
                // }
                // else {
                //
                // if (this.converter != null) {
                // System.out.println("Converter is set.");
                // if (converter.toString(entry).toLowerCase()
                // .contains(filterString.toLowerCase())) {
                //
                // System.out.println("Contains filter string.");
                // filteredList.add(entry);
                // }
                // }
                // else {
                // if (entry != null
                // && entry.toString().toLowerCase()
                // .contains(filterString.toLowerCase())) {
                // filteredList.add(entry);
                // }
                // }
                // }

            }
            this.tableView.getItems().clear();

            // Begin evil workaround for tableview updates
            this.tableView.getColumns().clear();
            this.addTableColumnsToTable();
            // End evil workaround for tableview updates.

            this.items = FXCollections.observableArrayList(filteredList);

            this.tableView.setItems(this.items);
            if (cur != null) {

                this.tableView.getSelectionModel().select(cur);
            }
        }
        else {
            this.clear();
        }
        this.requestLayout();
    }


    /**
     * adds a single entry to the list view
     * 
     * @param entry
     */
    public void populateSingleValue(T entry) {

        ArrayList<T> list = new ArrayList<>();
        list.add(entry);
        this.populate(list);
    }

    /**
     * Clears the list view.
     */
    public void clear() {

        this.items = FXCollections.observableArrayList(new ArrayList<T>());
        this.tableView.setItems(this.items);
        this.tableView.getSelectionModel().clearSelection();
    }


    /**
     * Clears the selection.
     */
    public void clearSelection() {

        tableView.getSelectionModel().clearSelection();
    }

    /**
     * Returns the selected item.
     * 
     * @return the selected item from the list view
     */
    public T getSelectedItem() {

        return this.tableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the selected index.
     * 
     * @return the selected index from the list view
     */
    public int getSelectedIndex() {

        return tableView.getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns the selected items.
     * 
     * @return the selected items from the list view
     */
    public List<T> getSelectedItems() {

        return this.tableView.getSelectionModel().getSelectedItems();
    }

    /**
     * @param elements
     */
    @SafeVarargs
    public final void remove(T... elements) {

        items.removeAll(elements);
    }

    /**
     * @param index
     *            the index of the string value to be removed from the list
     */
    public void remove(int index) {

        items.remove(index);
    }

    /**
     * @param item
     *            the item to be removed
     */
    public void removeAll(Object item) {

        items.remove(item);
    }

    /**
     * Returns the selected indices.
     * 
     * @return the selected indices from the list view
     */
    public List<Integer> getSelectedIndices() {

        return tableView.getSelectionModel().getSelectedIndices();
    }


    /**
     * Sets the selection mode of this ListView.
     * 
     * @param mode
     *            the mode to be set to.
     */
    public void setSelectionMode(SelectionMode mode) {

        tableView.getSelectionModel().setSelectionMode(mode);
    }



    /**
     * @param propertyClass
     * @param columnTitle
     * @param converter
     */
    public <S> void setStringConverter(Class<S> propertyClass,
            String columnTitle, StringConverter<S> converter) {



        TableColumn<T, ?> currentColumn = this.titleColumnMap.get(columnTitle);

        if (currentColumn != null) {

            TableColumn<T, S> column = this.createTableColumn(propertyClass,
                    converter, columnTitle,
                    this.titlePropertyMap.get(columnTitle));


            if (column != null) {

                int index = this.tableView.getColumns().indexOf(currentColumn);
                this.titleColumnMap.put(columnTitle, column);
                this.tableView.getColumns().set(index, column);

            }
            else {

                System.out.println("Couldn't create replacement column "
                        + columnTitle);
            }
        }
        else {
            System.out.println("Couldn't retrieve column " + columnTitle);
        }

        // this.converter = converter;

        // if (converter != null) {
        // this.listView
        // .setCellFactory(new Callback<TableView<T>, TableCell<T>>() {
        //
        // @Override
        // public TableCell<T> call(TableView<T> param) {
        //
        // return new TextFieldTableCell<>(
        // TableViewWithSearchPanel.this.converter);
        // }
        // });
        // }
        // else {
        // this.listView
        // .setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
        //
        // @Override
        // public ListCell<T> call(ListView<T> param) {
        //
        // return new TextFieldListCell<>();
        // }
        // });
        // }
    }

    // public StringConverter<?> getStringConverter(String columnTitle) {
    //
    // return this.converter;
    // }


    private <S> List<S> calculateNewSelection(Change<? extends S> changes) {

        List<S> newValues = new ArrayList<>();
        newValues.addAll(changes.getList());

        return newValues;
    }


    /**
     * Provides access to the context menu actions.
     * 
     * @return the context menu actions list.
     */
    public ObservableList<Action> contextMenuActions() {

        return this.contextMenuActions;
    }
}
