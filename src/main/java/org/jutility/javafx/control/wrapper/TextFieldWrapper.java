package org.jutility.javafx.control.wrapper;


//@formatter:off
/*
<<<<<<< HEAD
* #%L
=======
 * #%L
>>>>>>> 928a61bab212b1149b91f977a88fc188d2e980ff
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
<<<<<<< HEAD
*/
=======
 */
>>>>>>> 928a61bab212b1149b91f977a88fc188d2e980ff
//@formatter:on


import javafx.beans.DefaultProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;



/**
 * The {@code TextFieldWrapper} provides a {@link ControlWrapper Wrapper} around
 * a {@link TextField}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@DefaultProperty(value = "text")
public class TextFieldWrapper
        extends TextInputControlWrapper<TextField> {



    /**
     * Specifies how the text should be aligned when there is empty space within
     * the {@link TextField}.
     *
     * @see TextField#getAlignment()
     * @see TextField#setAlignment(Pos)
     * @return the text alignment property.
     */
    public final ObjectProperty<Pos> textAlignmentProperty() {

        return this.getWrappedControl().alignmentProperty();
    }

    /**
     * The action handler associated with this text field, or null if no action
     * handler is assigned.
     *
     * The action handler associated with this text field, or null if no action
     * handler is assigned. The action handler is normally called when the user
     * types the ENTER key.
     *
     * @see TextField#getOnAction() @see TextField#setOnAction(EventHandler)
     *
     * @return the on action property.
     */
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {

        return this.getWrappedControl().onActionProperty();
    }

    /**
     * The preferred number of text columns.
     *
     * @return the preferred column count property.
     * @see TextField#getPrefColumnCount()
     * @see TextField#setPrefColumnCount(int)
     * @see TextField#getPrefColumnCount
     */
    public final IntegerProperty prefColumnCountProperty() {

        return this.getWrappedControl().prefColumnCountProperty();
    }



    /**
     * Creates a new instance of the {@code TextFieldWrapper} class.
     */
    public TextFieldWrapper() {

        this(null);

    }

    /**
     * Creates a new instance of the {@code TextFieldWrapper} class.
     *
     * @param text
     *            the initial text content of the {@link TextField}.
     */
    public TextFieldWrapper(final String text) {

        super(text == null ? new TextField() : new TextField(text));

        GridPane.setHgrow(this.getWrappedControl(), Priority.SOMETIMES);
    }



    /**
     * Returns the character sequence backing the text field's content.
     * prefColumnCountProperty
     *
     * @return the character sequence backing the text field's content.
     *         prefColumnCountProperty
     */
    public CharSequence getCharacters() {

        return this.getWrappedControl().getCharacters();
    }


    /**
     * Gets the value of the property prefColumnCount.
     *
     * @return The preferred number of text columns. This is used for
     *         calculating the TextField's preferred width.
     */
    public final int getPrefColumnCount() {

        return this.getWrappedControl().getPrefColumnCount();
    }


    /**
     * Sets the value of the property prefColumnCount.
     *
     * @param value
     *            The preferred number of text columns. This is used for
     *            calculating the TextField's preferred width.
     */
    public final void setPrefColumnCount(final int value) {

        this.getWrappedControl().setPrefColumnCount(value);
    }



    /**
     * Gets the value of the property onAction.
     *
     * @return The action handler associated with this text field, or null if no
     *         action handler is assigned. The action handler is normally called
     *         when the user types the ENTER key.
     */
    public final EventHandler<ActionEvent> getOnAction() {

        return this.getWrappedControl().getOnAction();
    }

    /**
     * Sets the value of the property onAction.
     *
     * @param value
     *            The action handler associated with this text field, or null if
     *            no action handler is assigned. The action handler is normally
     *            called when the user types the ENTER key.
     */
    public final void setOnAction(final EventHandler<ActionEvent> value) {

        this.getWrappedControl().setOnAction(value);
    }

    /**
     * Gets the value of the property alignment.
     *
     * @return Specifies how the text should be aligned when there is empty
     *         space within the TextField.
     */
    public final Pos getTextAlignment() {

        return this.getWrappedControl().getAlignment();
    }

    /**
     * Sets the value of the property alignment.
     *
     * @param value
     *            Specifies how the text should be aligned when there is empty
     *            space within the TextField.
     */
    public final void setTextAlignment(final Pos value) {

        this.getWrappedControl().setAlignment(value);
    }
}
