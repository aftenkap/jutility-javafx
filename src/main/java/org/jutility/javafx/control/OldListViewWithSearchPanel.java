package org.jutility.javafx.control;


// @formatter:off
/*
 * #%L 
 * jutility-javafx 
 * %% 
 * Copyright (C) 2013 - 2014 jutility.org 
 * %% 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * #L%
 */

//@formatter:on


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
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

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;



/**
 * Provides a ListView with a Title and SearchPanel for the provided type.
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * @version 0.1.2
 * @since 0.0.1
 * @param <T>
 *            the content type of the {@link ListView}.
 * @deprecated
 */
public class OldListViewWithSearchPanel<T>
        extends VBox {

    private final Label                              title;
    private final ListView<T>                        listView;

    private final ObservableList<T>                  items;
    private final ObservableList<T>                  filteredItems;

    private final ObjectProperty<StringConverter<T>> stringConverter;


    private final SearchPanel<T>                        searchPanel;

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
     * Creates a new {@link OldListViewWithSearchPanel} with no title.
     */
    public OldListViewWithSearchPanel() {

        this(null);
    }

    /**
     * Creates a new {@link OldListViewWithSearchPanel} with the provided title.
     * 
     * @param title
     *            title of this panel
     */
    public OldListViewWithSearchPanel(final String title) {

        this(title, null);
    }


    /**
     * Creates a new {@link OldListViewWithSearchPanel} with the provided title.
     * 
     * @param title
     *            title of this panel
     * @param stringConverter
     *            the string converter to use.
     */
    public OldListViewWithSearchPanel(final String title,
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


        this.searchPanel = new SearchPanel<>();
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

        this.listView.getSelectionModel().clearSelection();
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



        this.contextMenuActions
                .addListener((Change<? extends Action> change) -> {

                    this.contextMenu.getItems().clear();

                    this.contextMenu = ActionUtils
                            .createContextMenu(this.contextMenuActions);
                });



        this.stringConverter.addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                this.listView.setCellFactory((param) -> {

                    return new TextFieldListCell<>(this.getStringConverter());
                });
            }
            else {
                this.listView.setCellFactory((param) -> {

                    return new TextFieldListCell<>();
                });
            }

            this.update();
        });



        this.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {

            final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.F,
                    KeyCombination.SHORTCUT_DOWN);

            if (keyComb1.match(event)) {

                this.searchPanel.setVisible(true);
                this.searchPanel.requestFocus();
            }

        });



        this.searchPanel.visibleProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (Boolean.FALSE.equals(newValue)) {

                        this.getChildren().remove(this.searchPanel);
                    }
                    else if (Boolean.TRUE.equals(newValue)
                            && !this.getChildren().contains(this.searchPanel)) {

                        this.getChildren().add(this.searchPanel);
                    }
                });



        this.addEventHandler(
                ContextMenuEvent.CONTEXT_MENU_REQUESTED,
                (event) -> {

                    this.contextMenu.show(this, event.getScreenX(),
                            event.getScreenY());
                });



        this.items().addListener((Change<? extends T> change) -> {

            this.update();
        });


        this.searchPanel.getStringFilter().filterStringProperty().addListener(
                (observable, oldValue, newValue) -> {

                    this.update();
                });

    }

    /**
     * Updates the list view after a change.
     */
    public void update() {

        this.filteredItems.clear();

        if (!this.items.isEmpty()) {

            List<T> selectedItems = new ArrayList<>(this.getSelectedItems());

            String filterString = this.searchPanel.getStringFilter().getFilterString();


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
