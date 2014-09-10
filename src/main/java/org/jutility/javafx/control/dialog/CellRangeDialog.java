package org.jutility.javafx.control.dialog;


import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;
import org.jutility.common.datatype.table.CellRange;
import org.jutility.javafx.control.CellRangeGridPane;



/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
public class CellRangeDialog
        extends Dialog {



    private Action                  confirmAction;
    private GridPane                content;

    private final CellRangeGridPane cellRange;


    /**
     * Creates a new instance of the {@link CellRangeDialog} class.
     * 
     * @param title
     *            the title.
     * @param owner
     *            the owner.
     * @param validRange
     *            the range restriction.
     * @param initialValue
     *            the initial value.
     */
    public CellRangeDialog(String title, Window owner, CellRange validRange,
            CellRange initialValue) {

        super(owner, title);

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);

        this.setContent(content);

        this.cellRange = new CellRangeGridPane(validRange, initialValue);

        this.content.add(this.cellRange, 0, 0);

        this.confirmAction = new DefaultDialogAction("Ok") {

            {
                ButtonBar.setType(this, ButtonType.OK_DONE);
            }

            // This method is called when the login button is clicked...
            @Override
            public void handle(ActionEvent ae) {

                if (!isDisabled()) {
                    if (ae.getSource() instanceof Dialog) {
                        Dialog dlg = (Dialog) ae.getSource();

                        dlg.setResult(CellRangeDialog.this.confirmAction);
                    }
                }
            }
        };
        this.getActions().addAll(confirmAction, Dialog.Actions.CANCEL);

        this.confirmAction.disabledProperty().bind(
                this.cellRange.invalidProperty());
        this.cellRange.requestFocus();

    }

    /**
     * Creates a lightweight dialog for creating or editing a {@link CellRange}.
     * 
     * @param validRange
     *            the range restriction.
     * @param initialValue
     *            the initial value.
     * 
     * @return the new {@link CellRange}.
     */
    public static <T> Optional<CellRange> showCellRangeDialog(
            CellRange validRange, CellRange initialValue) {

        return CellRangeDialog.showCellRangeDialog(null, validRange,
                initialValue);
    }

    /**
     * Creates a dialog for creating or editing a {@link CellRange}.
     * 
     * @param validRange
     *            the range restriction.
     * @param initialValue
     *            the initial value.
     * @param owner
     *            the owner of the dialog.
     * @return an {@link Optional} containing the {@link CellRange}.
     */
    public static Optional<CellRange> showCellRangeDialog(Window owner,
            CellRange validRange, CellRange initialValue) {

        CellRangeDialog dialog = null;
        if (initialValue != null) {

            dialog = new CellRangeDialog("Edit Cell Range", owner, validRange,
                    initialValue);
        }
        else {

            dialog = new CellRangeDialog("Create Cell Range", owner,
                    validRange, initialValue);
        }
        Action result = dialog.show();

        if (result == dialog.confirmAction) {

            return Optional.of(dialog.cellRange.getCellRange());
        }

        return Optional.empty();
    }
}
