/**
 * 
 */
package org.jutility.javafx.control.wrapper;


import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;



/**
 * The {@link TextFieldWrapper} class wraps a {@link TextField} in a
 * {@link GridPane} with six surrounding {@link Node Nodes}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 */
@DefaultProperty(value = "text")
public class TextFieldWrapper
        extends WrapperBase<TextField> {




    /**
     * Specifies how the text should be aligned when there is empty space within
     * the {@link TextField}.
     * 
     * @see TextField#getAlignment()
     * @see TextField#setAlignment(Pos)
     * @return the text alignment property.
     */
    public final ObjectProperty<Pos> textAlignmentProperty() {

        return this.getWrapped().alignmentProperty();
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

        return this.getWrapped().onActionProperty();
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

        return this.getWrapped().prefColumnCountProperty();
    }

    /**
     * The prompt text to display in the TextField, or {@code null} if no prompt
     * text is displayed.
     * 
     * @return the prompt text to display.
     */
    public final StringProperty promptTextProperty() {

        return this.getWrapped().promptTextProperty();
    }

    /**
     * The textual content of this TextInputControl.
     * 
     * @return the textual content of this TextInputControl.
     */
    public final StringProperty textProperty() {

        return this.getWrapped().textProperty();
    }

    /**
     * The number of characters in the text input.
     * 
     * @return the number of characters in the text input.
     */
    public final ReadOnlyIntegerProperty lengthProperty() {

        return this.getWrapped().lengthProperty();
    }

    /**
     * Indicates whether this TextInputControl can be edited by the user.
     * 
     * @return {@code true}, if this TextInputControl can be edited by the user;
     *         {@code false} otherwise.
     */
    public final BooleanProperty editableProperty() {

        return this.getWrapped().editableProperty();
    }

    /**
     * The current selection.
     * 
     * @return the current selection.
     */
    public final ReadOnlyObjectProperty<IndexRange> selectionProperty() {

        return this.getWrapped().selectionProperty();
    }

    /**
     * Defines the characters in the TextInputControl which are selected.
     * 
     * @return the characters in the TextInputControl which are selected
     */
    public final ReadOnlyStringProperty selectedTextProperty() {

        return this.getWrapped().selectedTextProperty();
    }

    /**
     * The anchor of the text selection.
     * 
     * @return the anchor of the text selection.
     */
    public final ReadOnlyIntegerProperty anchorProperty() {

        return this.getWrapped().anchorProperty();
    }

    /**
     * The current position of the caret within the text.
     * 
     * @return the current position of the caret within the text.
     */
    public final ReadOnlyIntegerProperty caretPositionProperty() {

        return this.getWrapped().caretPositionProperty();
    }




    /**
     * Creates a new instance of the {@link TextFieldWrapper} class.
     */
    public TextFieldWrapper() {

        this(null);

    }

    /**
     * Creates a new instance of the {@link TextFieldWrapper} class.
     * 
     * @param text
     *            the initial text content of the {@link TextField}.
     */
    public TextFieldWrapper(final String text) {

        super();

        this.setWrapped(new TextField());
        GridPane.setHgrow(this.getWrapped(), Priority.SOMETIMES);
        if (text != null) {

            this.getWrapped().setText(text);
        }
    }

    /**
     * Appends a sequence of characters to the content.
     * 
     * @param text
     *            the text to append.
     */
    public void appendText(String text) {

        this.getWrapped().appendText(text);
    }

    /**
     * Moves the caret position backward.
     */
    public void backward() {

        this.getWrapped().backward();
    }

    /**
     * Clears the text.
     */
    public void clear() {

        this.getWrapped().clear();
    }

    /**
     * Transfers the currently selected range in the text to the clipboard,
     * leaving the current selection.
     */
    public void copy() {

        this.getWrapped().copy();
    }

    /**
     * Transfers the currently selected range in the text to the clipboard,
     * removing the current selection.
     */
    public void cut() {

        this.getWrapped().cut();
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

        return this.getWrapped().deleteNextChar();
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

        return this.getWrapped().deletePreviousChar();
    }

    /**
     * Removes a range of characters from the content.
     * 
     * @param range
     *            the range to delete.
     */
    public void deleteText(IndexRange range) {

        this.getWrapped().deleteText(range);
    }

    /**
     * Removes a range of characters from the content.
     * 
     * @param start
     *            the start index.
     * @param end
     *            the end index.
     */
    public void deleteText(int start, int end) {

        this.getWrapped().deleteText(start, end);
    }

    /**
     * Clears the selection.
     */
    public void deselect() {

        this.getWrapped().deselect();
    }

    /**
     * Moves the caret to after the last char of the text.
     */
    public void end() {

        this.getWrapped().end();
    }

    /**
     * Moves the caret to the end of the next word.
     */
    public void endOfNextWord() {

        this.getWrapped().endOfNextWord();
    }

    /**
     * This function will extend the selection to include the specified
     * position.
     * 
     * @param pos
     *            the position
     */
    public void extendSelection(int pos) {

        this.getWrapped().extendSelection(pos);
    }

    /**
     * Moves the caret position forward.
     */
    public void forward() {

        this.getWrapped().forward();
    }

    /**
     * Gets the value of the property anchor.
     * 
     * @return The anchor of the text selection. The anchor and caretPosition
     *         make up the selection range. Selection must always be specified
     *         in terms of begin <= end, but anchor may be less than, equal to,
     *         or greater than the caretPosition. Depending on how the user
     *         selects text, the anchor might represent the lower or upper bound
     *         of the selection.
     */
    public int getAnchor() {

        return this.getWrapped().getAnchor();
    }

    /**
     * Gets the value of the property caretPosition.
     * 
     * @return The current position of the caret within the text. The anchor and
     *         caretPosition make up the selection range. Selection must always
     *         be specified in terms of begin <= end, but anchor may be less
     *         than, equal to, or greater than the caretPosition. Depending on
     *         how the user selects text, the caretPosition might represent the
     *         lower or upper bound of the selection.
     */
    public int getCaretPosition() {

        return this.getWrapped().getCaretPosition();
    }


    /**
     * Gets the value of the property length.
     * 
     * @return the value of the property length.
     */
    public int getLength() {

        return this.getWrapped().getLength();
    }

    /**
     * Gets the value of the property promptText.
     * 
     * @return the value of the property promptText.
     */
    public String getPromptText() {

        return this.getWrapped().getPromptText();
    }

    /**
     * Gets the value of the property selectedText.
     * 
     * @return the value of the property selectedText.
     */
    public String getSelectedText() {

        return this.getWrapped().getSelectedText();
    }

    /**
     * Gets the value of the property selection.
     * 
     * @return the value of the property selection.
     */
    public IndexRange getSelection() {

        return this.getWrapped().getSelection();
    }

    /**
     * Gets the value of the property text.
     * 
     * @return the value of the property text.
     */
    public String getText() {

        return this.getWrapped().getText();
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
    public String getText(int start, int end) {

        return this.getWrapped().getText(start, end);
    }

    /**
     * Moves the caret to before the first char of the text.
     */
    public void home() {

        this.getWrapped().home();
    }

    /**
     * Inserts a sequence of characters into the content.
     * 
     * @param index
     *            the insertion index.
     * @param text
     *            the text to insert.
     */
    public void insertText(int index, String text) {

        this.getWrapped().insertText(index, text);
    }


    /**
     * Gets the value of the property editable.
     * 
     * @return {@code true}, if the {@link TextField} is editable; {@code false}
     *         otherwise.
     */
    public boolean isEditable() {

        return this.getWrapped().isEditable();
    }


    /**
     * Moves the caret to the beginning of next word.
     */
    public void nextWord() {

        this.getWrapped().nextWord();
    }

    /**
     * Transfers the contents in the clipboard into this text, replacing the
     * current selection.
     */
    public void paste() {

        this.getWrapped().paste();
    }

    /**
     * Positions the caret to the position indicated by pos.
     * 
     * @param pos
     *            the new caret position.
     */
    public void positionCaret(int pos) {

        this.getWrapped().positionCaret(pos);
    }

    /**
     * Moves the caret to the beginning of previous word.
     */
    public void previousWord() {

        this.getWrapped().previousWord();
    }

    /**
     * Replaces the selection with the given replacement String.
     * 
     * @param replacement
     *            the replacement String.
     */
    public void replaceSelection(String replacement) {

        this.getWrapped().replaceSelection(replacement);
    }

    /**
     * Replaces a range of characters with the given text.
     * 
     * @param range
     *            the range.
     * @param text
     *            the replacement text.
     */
    public void replaceText(IndexRange range, String text) {

        this.getWrapped().replaceText(range, text);
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
    public void replaceText(int start, int end, String text) {

        this.getWrapped().replaceText(start, end, text);
    }

    /**
     * Selects all text in the text input.
     */
    public void selectAll() {

        this.getWrapped().selectAll();
    }

    /**
     * Moves the selection backward one char in the text.
     */
    public void selectBackward() {

        this.getWrapped().selectBackward();
    }

    /**
     * Moves the caret to after the last char of text.
     */
    public void selectEnd() {

        this.getWrapped().selectEnd();
    }

    /**
     * Moves the caret to the end of the next word.
     */
    public void selectEndOfNextWord() {

        this.getWrapped().selectEndOfNextWord();
    }

    /**
     * Moves the selection forward one char in the text.
     */
    public void selectForward() {

        this.getWrapped().selectForward();
    }

    /**
     * Moves the caret to before the first char of text.
     */
    public void selectHome() {

        this.getWrapped().selectHome();
    }

    /**
     * Moves the caret to the beginning of next word.
     */
    public void selectNextWord() {

        this.getWrapped().selectNextWord();
    }

    /**
     * Positions the caret to the position indicated by pos and extends the
     * selection, if there is one.
     * 
     * @param pos
     *            the position.
     */
    public void selectPositionCaret(int pos) {

        this.getWrapped().selectPositionCaret(pos);
    }

    /**
     * Moves the caret to the beginning of previous word.
     */
    public void selectPreviousWord() {

        this.getWrapped().selectPreviousWord();
    }

    /**
     * Positions the anchor and caretPosition explicitly.
     * 
     * @param anchor
     *            the anchor.
     * @param caretPosition
     *            the caret position.
     */
    public void selectRange(int anchor, int caretPosition) {

        this.getWrapped().selectRange(anchor, caretPosition);
    }

    /**
     * Sets the value of the property editable.
     * 
     * @param value
     *            the new value.
     */
    public void setEditable(boolean value) {

        this.getWrapped().setEditable(value);
    }

    /**
     * Sets the value of the property promptText.
     * 
     * @param value
     *            the new prompt text.
     */
    public void setPromptText(String value) {

        this.getWrapped().setPromptText(value);
    }

    /**
     * Sets the value of the property text.
     * 
     * @param value
     *            the new text value.
     */
    public void setText(String value) {

        this.getWrapped().setText(value);
    }



    /**
     * Returns the character sequence backing the text field's content.
     * prefColumnCountProperty
     * 
     * @return the character sequence backing the text field's content.
     *         prefColumnCountProperty
     */
    public CharSequence getCharacters() {

        return this.getWrapped().getCharacters();
    }


    /**
     * Gets the value of the property prefColumnCount.
     * 
     * @return The preferred number of text columns. This is used for
     *         calculating the TextField's preferred width.
     */
    public final int getPrefColumnCount() {

        return this.getWrapped().getPrefColumnCount();
    }


    /**
     * Sets the value of the property prefColumnCount.
     * 
     * @param value
     *            The preferred number of text columns. This is used for
     *            calculating the TextField's preferred width.
     */
    public final void setPrefColumnCount(int value) {

        this.getWrapped().setPrefColumnCount(value);
    }



    /**
     * Gets the value of the property onAction.
     * 
     * @return The action handler associated with this text field, or null if no
     *         action handler is assigned. The action handler is normally called
     *         when the user types the ENTER key.
     */
    public final EventHandler<ActionEvent> getOnAction() {

        return this.getWrapped().getOnAction();
    }

    /**
     * Sets the value of the property onAction.
     * 
     * @param value
     *            The action handler associated with this text field, or null if
     *            no action handler is assigned. The action handler is normally
     *            called when the user types the ENTER key.
     */
    public final void setOnAction(EventHandler<ActionEvent> value) {

        this.getWrapped().setOnAction(value);
    }


    /**
     * Sets the value of the property alignment.
     * 
     * @param value
     *            Specifies how the text should be aligned when there is empty
     *            space within the TextField.
     */
    public final void setTextAlignment(Pos value) {

        this.getWrapped().setAlignment(value);
    }

    /**
     * Gets the value of the property alignment.
     * 
     * @return Specifies how the text should be aligned when there is empty
     *         space within the TextField.
     */
    public final Pos getTextAlignment() {

        return this.getWrapped().getAlignment();
    }


}
