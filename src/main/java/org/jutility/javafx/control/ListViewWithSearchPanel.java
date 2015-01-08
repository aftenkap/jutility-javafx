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


import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import org.jutility.javafx.control.labeled.LabeledListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Provides a ListView with a Title and SearchPanel for the provided type.
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * @version 0.1.2
 * @since 0.0.1
 * @param <T>
 *            the content type of the {@link ListView}.
 * 
 */
public class ListViewWithSearchPanel<T>
        extends LabeledListView<T> {

    private static Logger                           LOG = LoggerFactory
                                                                .getLogger(ListViewWithSearchPanel.class);

    private final ObjectProperty<ObservableList<T>> itemsProperty;
    private final ObjectProperty<ObservableList<T>> filteredItemsProperty;

    private final SearchPanel<T>                    searchPanel;


    @Override
    public ObjectProperty<ObservableList<T>> itemsProperty() {

        return this.itemsProperty;
    }

    @Override
    public ObservableList<T> getItems() {

        return this.itemsProperty.get();
    }

    @Override
    public void setItems(ObservableList<T> value) {

        this.itemsProperty.set(value);
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
     * @param converter
     *            the string converter to use.
     */
    public ListViewWithSearchPanel(final String title,
            StringConverter<T> converter) {

        this(null, converter, null);
    }

    /**
     * Creates a new {@link ListViewWithSearchPanel} with the provided title.
     * 
     * @param items
     *            the initial item list of the {@link ListView}
     * 
     * @param title
     *            title of this panel
     * @param converter
     *            the string converter to use.
     */
    public ListViewWithSearchPanel(final ObservableList<T> items,
            StringConverter<T> converter, final String title) {

        super(null, converter, title, Position.NORTH);


        this.itemsProperty = new SimpleObjectProperty<>(items);
        if (items == null) {

            this.setItems(FXCollections.observableArrayList());
        }
        this.filteredItemsProperty = new SimpleObjectProperty<>();

        this.filteredItemsProperty.bindBidirectional(super.itemsProperty());

        this.searchPanel = new SearchPanel<>();

        this.searchPanel.setVisible(false);

        this.updateFilteredItems();

        this.setupEventHandlers();
    }



    /**
     * Clears the list view.
     */
    public void clear() {

        this.getItems().clear();
        this.clearSelection();
    }


    /**
     * Clears the selection.
     */
    public void clearSelection() {

        this.getSelectionModel().clearSelection();
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

        this.getItems().removeAll(elements);
    }

    /**
     * @param index
     *            the index of the string value to be removed from the list
     */
    public void remove(int index) {

        this.getItems().remove(index);
    }

    /**
     * @param item
     *            the item to be removed
     */
    public void removeAll(Object item) {

        this.getItems().remove(item);
    }



    private void setupEventHandlers() {


        this.itemsProperty().addListener((observable, oldValue, newValue) -> {

            this.updateFilteredItems();

        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {

            final KeyCombination acceleratorKey = new KeyCodeCombination(
                    KeyCode.F, KeyCombination.SHORTCUT_DOWN);

            if (acceleratorKey.match(event)) {

                this.searchPanel.setVisible(true);
                this.searchPanel.requestFocus();
            }

        });



        this.searchPanel.visibleProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (Boolean.FALSE.equals(newValue)) {

                        LOG.debug("Removing search panel.");
                        this.setSouth(null);
                        this.getChildren().remove(this.searchPanel);
                    }
                    else if (Boolean.TRUE.equals(newValue)
                            && !this.getChildren().contains(this.searchPanel)) {

                        LOG.debug("Adding search panel.");
                        this.addNode(this.searchPanel, Position.SOUTH);
                    }
                });

        this.searchPanel
                .getStringFilter()
                .filterStringProperty()
                .addListener(
                        (Observable observable) -> {

                            LOG.debug("FilterString invalidated!");
                            ObservableList<T> filteredItems = this.filteredItemsProperty
                                    .get();

                            LOG.debug("filteredItems is null? "
                                    + (filteredItems == null));
                            LOG.debug("filteredItems is FilteredList<?>? "
                                    + (filteredItems instanceof FilteredList<?>));

                            if (filteredItems != null
                                    && filteredItems instanceof FilteredList<?>) {

                                FilteredList<T> filteredList = ((FilteredList<T>) filteredItems);
                                LOG.debug("Invalidating predicate");
                                filteredList.setPredicate(t -> true);
                                filteredList.setPredicate(this.searchPanel
                                        .getStringFilter());
                            }
                        });

    }

    private void updateFilteredItems() {

        this.filteredItemsProperty.set(null);

        if (this.getItems() != null) {

            this.filteredItemsProperty.set(new FilteredList<>(this.getItems(),
                    this.searchPanel.getStringFilter()));
        }
    }
}
