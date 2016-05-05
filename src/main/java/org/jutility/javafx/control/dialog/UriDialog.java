package org.jutility.javafx.control.dialog;

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

import java.net.URI;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

import org.controlsfx.validation.Validator;
import org.jutility.javafx.control.validation.ValidationSupport;
import org.jutility.javafx.control.validation.ValidationUtils;

/**
 * The {@code UriDialog} provides a validated {@link Dialog} for the entry of
 * {@link URI URI's}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class UriDialog extends Dialog<URI> {

	private final GridPane content;

	private final TextField uriTF;

	private final ValidationSupport validationSupport;

	/**
	 * Creates a new instance of the {@link UriDialog} class.
	 *
	 * @param owner
	 *            the owner of the dialog.
	 */
	public UriDialog(final Window owner) {

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
	public UriDialog(final Window owner, final String title) {

		super();
		this.initOwner(owner);
		this.setTitle(title);

		this.content = new GridPane();

		this.content.setHgap(10);
		this.content.setVgap(10);

		this.getDialogPane().setContent(this.content);

		this.validationSupport = new ValidationSupport();

		this.uriTF = new TextField();
		this.uriTF.setPromptText("Enter URI");
		GridPane.setHgrow(this.uriTF, Priority.ALWAYS);

		final Label uriLabel = new Label("URI");
		uriLabel.setLabelFor(this.uriTF);

		this.content.add(uriLabel, 0, 0);
		this.content.add(this.uriTF, 1, 0);

		ValidationSupport.setRequired(this.uriTF, true);

		this.validationSupport.registerValidator(this.uriTF,
				Validator.createEmptyValidator("URI cannot be empty!"));

		this.validationSupport.registerValidator(this.uriTF,
				ValidationUtils.createURIFormatValidator("Invalid URI!"));

		this.validationSupport.setErrorDecorationEnabled(true);

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
