package org.jutility.javafx.control;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;



/**
 * Provides a ListView with a Title and SearchPanel for the provided type.
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * @version 0.2
 * @param <T>
 *            the contained type.
 * 
 */
public class ListViewWithSearchPanel<T>
        extends VBox {

    private final Label                              title;
    private final ListView<T>                        listView;

    private final ObservableList<T>                  items;
    private final ObservableList<T>                  filteredItems;

    private final ObjectProperty<StringConverter<T>> stringConverter;


    private final SearchPanel                        searchPanel;

    private ContextMenu                              contextMenu;
    private final ObservableList<Action>             contextMenuActions;


    /**
     * Returns the title property.
     * 
     * @return the title property.
     */
    public StringProperty title() {

        return this.title.textProperty();
    }

    /**
     * Returns the value of the title property.
     * 
     * @return the value of the title property.
     */
    public String getTitle() {

        return this.title().get();
    }

    /**
     * Sets the value of the title property.
     * 
     * @param title
     *            the value of the title property.
     */
    public void setTitle(final String title) {

        this.title().set(title);
    }

    /**
     * Creates a new {@link ListViewWithSearchPanel} with no title.
     */
    public ListViewWithSearchPanel() {

        this(null);
    }

    /**
     * Creates a new {@link ListViewWithSearchPanel} with the provided title.
     * 
     * @param title
     *            title of this panel
     */
    public ListViewWithSearchPanel(final String title) {

        this(title, null);
    }


    /**
     * Creates a new {@link ListViewWithSearchPanel} with the provided title.
     * 
     * @param title
     *            title of this panel
     * @param stringConverter
     *            the string converter to use.
     */
    public ListViewWithSearchPanel(final String title,
            StringConverter<T> stringConverter) {


        this.listView = new ListView<>();
        VBox.setVgrow(listView, Priority.ALWAYS);

        if (title != null && title != "") {

            this.title = new Label(title);
            this.title.setFont(Font.font("verdana", 16));
            this.getChildren().add(this.title);

            this.listView.setId(title);

            this.title.setLabelFor(this.listView);
        }
        else {

            this.title = null;
        }

        this.getChildren().add(this.listView);

        this.items = FXCollections.observableList(new LinkedList<T>());
        this.filteredItems = FXCollections.observableList(new LinkedList<T>());

        Bindings.bindContentBidirectional(this.listView.getItems(),
                this.filteredItems);



        this.contextMenu = new ContextMenu();
        this.contextMenuActions = FXCollections
                .observableList(new LinkedList<Action>());


        this.searchPanel = new SearchPanel();
        this.searchPanel.setVisible(false);

        this.stringConverter = new SimpleObjectProperty<>();


        this.setupEventHandlers();
        this.setStringConverter(stringConverter);
    }


    /**
     * Returns the items of this ListView.
     * 
     * @return the items of this ListView.
     */
    public ObservableList<T> items() {

        return this.items;
    }



    /**
     * Clears the list view.
     */
    public void clear() {

        this.items.clear();
        this.clearSelection();
    }


    /**
     * Clears the selection.
     */
    public void clearSelection() {

        listView.getSelectionModel().clearSelection();
    }

    /**
     * Returns the selection model.
     * 
     * @return the selection model.
     */
    public MultipleSelectionModel<T> getSelectionModel() {


        return this.listView.getSelectionModel();
    }

    /**
     * Sets the selection mode of this ListView.
     * 
     * @param mode
     *            the mode to be set to.
     */
    public void setSelectionMode(SelectionMode mode) {

        this.getSelectionModel().setSelectionMode(mode);
    }

    /**
     * Returns the selected item property.
     * 
     * @return the selected item property.
     */
    public ReadOnlyObjectProperty<T> selectedItemProperty() {

        return this.getSelectionModel().selectedItemProperty();
    }

    /**
     * Returns the selected item.
     * 
     * @return the selected item from the list view
     */
    public T getSelectedItem() {

        return this.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the selected index property.
     * 
     * @return the selected index property.
     */
    public ReadOnlyIntegerProperty selectedIndexProperty() {

        return this.getSelectionModel().selectedIndexProperty();
    }

    /**
     * Returns the selected index.
     * 
     * @return the selected index from the list view
     */
    public int getSelectedIndex() {

        return this.getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns the selected filteredItems.
     * 
     * @return the selected filteredItems from the list view
     */
    public ObservableList<T> getSelectedItems() {

        return this.getSelectionModel().getSelectedItems();
    }

    /**
     * Returns the selected indices.
     * 
     * @return the selected indices from the list view
     */
    public ObservableList<Integer> getSelectedIndices() {

        return this.getSelectionModel().getSelectedIndices();
    }

    /**
     * removes the provided elements from the list view.
     * 
     * @param elements
     *            the elements to remove.
     */
    @SafeVarargs
    public final void remove(T... elements) {

        this.items.removeAll(elements);
    }

    /**
     * @param index
     *            the index of the string value to be removed from the list
     */
    public void remove(int index) {

        this.items.remove(index);
    }

    /**
     * @param item
     *            the item to be removed
     */
    public void removeAll(Object item) {

        this.items.remove(item);
    }

    /**
     * Sets the cell factory of this ListView.
     * 
     * @param cellFactory
     *            the cell factory of this ListView.
     */
    public void setCellFactory(Callback<ListView<T>, ListCell<T>> cellFactory) {

        this.listView.setCellFactory(cellFactory);
    }


    /**
     * Returns the string converter property.
     * 
     * @return the string converter property.
     */
    public ObjectProperty<StringConverter<T>> stringConverter() {

        return this.stringConverter;
    }

    /**
     * Sets the string converter to the one provided.
     * 
     * @param converter
     *            the new string converter.
     */
    public void setStringConverter(StringConverter<T> converter) {

        this.stringConverter.set(converter);
    }

    /**
     * Returns the string converter.
     * 
     * @return the string converter.
     */
    public StringConverter<T> getStringConverter() {

        return this.stringConverter.get();
    }


    /**
     * Provides access to the context menu actions.
     * 
     * @return the context menu actions list.
     */
    public ObservableList<Action> contextMenuActions() {

        return this.contextMenuActions;
    }



    private void setupEventHandlers() {



        this.contextMenuActions.addListener(new ListChangeListener<Action>() {

            @Override
            public void onChanged(Change<? extends Action> change) {

                ListViewWithSearchPanel.this.contextMenu.getItems().clear();

                ListViewWithSearchPanel.this.contextMenu = ActionUtils
                        .createContextMenu(ListViewWithSearchPanel.this.contextMenuActions);
            }
        });



        this.stringConverter
                .addListener(new ChangeListener<StringConverter<T>>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends StringConverter<T>> observable,
                            StringConverter<T> oldValue,
                            StringConverter<T> newValue) {

                        if (newValue != null) {
                            ListViewWithSearchPanel.this.listView
                                    .setCellFactory(new Callback<ListView<T>, ListCell<T>>() {

                                        @Override
                                        public ListCell<T> call(
                                                ListView<T> param) {

                                            return new TextFieldListCell<>(
                                                    ListViewWithSearchPanel.this
                                                            .getStringConverter());
                                        }
                                    });
                        }
                        else {
                            ListViewWithSearchPanel.this.listView
                                    .setCellFactory(new Callback<ListView<T>, ListCell<T>>() {

                                        @Override
                                        public ListCell<T> call(
                                                ListView<T> param) {

                                            return new TextFieldListCell<>();
                                        }
                                    });
                        }

                        ListViewWithSearchPanel.this.update();
                    }
                });



        this.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        final KeyCombination keyComb1 = new KeyCodeCombination(
                                KeyCode.F, KeyCombination.SHORTCUT_DOWN);

                        if (keyComb1.match(event)) {

                            searchPanel.setVisible(true);
                            searchPanel.requestFocus();
                        }
                    }

                });



        this.searchPanel.visibleProperty().addListener(
                new ChangeListener<Boolean>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> observable,
                            Boolean oldValue, Boolean newValue) {

                        if (Boolean.FALSE.equals(newValue)) {

                            ListViewWithSearchPanel.this.getChildren().remove(
                                    ListViewWithSearchPanel.this.searchPanel);
                        }
                        else if (Boolean.TRUE.equals(newValue)
                                && !ListViewWithSearchPanel.this.getChildren()
                                        .contains(searchPanel)) {

                            ListViewWithSearchPanel.this.getChildren().add(
                                    ListViewWithSearchPanel.this.searchPanel);
                        }

                    }
                });



        this.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED,
                new EventHandler<ContextMenuEvent>() {

                    @Override
                    public void handle(ContextMenuEvent event) {

                        ListViewWithSearchPanel.this.contextMenu.show(
                                ListViewWithSearchPanel.this,
                                event.getScreenX(), event.getScreenY());
                    }
                });



        this.items().addListener(new ListChangeListener<T>() {

            @Override
            public void onChanged(Change<? extends T> change) {

                ListViewWithSearchPanel.this.update();
            }
        });


        this.searchPanel.filterString().addListener(
                new ChangeListener<String>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

                        ListViewWithSearchPanel.this.update();
                    }
                });

    }


    /**
     * Updates the list view after a change.
     */
    public void update() {

        this.filteredItems.clear();

        if (!this.items.isEmpty()) {

            List<T> selectedItems = new ArrayList<>(
                    ListViewWithSearchPanel.this.getSelectedItems());

            String filterString = this.searchPanel.getFilterString();


            if (filterString == null || "".equals(filterString)) {

                this.filteredItems.addAll(this.items);

            }
            else {

                for (T entry : this.items) {

                    if (this.toString(entry).toLowerCase()
                            .contains(filterString.toLowerCase())) {

                        this.filteredItems.add(entry);
                    }
                }
            }

            this.clearSelection();

            for (T selectedItem : selectedItems) {

                this.getSelectionModel().select(selectedItem);
            }
        }

    }

    /**
     * Adds an event handler to the nested content of this class.
     * 
     * @param eventType
     *            the event type.
     * @param eventHandler
     *            the event handler.
     */
    public <S extends Event> void addEventHandlerForContent(
            EventType<S> eventType, EventHandler<S> eventHandler) {

        this.listView.addEventHandler(eventType, eventHandler);
    }

    /**
     * Adds an event filter to the nested content of this class.
     * 
     * @param eventType
     *            the event type.
     * @param eventFilter
     *            the event filter.
     */
    public <S extends Event> void addEventFilterForContent(
            EventType<S> eventType, EventHandler<S> eventFilter) {

        this.listView.addEventFilter(eventType, eventFilter);
    }


    private String toString(T value) {

        if (this.stringConverter.get() != null) {

            return this.stringConverter.get().toString(value);
        }
        else if (value != null) {

            return value.toString();
        }

        return null;
    }

}
