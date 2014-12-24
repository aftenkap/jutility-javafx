package org.jutility.javafx.control.dialog;


/*
 * #%L jutility-javafx %% Copyright (C) 2013 - 2014 jutility.org %% Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. #L%
 */


import java.net.URI;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.validated.ValidationUtils;

import javafx.scene.layout.Priority;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;



/**
 *
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class UriDialog
        extends Dialog<URI> {

    private final TextField         uriTF;

    private final ValidationSupport validationSupport;

    /**
     * Creates a new instance of the {@link UriDialog} class.
     * 
     * @param owner
     *            the owner of the dialog.
     */
    public UriDialog(Window owner) {

        this(owner, "Enter URI");
    }

    /**
     * Creates a new instance of the {@link UriDialog} class.
     * 
     * @param owner
     *            the owner of the dialog.
     * @param title
     *            the title of the dialog.
     */
    public UriDialog(Window owner, String title) {

        super();
        this.initOwner(owner);
        this.setTitle(title);

        GridPane content = new GridPane();

        content.setHgap(10);
        content.setVgap(10);

        this.getDialogPane().setContent(content);


        this.validationSupport = new ValidationSupport();

        this.uriTF = new TextField();
        this.uriTF.setPromptText("Enter URI");
        GridPane.setHgrow(this.uriTF, Priority.ALWAYS);

        Label uriLabel = new Label("URI");
        uriLabel.setLabelFor(uriTF);

        content.add(uriLabel, 0, 0);
        content.add(this.uriTF, 1, 0);

        ValidationSupport.setRequired(this.uriTF, true);

        this.validationSupport.registerValidator(this.uriTF,
                Validator.createEmptyValidator("URI cannot be empty!"));

        this.validationSupport.registerValidator(this.uriTF,
                ValidationUtils.createURIFormatValidator("Invalid URI!"));


        this.validationSupport
                .setValidationDecorator(new GraphicValidationDecoration());

        this.setResultConverter(param -> {

            if (param == ButtonType.OK) {

                return URI.create(this.uriTF.getText());
            }

            return null;
        });

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);

        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.validationSupport.invalidProperty());

        Platform.runLater(() -> {

            this.uriTF.requestFocus();
        });
    }
}
