package org.jutility.javafx.control.wrapper;


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

import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Font;


/**
 * The abstract {@code TextInputControlWrapper} class provides a base class for
 * {@link ControlWrapper Wrappers} of {@link TextInputControl TextInputControls}
 * .
 *
 * @param <T>
 *            the type of the {@link TextInputControl}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 *
 */
@DefaultProperty(value = "text")
public abstract class TextInputControlWrapper<T extends TextInputControl>
        extends ControlWrapper<T> {

    /**
     * The anchor of the text selection.
     *
     * @return the anchor of the text selection.
     */
    public ReadOnlyIntegerProperty anchorProperty() {

        return this.getWrappedControl().anchorProperty();
    }

    /**
     * Gets the value of the property anchor.
     *
     * @return The anchor of the text selection. The anchor and caretPosition
     *         make up the selection range. Selection must always be specified
     *         in terms of begin &le; end, but anchor may be less than, equal to,
     *         or greater than the caretPosition. Depending on how the user
     *         selects text, the anchor might represent the lower or upper bound
     *         of the selection.
     */
    public int getAnchor() {

        return this.getWrappedControl().getAnchor();
    }

    /**
     * The current position of the caret within the text.
     *
     * @return the current position of the caret within the text.
     */
    public ReadOnlyIntegerProperty caretPositionProperty() {

        return this.getWrappedControl().caretPositionProperty();
    }


    /**
     * Gets the value of the property caretPosition.
     *
     * @return The current position of the caret within the text. The anchor and
     *         caretPosition make up the selection range. Selection must always
     *         be specified in terms of begin &le; end, but anchor may be less
     *         than, equal to, or greater than the caretPosition. Depending on
     *         how the user selects text, the caretPosition might represent the
     *         lower or upper bound of the selection.
     */
    public int getCaretPosition() {

        return this.getWrappedControl().getCaretPosition();
    }


    /**
     * Indicates whether this TextInputControl can be edited by the user.
     *
     * @return {@code true}, if this TextInputControl can be edited by the user;
     *         {@code false} otherwise.
     */
    public BooleanProperty editableProperty() {

        return this.getWrappedControl().editableProperty();
    }

    /**
     * Gets the value of the property editable.
     *
     * @return {@code true}, if the {@link TextField} is editable; {@code false}
     *         otherwise.
     */
    public boolean isEditable() {

        return this.getWrappedControl().isEditable();
    }

    /**
     * Sets the value of the property editable.
     *
     * @param value
     *            the new value.
     */
    public void setEditable(final boolean value) {

        this.getWrappedControl().setEditable(value);
    }

    /**
     * The default font to use for text in the TextInputControl.
     *
     * @return the default font to use for text in the TextInputControl.
     */
    public ObjectProperty<Font> fontProperty() {

        return this.getWrappedControl().fontProperty();
    }

    /**
     * Returns the default font to use for text in the TextInputControl.
     *
     * @return the default font to use for text in the TextInputControl.
     */
    public Font getFont() {

        return this.getWrappedControl().getFont();
    }

    /**
     * Sets the default font to use for text in the TextInputControl.
     *
     * @param value
     *            the default font to use for text in the TextInputControl.
     */
    public void setFont(final Font value) {

        this.getWrappedControl().setFont(value);
    }

    /**
     * The number of characters in the text input.
     *
     * @return the number of characters in the text input.
     */
    public ReadOnlyIntegerProperty lengthProperty() {

        return this.getWrappedControl().lengthProperty();
    }


    /**
     * Gets the value of the property length.
     *
     * @return the value of the property length.
     */
    public int getLength() {

        return this.getWrappedControl().getLength();
    }

    /**
     * The prompt text to display in the TextField, or {@code null} if no prompt
     * text is displayed.
     *
     * @return the prompt text to display.
     */
    public StringProperty promptTextProperty() {

        return this.getWrappedControl().promptTextProperty();
    }


    /**
     * Gets the value of the property promptText.
     *
     * @return the value of the property promptText.
     */
    public String getPromptText() {

        return this.getWrappedControl().getPromptText();
    }


    /**
     * Sets the value of the property promptText.
     *
     * @param value
     *            the new prompt text.
     */
    public void setPromptText(final String value) {

        this.getWrappedControl().setPromptText(value);
    }

    /**
     * Defines the characters in the TextInputControl which are selected.
     *
     * @return the characters in the TextInputControl which are selected
     */
    public ReadOnlyStringProperty selectedTextProperty() {

        return this.getWrappedControl().selectedTextProperty();
    }

    /**
     * Gets the value of the property selectedText.
     *
     * @return the value of the property selectedText.
     */
    public String getSelectedText() {

        return this.getWrappedControl().getSelectedText();
    }

    /**
     * The current selection.
     *
     * @return the current selection.
     */
    public final ReadOnlyObjectProperty<IndexRange> selectionProperty() {

        return this.getWrappedControl().selectionProperty();
    }

    /**
     * Gets the value of the property selection.
     *
     * @return the value of the property selection.
     */
    public IndexRange getSelection() {

        return this.getWrappedControl().getSelection();
    }

    /**
     * The textual content of this TextInputControl.
     *
     * @return the textual content of this TextInputControl.
     */
    public final StringProperty textProperty() {

        return this.getWrappedControl().textProperty();
    }


    /**
     * Gets the value of the property text.
     *
     * @return the value of the property text.
     */
    public String getText() {

        return this.getWrappedControl().getText();
    }


    /**
     * Sets the value of the property text.
     *
     * @param value
     *            the new text value.
     */
    public void setText(final String value) {

        this.getWrappedControl().setText(value);
    }



    /**
     * Creates a new instance of the {@code TextInputControlWrapper} class.
     *
     * @param wrappedControl
     *            the control to wrap.
     */
    public TextInputControlWrapper(final T wrappedControl) {

        super(wrappedControl);
    }



    /**
     * Appends a sequence of characters to the content.
     *
     * @param text
     *            the text to append.
     */
    public void appendText(final String text) {

        this.getWrappedControl().appendText(text);
    }


    /**
     * Moves the caret position backward.
     */
    public void backward() {

        this.getWrappedControl().backward();
    }



    /**
     * Clears the text.
     */
    public void clear() {

        this.getWrappedControl().clear();
    }


    /**
     * Transfers the currently selected range in the text to the clipboard,
     * leaving the current selection.
     */
    public void copy() {

        this.getWrappedControl().copy();
    }

    /**
     * Transfers the currently selected range in the text to the clipboard,
     * removing the current selection.
     */
    public void cut() {

        this.getWrappedControl().cut();
    }

    /**
     * Deletes the character that follows the current caret position from the
     * text if there is no selection, or deletes the selection if there is one.
     *
     * @return Deletes the character that follows the current caret position
     *         from the text if there is no selection, or deletes the selection
     *         if there is one. This function returns true if the deletion
     *         succeeded, false otherwise.
     */
    public boolean deleteNextChar() {

        return this.getWrappedControl().deleteNextChar();
    }

    /**
     * Deletes the character that precedes the current caret position from the
     * text if there is no selection, or deletes the selection if there is one.
     *
     * @return Deletes the character that precedes the current caret position
     *         from the text if there is no selection, or deletes the selection
     *         if there is one. This function returns true if the deletion
     *         succeeded, false otherwise.
     */
    public boolean deletePreviousChar() {

        return this.getWrappedControl().deletePreviousChar();
    }

    /**
     * Removes a range of characters from the content.
     *
     * @param range
     *            the range to delete.
     */
    public void deleteText(final IndexRange range) {

        this.getWrappedControl().deleteText(range);
    }

    /**
     * Removes a range of characters from the content.
     *
     * @param start
     *            the start index.
     * @param end
     *            the end index.
     */
    public void deleteText(final int start, final int end) {

        this.getWrappedControl().deleteText(start, end);
    }

    /**
     * Clears the selection.
     */
    public void deselect() {

        this.getWrappedControl().deselect();
    }


    /**
     * Moves the caret to after the last char of the text.
     */
    public void end() {

        this.getWrappedControl().end();
    }

    /**
     * Moves the caret to the end of the next word.
     */
    public void endOfNextWord() {

        this.getWrappedControl().endOfNextWord();
    }


    /**
     * This function will extend the selection to include the specified
     * position.
     *
     * @param pos
     *            the position
     */
    public void extendSelection(final int pos) {

        this.getWrappedControl().extendSelection(pos);
    }


    /**
     * Moves the caret position forward.
     */
    public void forward() {

        this.getWrappedControl().forward();
    }

    /**
     * Returns the control's CSS metadata.
     *
     * @return the control's CSS metadata.
     */
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {

        return this.getWrappedControl().getControlCssMetaData();
    }

    /**
     * Returns a subset of the text input's content.
     *
     * @param start
     *            the start index.
     * @param end
     *            the end index.
     * @return a subset of the text input's content.
     */
    public String getText(final int start, final int end) {

        return this.getWrappedControl().getText(start, end);
    }


    /**
     * Moves the caret to before the first char of the text.
     */
    public void home() {

        this.getWrappedControl().home();
    }


    /**
     * Inserts a sequence of characters into the content.
     *
     * @param index
     *            the insertion index.
     * @param text
     *            the text to insert.
     */
    public void insertText(final int index, final String text) {

        this.getWrappedControl().insertText(index, text);
    }



    /**
     * Moves the caret to the beginning of next word.
     */
    public void nextWord() {

        this.getWrappedControl().nextWord();
    }

    /**
     * Transfers the contents in the clipboard into this text, replacing the
     * current selection.
     */
    public void paste() {

        this.getWrappedControl().paste();
    }

    /**
     * Positions the caret to the position indicated by pos.
     *
     * @param pos
     *            the new caret position.
     */
    public void positionCaret(final int pos) {

        this.getWrappedControl().positionCaret(pos);
    }

    /**
     * Moves the caret to the beginning of previous word.
     */
    public void previousWord() {

        this.getWrappedControl().previousWord();
    }


    /**
     * Replaces the selection with the given replacement String.
     *
     * @param replacement
     *            the replacement String.
     */
    public void replaceSelection(final String replacement) {

        this.getWrappedControl().replaceSelection(replacement);
    }

    /**
     * Replaces a range of characters with the given text.
     *
     * @param range
     *            the range.
     * @param text
     *            the replacement text.
     */
    public void replaceText(final IndexRange range, final String text) {

        this.getWrappedControl().replaceText(range, text);
    }

    /**
     * Replaces a range of characters with the given text.
     *
     * @param start
     *            the start index of the range.
     * @param end
     *            the end index of the range.
     * @param text
     *            the replacement text.
     */
    public void replaceText(final int start, final int end, final String text) {

        this.getWrappedControl().replaceText(start, end, text);
    }

    /**
     * Selects all text in the text input.
     */
    public void selectAll() {

        this.getWrappedControl().selectAll();
    }

    /**
     * Moves the selection backward one char in the text.
     */
    public void selectBackward() {

        this.getWrappedControl().selectBackward();
    }

    /**
     * Moves the caret to after the last char of text.
     */
    public void selectEnd() {

        this.getWrappedControl().selectEnd();
    }

    /**
     * Moves the caret to the end of the next word.
     */
    public void selectEndOfNextWord() {

        this.getWrappedControl().selectEndOfNextWord();
    }

    /**
     * Moves the selection forward one char in the text.
     */
    public void selectForward() {

        this.getWrappedControl().selectForward();
    }

    /**
     * Moves the caret to before the first char of text.
     */
    public void selectHome() {

        this.getWrappedControl().selectHome();
    }

    /**
     * Moves the caret to the beginning of next word.
     */
    public void selectNextWord() {

        this.getWrappedControl().selectNextWord();
    }

    /**
     * Positions the caret to the position indicated by pos and extends the
     * selection, if there is one.
     *
     * @param pos
     *            the position.
     */
    public void selectPositionCaret(final int pos) {

        this.getWrappedControl().selectPositionCaret(pos);
    }

    /**
     * Moves the caret to the beginning of previous word.
     */
    public void selectPreviousWord() {

        this.getWrappedControl().selectPreviousWord();
    }

    /**
     * Positions the anchor and caretPosition explicitly.
     *
     * @param anchor
     *            the anchor.
     * @param caretPosition
     *            the caret position.
     */
    public void selectRange(final int anchor, final int caretPosition) {

        this.getWrappedControl().selectRange(anchor, caretPosition);
    }

}
