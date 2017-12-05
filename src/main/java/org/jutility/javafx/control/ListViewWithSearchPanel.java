package org.jutility.javafx.control;


// @formatter:off
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


import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
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
 * The {@code ListViewWithSearchPanel} class provides a {@link LabeledListView}
 * with a {@link SearchPanel}.
 *
 * @param <T>
 *         the content type of the {@link LabeledListView}.
 *
 * @author Shawn P. Neuman, Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class ListViewWithSearchPanel<T>
        extends LabeledListView<T> {

    private static final Logger LOG = LoggerFactory.getLogger(
            ListViewWithSearchPanel.class);

    private final ObjectProperty<ObservableList<T>> itemsProperty;
    private final ObjectProperty<ObservableList<T>> filteredItemsProperty;

    private final SearchPanel<T> searchPanel;


    @Override
    public ObjectProperty<ObservableList<T>> itemsProperty() {

        return this.itemsProperty;
    }

    @Override
    public ObservableList<T> getItems() {

        return this.itemsProperty.get();
    }

    @Override
    public void setItems(final ObservableList<T> value) {

        this.itemsProperty.set(value);
    }

    /**
     * Creates a new {@code ListViewWithSearchPanel} with no title.
     */
    public ListViewWithSearchPanel() {

        this(null);
    }

    /**
     * Creates a new {@code ListViewWithSearchPanel} with the provided title.
     *
     * @param title
     *         title of this panel
     */
    public ListViewWithSearchPanel(final String title) {

        this(title, null);
    }


    /**
     * Creates a new {@code ListViewWithSearchPanel} with the provided title.
     *
     * @param title
     *         title of this panel
     * @param converter
     *         the string converter to use.
     */
    public ListViewWithSearchPanel(final String title,
            final StringConverter<T> converter) {

        this(null, title, converter);
    }

    /**
     * Creates a new {@code ListViewWithSearchPanel} with the provided title.
     *
     * @param items
     *         the initial item list of the {@link ListView}
     * @param title
     *         title of this panel
     * @param converter
     *         the string converter to use.
     */
    public ListViewWithSearchPanel(final ObservableList<T> items,
            final String title, final StringConverter<T> converter) {

        super(null, title, Pos.TOP_CENTER, converter);


        this.itemsProperty = new SimpleObjectProperty<>(items);
        if (items == null) {

            this.setItems(FXCollections.observableArrayList());
        }
        this.filteredItemsProperty = new SimpleObjectProperty<>();

        this.filteredItemsProperty.bindBidirectional(super.itemsProperty());

        this.searchPanel = new SearchPanel<>();

        this.searchPanel.setVisible(false);

        this.searchPanel.getStringFilter()
                        .converterProperty()
                        .bind(this.converterProperty());

        this.updateFilteredItems();

        this.setupEventHandlers();
    }



    /**
     * Clears the list view.
     */
    public void clear() {

        this.getItems()
            .clear();
        this.clearSelection();
    }


    /**
     * Clears the selection.
     */
    public void clearSelection() {

        this.getSelectionModel()
            .clearSelection();
    }

    /**
     * Returns the selected item property.
     *
     * @return the selected item property.
     */
    public ReadOnlyObjectProperty<T> selectedItemProperty() {

        return this.getSelectionModel()
                   .selectedItemProperty();
    }

    /**
     * Returns the selected item.
     *
     * @return the selected item from the list view
     */
    public T getSelectedItem() {

        return this.getSelectionModel()
                   .getSelectedItem();
    }

    /**
     * Returns the selected index property.
     *
     * @return the selected index property.
     */
    public ReadOnlyIntegerProperty selectedIndexProperty() {

        return this.getSelectionModel()
                   .selectedIndexProperty();
    }

    /**
     * Returns the selected index.
     *
     * @return the selected index from the list view
     */
    public int getSelectedIndex() {

        return this.getSelectionModel()
                   .getSelectedIndex();
    }

    /**
     * Returns the selected filteredItems.
     *
     * @return the selected filteredItems from the list view
     */
    public ObservableList<T> getSelectedItems() {

        return this.getSelectionModel()
                   .getSelectedItems();
    }

    /**
     * Returns the selected indices.
     *
     * @return the selected indices from the list view
     */
    public ObservableList<Integer> getSelectedIndices() {

        return this.getSelectionModel()
                   .getSelectedIndices();
    }

    /**
     * Removes the provided elements from the {@link ListView}.
     *
     * @param elements
     *         the elements to remove.
     */
    @SafeVarargs
    public final void removeAll(final T... elements) {

        this.getItems()
            .removeAll(elements);
    }

    /**
     * Removes the element at the provided index from the {@link ListView}.
     *
     * @param index
     *         the index of the string value to be removed from the {@link
     *         ListView}.
     */
    public void remove(final int index) {

        this.getItems()
            .remove(index);
    }

    /**
     * Removes the provided item from the {@link ListView}.
     *
     * @param item
     *         the item to remove.
     */
    public void remove(final T item) {

        this.getItems()
            .remove(item);
    }



    private void setupEventHandlers() {


        this.itemsProperty()
            .addListener(
                    (observable, oldValue, newValue) -> this
                            .updateFilteredItems());

        this.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {

            final KeyCombination acceleratorKey = new KeyCodeCombination(
                    KeyCode.F, KeyCombination.SHORTCUT_DOWN);

            if (acceleratorKey.match(event) && (this.getItems() != null)
                    && !this.getItems()
                            .isEmpty()) {

                this.searchPanel.setVisible(true);
                this.searchPanel.requestFocus();
            }
        });



        this.searchPanel.visibleProperty()
                        .addListener((observable, oldValue, newValue) -> {

                            if (Boolean.FALSE.equals(newValue)) {

                                ListViewWithSearchPanel.LOG.debug(
                                        "Removing search panel.");
                                this.setBottomCenterNode(null);
                                this.getChildren()
                                    .remove(this.searchPanel);
                                this.requestFocus();
                            }
                            else if (Boolean.TRUE.equals(newValue)
                                    && !this.getChildren()
                                            .contains(this.searchPanel)) {

                                ListViewWithSearchPanel.LOG.debug(
                                        "Adding search panel.");
                                this.addNode(this.searchPanel,
                                        Pos.BOTTOM_CENTER);
                            }
                        });

        this.searchPanel.getStringFilter()
                        .filterStringProperty()
                        .addListener((final Observable observable) -> {

                            ListViewWithSearchPanel.LOG.debug(
                                    "FilterString invalidated!");
                            final ObservableList<T> filteredItems = this
                                    .filteredItemsProperty.get();

                            if ((filteredItems != null)
                                    && (filteredItems instanceof
                                    FilteredList<?>)) {

                                final FilteredList<T> filteredList = (
                                        (FilteredList<T>) filteredItems);
                                ListViewWithSearchPanel.LOG.debug(
                                        "Invalidating predicate");
                                filteredList.setPredicate(t -> true);
                                filteredList.setPredicate(
                                        this.searchPanel.getStringFilter());
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
