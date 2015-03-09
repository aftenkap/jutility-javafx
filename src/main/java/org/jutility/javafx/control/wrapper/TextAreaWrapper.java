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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;


/**
 * The {@code TextAreaWrapper} class provides a {@link ControlWrapper} for a
 * {@link TextArea}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 */
public class TextAreaWrapper
        extends TextInputControlWrapper<TextArea> {


    /**
     * The preferred number of text columns.
     *
     * @return the preferred number of text columns.
     */
    public IntegerProperty prefColumnCountProperty() {

        return this.getWrappedControl().prefColumnCountProperty();
    }

    /**
     * The preferred number of text rows.
     *
     * @return the preferred number of text rows.
     */
    public IntegerProperty prefRowCountProperty() {

        return this.getWrappedControl().prefRowCountProperty();
    }

    /**
     * The number of pixels by which the content is horizontally scrolled.
     *
     * @return the number of pixels by which the content is horizontally
     *         scrolled.
     */
    public DoubleProperty scrollLeftProperty() {

        return this.getWrappedControl().scrollLeftProperty();
    }

    /**
     * The number of pixels by which the content is vertically scrolled.
     *
     * @return the number of pixels by which the content is vertically scrolled.
     */
    public DoubleProperty scrollTopProperty() {

        return this.getWrappedControl().scrollTopProperty();
    }

    /**
     * If a run of text exceeds the width of the TextArea, then this variable
     * indicates whether the text should wrap onto another line.
     *
     * @return if a run of text exceeds the width of the TextArea, then this
     *         variable indicates whether the text should wrap onto another
     *         line.
     */
    public BooleanProperty wrapTextProperty() {

        return this.getWrappedControl().wrapTextProperty();
    }


    /**
     * Creates a new instance of the {@code TextAreaWrapper} class.
     */
    public TextAreaWrapper() {

        this(null);
    }

    /**
     * Creates a new instance of the {@code TextAreaWrapper} class.
     *
     * @param text
     *            the initial text of the {@link TextArea}.
     */
    public TextAreaWrapper(final String text) {

        super(text == null ? new TextArea() : new TextArea(text));
    }


    /**
     * Returns an unmodifiable list of the character sequences that back the
     * text area's content.
     *
     * @return an unmodifiable list of the character sequences that back the
     *         text area's content.
     */
    public ObservableList<CharSequence> getParagraphs() {

        return this.getWrappedControl().getParagraphs();
    }

    /**
     * Gets the value of the property prefColumnCount.
     *
     * @return the value of the property prefColumnCount.
     */
    public int getPrefColumnCount() {

        return this.getWrappedControl().getPrefColumnCount();
    }

    /**
     * Gets the value of the property prefRowCount.
     *
     * @return the value of the property prefRowCount.
     */
    public int getPrefRowCount() {

        return this.getWrappedControl().getPrefRowCount();
    }

    /**
     * Gets the value of the property scrollLeft.
     *
     * @return the value of the property scrollLeft.
     */
    public double getScrollLeft() {

        return this.getWrappedControl().getScrollLeft();
    }

    /**
     * Gets the value of the property scrollTop.
     *
     * @return the value of the property scrollTop.
     */
    public double getScrollTop() {

        return this.getWrappedControl().getScrollTop();
    }

    /**
     * Gets the value of the property wrapText.
     *
     * @return the value of the property wrapText.
     */
    boolean isWrapText() {

        return this.getWrappedControl().isWrapText();
    }

    /**
     * Sets the value of the property prefColumnCount.
     *
     * @param value
     *            the value of the property prefColumnCount.
     */
    public void setPrefColumnCount(final int value) {

        this.getWrappedControl().setPrefColumnCount(value);
    }

    /**
     * Sets the value of the property prefRowCount.
     *
     * @param value
     *            the value of the property prefRowCount.
     */
    public void setPrefRowCount(final int value) {

        this.getWrappedControl().setPrefRowCount(value);
    }

    /**
     * Sets the value of the property scrollLeft.
     *
     * @param value
     *            the value of the property scrollLeft.
     */
    public void setScrollLeft(final double value) {

        this.getWrappedControl().setScrollLeft(value);
    }

    /**
     * Sets the value of the property scrollTop.
     *
     * @param value
     *            the value of the property scrollTop.
     */
    public void setScrollTop(final double value) {

        this.getWrappedControl().setScrollTop(value);
    }

    /**
     * Sets the value of the property wrapText.
     *
     * @param value
     *            the value of the property wrapText.
     */
    public void setWrapText(final boolean value) {

        this.getWrappedControl().setWrapText(value);
    }
}
