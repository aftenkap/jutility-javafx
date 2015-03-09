package org.jutility.javafx.control.dialog;


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
// @formatter:on

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import org.jutility.common.datatype.table.CellRange;
import org.jutility.javafx.control.CellRangeGridPane;



/**
 * The {@code CellRangeDialog} provides a {@link Dialog} for the entry of
 * {@link CellRange CellRanges}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class CellRangeDialog
        extends Dialog<CellRange> {

    private final GridPane          content;

    private final CellRangeGridPane cellRange;


    /**
     * Creates a new instance of the {@code CellRangeDialog} class.
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
    public CellRangeDialog(final String title, final Window owner,
            final CellRange validRange, final CellRange initialValue) {

        super();
        this.initOwner(owner);
        this.setTitle(title);

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);

        this.getDialogPane().setContent(this.content);

        this.cellRange = new CellRangeGridPane(validRange, initialValue);

        this.content.add(this.cellRange, 0, 0);

        this.setResultConverter((param) -> {

            if (param == ButtonType.OK) {

                return this.cellRange.getCellRange();
            }

            return null;
        });

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);


        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.cellRange.invalidProperty());

        Platform.runLater(() -> {

            this.cellRange.requestFocus();
        });

    }
}
