package org.jutility.javafx.control;

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



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;



/**
 * creates the box for choosing how which display properties to set meta data or
 * properties
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * @param <T>
 *            the contained type.
 * 
 */
public class LabeledComboBox<T>
        extends VBox {

    private final ValidationSupport                  validationSupport;
    private final ComboBox<T>                        comboBox;
    private final Label                              label;

    private final ObjectProperty<StringConverter<T>> stringConverter;

    private final ObservableList<T>                  items;
    private ContextMenu                              contextMenu;
    private final ObservableList<Action>             contextMenuActions;


    /**
     * Returns whether or not the state of the Control is valid.
     * 
     * @return {@code true}, if the state of the Control is valid; {@code false}
     *         otherwise.
     */
    public boolean isInvalid() {

        return this.validationSupport.isInvalid();
    }

    /**
     * Returns the invalid property of the control.
     * 
     * @return the invalid property of the control.
     */
    public ReadOnlyObjectProperty<Boolean> invalidProperty() {

        return this.validationSupport.invalidProperty();
    }

    /**
     * Sets the font of the label.
     * 
     * @param font
     *            the font of the label.
     */
    public void setLabelFont(Font font) {

        this.label.setFont(font);
    }

    /**
     * Sets the value of the label's style.
     * 
     * @param value
     *            the value of the label's style.
     */
    public void setLabelStyle(String value) {

        this.label.setStyle(value);
    }

    /**
     * Registers {@link Validator} for specified control with additional
     * possibility to mark control as required or not.
     * 
     * @param required
     *            true if controls should be required
     * @param validator
     *            {@link Validator} to be used
     * @return true if registration is successful
     */
    public boolean registerValidator(boolean required,
            final Validator<T> validator) {

        return this.validationSupport.registerValidator(this.comboBox,
                required, validator);
    }

    /**
     * Registers {@link Validator} for the control and makes control required
     * 
     * @param validator
     *            {@link Validator} to be used
     * @return true if registration is successful
     */
    public boolean registerValidator(final Validator<T> validator) {

        return registerValidator(true, validator);
    }

    /**
     * Creates a new {@link LabeledComboBox} with the provided title.
     * 
     * @param title
     *            the title of this panel.
     */
    public LabeledComboBox(String title) {

        this(title, null);
    }

    /**
     * Creates a new {@link LabeledComboBox} with the provided title.
     * 
     * @param title
     *            title of this panel
     * @param stringConverter
     *            the string converter to use.
     */
    public LabeledComboBox(String title, StringConverter<T> stringConverter) {

        this.validationSupport = new ValidationSupport();

        this.comboBox = new ComboBox<>();
        this.comboBox.setMaxWidth(Double.MAX_VALUE);
        this.label = new Label(title);
        this.label.setFont(Font.font("verdana", 16));
        this.label.setLabelFor(comboBox);


        this.items = FXCollections.observableList(new LinkedList<T>());

        Bindings.bindContentBidirectional(this.comboBox.getItems(), this.items);

        GridPane.setHgrow(label, Priority.ALWAYS);
        GridPane.setHgrow(comboBox, Priority.ALWAYS);

        this.getChildren().addAll(this.label, this.comboBox);


        this.contextMenu = new ContextMenu();
        this.contextMenuActions = FXCollections
                .observableList(new LinkedList<Action>());

        this.stringConverter = new SimpleObjectProperty<>();

        this.setupEventHandlers();

        this.stringConverter.set(stringConverter);

        this.validationSupport
                .setValidationDecorator(new GraphicValidationDecoration());
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

        this.getSelectionModel().clearSelection();
    }

    /**
     * Returns the selection model.
     * 
     * @return the selection model.
     */
    public SingleSelectionModel<T> getSelectionModel() {


        return this.comboBox.getSelectionModel();
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

                LabeledComboBox.this.contextMenu.getItems().clear();

                LabeledComboBox.this.contextMenu = ActionUtils
                        .createContextMenu(LabeledComboBox.this.contextMenuActions);
            }
        });



        this.stringConverter
                .addListener(new ChangeListener<StringConverter<T>>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends StringConverter<T>> observable,
                            StringConverter<T> oldValue,
                            StringConverter<T> newValue) {

                        Callback<ListView<T>, ListCell<T>> cellFactory = null;
                        if (newValue != null) {

                            cellFactory = new Callback<ListView<T>, ListCell<T>>() {

                                @Override
                                public ListCell<T> call(ListView<T> param) {

                                    return new TextFieldListCell<>(
                                            LabeledComboBox.this
                                                    .getStringConverter());
                                }
                            };


                        }
                        else {
                            cellFactory = new Callback<ListView<T>, ListCell<T>>() {

                                @Override
                                public ListCell<T> call(ListView<T> param) {

                                    return new TextFieldListCell<>();
                                }
                            };
                        }

                        LabeledComboBox.this.comboBox
                                .setCellFactory(cellFactory);
                        LabeledComboBox.this.comboBox.setButtonCell(cellFactory
                                .call(null));

                        LabeledComboBox.this.update();
                    }
                });



        this.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED,
                new EventHandler<ContextMenuEvent>() {

                    @Override
                    public void handle(ContextMenuEvent event) {

                        LabeledComboBox.this.contextMenu.show(
                                LabeledComboBox.this, event.getScreenX(),
                                event.getScreenY());
                    }
                });



    }

    /**
     * Updates the combo box after a change.
     */
    public void update() {

        T selectedItem = this.getSelectedItem();

        List<T> backup = new ArrayList<>(this.items);

        this.items.clear();
        this.items.addAll(backup);
        this.getSelectionModel().select(selectedItem);
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

        this.comboBox.addEventHandler(eventType, eventHandler);
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

        this.comboBox.addEventFilter(eventType, eventFilter);
    }


    @Override
    public void requestFocus() {

        this.comboBox.requestFocus();
    }
}
