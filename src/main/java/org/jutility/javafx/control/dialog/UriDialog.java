package org.jutility.javafx.control.dialog;

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


import java.net.URI;
import java.net.URISyntaxException;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

import javafx.scene.layout.Priority;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        extends Dialog {

    private final TextField uriTF;

    private URI             uri;

    private Action          ok;

    /**
     * Returns the URI.
     *
     * @return the URI.
     */
    public URI getUri() {

        return this.uri;
    }

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

        super(owner, title);

        GridPane content = new GridPane();

        content.setHgap(10);
        content.setVgap(10);

        this.setContent(content);

        this.uriTF = new TextField();
        this.uriTF.setPromptText("Enter URI");
        GridPane.setHgrow(this.uriTF, Priority.ALWAYS);

        Label uriLabel = new Label("URI");
        uriLabel.setLabelFor(uriTF);

        content.add(uriLabel, 0, 0);
        content.add(uriTF, 1, 0);

        this.uri = null;

        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                UriDialog.this.validate();
            }
        };
        this.uriTF.textProperty().addListener(changeListener);

        this.ok = Dialog.Actions.OK;
        this.ok.disabledProperty().set(true);
        this.getActions().addAll(this.ok, Dialog.Actions.CANCEL);

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                uriTF.requestFocus();
            }
        });
    }


    /**
     * Validates the input of the URI text field.
     */
    protected void validate() {


        boolean invalid = (uriTF == null) || (uriTF.getText() == null)
                || (uriTF.getText().trim().isEmpty());



        if (!invalid) {

            try {

                this.uri = new URI(uriTF.getText());
            }
            catch (URISyntaxException | NumberFormatException e2) {

                invalid = true;
            }
        }

        if (!invalid) {

            this.ok.disabledProperty().set(false);
        }
        else {

            this.ok.disabledProperty().set(true);
            this.uri = null;
        }
    }

    /**
     * Creates and shows a UriDialog and returns the input value.
     * 
     * @param owner
     *            the owner of the dialog.
     *
     * @return the URI or {@code null}.
     */
    public static URI showUriDialog(Window owner) {

        UriDialog dialog = new UriDialog(owner);

        Action result = dialog.show();

        if (result == dialog.ok) {

            return dialog.getUri();
        }
        return null;
    }
}
