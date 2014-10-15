/**
 * 
 */
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


import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import org.jutility.common.datatype.table.CellLocation;
import org.jutility.common.datatype.table.CellRange;


/**
 * The {@link CellRangeGridPane} provides an input Control for {@link CellRange
 * CellRanges}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 */
public class CellRangeGridPane
        extends GridPane {

    private final CellLocationGridPane from;
    private final CellLocationGridPane to;

    private final BooleanProperty      invalid;


    /**
     * Returns the invalid Property of this control.
     * 
     * @return the invalid Property of this control.
     */
    public ReadOnlyBooleanProperty invalidProperty() {

        return this.invalid;
    }

    /**
     * Returns whether or not the state of the control is invalid.
     * 
     * @return {@code true}, if the state of the control is invalid;
     *         {@code false} otherwise.
     */
    public boolean isInvalid() {

        return this.invalid.get();
    }

    /**
     * Returns an optional containing the {@link CellRange} defined by this
     * control.
     * 
     * @return an optional containing the {@link CellRange} defined by this
     *         control.
     */
    public CellRange getCellRange() {

        if (this.isInvalid()) {

            return null;
        }

        return new CellRange(this.from.getCellLocation(),
                this.to.getCellLocation());
    }

    /**
     * Sets the {@link CellRange} defined by this Control.
     * 
     * @param cellRange
     *            the {@link CellRange} defined by this Control (a value of
     *            {@code null} clears the control).
     */
    public void setCellRange(final CellRange cellRange) {

        this.from.setCellLocation(null);
        this.to.setCellLocation(null);
        if (cellRange != null) {

            this.from.setCellLocation(cellRange.getBeginning());
            this.to.setCellLocation(cellRange.getEnd());
        }
    }

    /**
     * Creates a new instance of the {@link CellRangeGridPane} class.
     */
    public CellRangeGridPane() {

        this(null);
    }

    /**
     * Creates a new instance of the {@link CellRangeGridPane} class with the
     * provided range restriction.
     * 
     * @param minRow
     *            the minimum row index.
     * @param minColumn
     *            the minimum column index.
     * @param maxRow
     *            the maximum row index.
     * @param maxColumn
     *            the maximum column index.
     */
    public CellRangeGridPane(final int minRow, final int minColumn,
            final int maxRow, final int maxColumn) {

        this(new CellRange(new CellLocation(minRow, minColumn),
                new CellLocation(maxRow, maxColumn)));
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class with the
     * provided range restriction.
     * 
     * @param validRange
     *            the range restriction.
     */
    public CellRangeGridPane(final CellRange validRange) {

        this(validRange, null);
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class with the
     * provided range restriction and initial value.
     * 
     * @param validRange
     *            the range restriction.
     * @param initialValue
     *            the initial value.
     */
    public CellRangeGridPane(final CellRange validRange,
            final CellRange initialValue) {

        super();

        this.setPadding(new Insets(5));

        this.setHgap(10);
        this.setVgap(10);

        this.invalid = new SimpleBooleanProperty();

        if (initialValue != null) {

            this.from = new CellLocationGridPane(validRange,
                    initialValue.getBeginning());
            this.to = new CellLocationGridPane(validRange,
                    initialValue.getEnd());
        }
        else {

            this.from = new CellLocationGridPane(validRange);
            this.to = new CellLocationGridPane(validRange);
        }
        this.invalid.bind(BooleanBinding.booleanExpression(
                this.from.invalidProperty()).or(
                BooleanBinding.booleanExpression(this.to.invalidProperty())));


        GridPane rangeBox = new GridPane();
        rangeBox.setHgap(10);

        Label fromLabel = new Label("From:");
        GridPane.setMargin(fromLabel, new Insets(10));
        fromLabel
                .setStyle("-fx-font-family: sans-serif; -fx-font-style: italic;");
        Label toLabel = new Label("To:");
        toLabel.setStyle("-fx-font-family: sans-serif; -fx-font-style: italic;");
        GridPane.setMargin(toLabel, new Insets(10));


        fromLabel.setLabelFor(from);
        toLabel.setLabelFor(to);


        rangeBox.add(fromLabel, 0, 0);
        rangeBox.add(from, 1, 0, 1, 2);


        rangeBox.add(toLabel, 0, 3);
        rangeBox.add(to, 1, 3, 1, 2);

        String rangeLabelText = "Range";

        if (validRange != null) {

            rangeLabelText += " (valid within " + validRange + ")";
        }
        rangeLabelText += ":";

        Label rangeLabel = new Label(rangeLabelText);
        rangeLabel
                .setStyle("-fx-font-family: sans-serif; -fx-font-weight: bold;");
        rangeLabel.setLabelFor(rangeBox);


        this.add(rangeLabel, 0, 0);
        this.add(rangeBox, 0, 1);
    }
}
