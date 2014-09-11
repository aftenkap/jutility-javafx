package org.jutility.javafx.control;


import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.common.datatype.table.CellLocation;
import org.jutility.common.datatype.table.CellRange;
import org.jutility.javafx.control.validated.ValidationUtils;


/**
 * The {@link CellLocationGridPane} provides an input Control for
 * {@link CellLocation CellLocations}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 */
public class CellLocationGridPane
        extends GridPane {


    private final TextField         rowTF;
    private final TextField         columnTF;

    private final CellRange         validRange;

    private final ValidationSupport validationSupport;

    /**
     * Returns the valid {@link CellRange Range}.
     * 
     * @return the valid {@link CellRange Range}.
     */
    public CellRange getValidRange() {

        return this.validRange;
    }

    /**
     * Returns whether or not the state of this control is invalid.
     * 
     * @return {@code true}, if the state of this control is invalid;
     *         {@code false} otherwise.
     */
    public boolean isInvalid() {

        return this.invalidProperty().get();
    }

    /**
     * Returns the invalid Property of this Control.
     * 
     * @return the invalid Property of this Control.
     */
    public ReadOnlyObjectProperty<Boolean> invalidProperty() {

        return this.validationSupport.invalidProperty();
    }

    /**
     * Returns the {@link CellLocation} defined by this Control.
     * 
     * @return the {@link CellLocation} defined by this Control or {@code null},
     *         if the contents are invalid.
     */
    public CellLocation getCellLocation() {

        if (this.isInvalid()) {

            return null;
        }

        int row = Integer.parseInt(this.rowTF.getText());
        int column = Integer.parseInt(this.columnTF.getText());

        return new CellLocation(row, column);
    }

    /**
     * Sets the {@link CellLocation} defined by this Control.
     * 
     * @param cellLocation
     *            the {@link CellLocation} defined by this Control (a value of
     *            {@code null} clears the control).
     */
    public void setCellLocation(final CellLocation cellLocation) {

        this.rowTF.clear();
        this.columnTF.clear();
        if (cellLocation != null) {

            this.rowTF.setText(cellLocation.getRow().toString());
            this.columnTF.setText(cellLocation.getColumn().toString());
        }
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class without
     * any restriction on the valid range.
     */
    public CellLocationGridPane() {

        this((CellRange) null);
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class without
     * any restriction on the valid range with the provided initial value.
     * 
     * @param initialValue
     *            the initial value.
     */
    public CellLocationGridPane(final CellLocation initialValue) {

        this(null, initialValue);
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class with the
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
    public CellLocationGridPane(final int minRow, final int minColumn,
            final int maxRow, final int maxColumn) {

        this(new CellRange(new CellLocation(minRow, minColumn),
                new CellLocation(maxRow, maxColumn)));
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class with the
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
     * @param initialValue
     *            the initial value.
     */
    public CellLocationGridPane(final int minRow, final int minColumn,
            final int maxRow, final int maxColumn,
            final CellLocation initialValue) {

        this(new CellRange(new CellLocation(minRow, minColumn),
                new CellLocation(maxRow, maxColumn)), initialValue);
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class with the
     * provided range restriction.
     * 
     * @param validRange
     *            the range restriction.
     */
    public CellLocationGridPane(final CellRange validRange) {

        this(validRange, null);
    }

    /**
     * Creates a new instance of the {@link CellLocationGridPane} class with the
     * provided range restriction.
     * 
     * @param validRange
     *            the range restriction.
     * @param initialValue
     *            the initial value.
     */
    public CellLocationGridPane(final CellRange validRange,
            final CellLocation initialValue) {

        super();

        this.setPadding(new Insets(5));

        this.setHgap(10);
        this.setVgap(5);

        this.validationSupport = new ValidationSupport();
        this.validRange = validRange;

        this.rowTF = new TextField();
        this.columnTF = new TextField();

        this.rowTF.setPromptText("Enter row index");
        this.columnTF.setPromptText("Enter column index");

        if (initialValue != null) {

            this.rowTF.setText(initialValue.getRow().toString());
            this.columnTF.setText(initialValue.getColumn().toString());
        }

        ValidationSupport.setRequired(this.rowTF, true);
        ValidationSupport.setRequired(this.columnTF, true);

        this.validationSupport.registerValidator(this.rowTF,
                Validator.createEmptyValidator("Row cannot be empty"));
        this.validationSupport.registerValidator(this.columnTF,
                Validator.createEmptyValidator("Column cannot be empty"));

        this.validationSupport.registerValidator(this.rowTF, ValidationUtils
                .createNumberFormatValidator(Integer.class,
                        "Value must be an Integer!"));
        this.validationSupport.registerValidator(this.columnTF, ValidationUtils
                .createNumberFormatValidator(Integer.class,
                        "Value must be an Integer!"));

        if (this.validRange != null) {

            this.validationSupport.registerValidator(this.rowTF,
                    ValidationUtils.createNumberRangeValidator(this.validRange
                            .getBeginning().getRow(), this.validRange.getEnd()
                            .getRow(), true, false, "Value not in range!"));
            this.validationSupport.registerValidator(this.columnTF,
                    ValidationUtils.createNumberRangeValidator(this.validRange
                            .getBeginning().getColumn(), this.validRange
                            .getEnd().getColumn(), true, false,
                            "Value not in range!"));
        }

        this.validationSupport
                .setValidationDecorator(new GraphicValidationDecoration());


        this.add(this.rowTF, 0, 0);
        this.add(this.columnTF, 1, 0);
    }

    @Override
    public void requestFocus() {

        this.rowTF.requestFocus();
    }
}
