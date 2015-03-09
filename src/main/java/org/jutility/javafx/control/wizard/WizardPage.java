package org.jutility.javafx.control.wizard;


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



import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * The abstract {@code WizardPage} class provides the basis for pages of a
 * {@link IWizard Wizard}.
 *
 * @author Shawn P. Neuman
 * @version 0.1.2
 * @since 0.1.0
 * @deprecated since 0.1.2 in favor of {@link org.controlsfx.dialog.Wizard}.
 */
@Deprecated
public abstract class WizardPage
        extends VBox {

    /**
     * The button for navigating to the previous page.
     */
    protected Button priorButton  = new Button("_Previous");

    /**
     * The button for navigating to the next page.
     */
    protected Button nextButton   = new Button("N_ext");

    /**
     * The button for canceling the {@link IWizard Wizard}.
     */
    protected Button cancelButton = new Button("Cancel");

    /**
     * The button for completing the {@link IWizard Wizard}.
     */
    protected Button finishButton = new Button("_Finish");


    /**
     * Creates a new instance of the {@code WizardPage} class with the provided
     * title.
     *
     * @param title
     *            the title.
     */
    WizardPage(final String title) {

        final Label label = new Label(title);
        label.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 5 0;");
        this.getChildren().add(label);
        this.setId(title);
        this.setSpacing(5);
        this.setStyle("-fx-padding:10; -fx-background-color: honeydew; "
                + "-fx-border-color: derive(honeydew, -30%); "
                + "-fx-border-width: 3;");

        final Region spring = new Region();
        VBox.setVgrow(spring, Priority.ALWAYS);
        this.getChildren().addAll(this.getContent(), spring, this.getButtons());


        this.priorButton
                .setOnAction(actionEvent -> WizardPage.this.priorPage());
        this.nextButton.setOnAction(actionEvent -> WizardPage.this.nextPage());
        this.cancelButton.setOnAction(actionEvent -> WizardPage.this
                .getWizard().cancel());
        this.finishButton.setOnAction(actionEvent -> WizardPage.this.finish());
    }


    /**
     * Returns the button bar.
     * 
     * @return the button bar.
     */
    HBox getButtons() {

        final Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);
        final HBox buttonBar = new HBox(5);
        this.cancelButton.setCancelButton(true);
        this.finishButton.setDefaultButton(true);
        buttonBar.getChildren().addAll(spring, this.priorButton,
                this.nextButton, this.cancelButton, this.finishButton);
        return buttonBar;
    }

    /**
     * Returns the content of the page
     * 
     * @return the content of the page
     */
    abstract Parent getContent();


    /**
     * Returns whether or not the {@code IWizard} contains a page after the
     * current page.
     *
     * @return {@code true}, if a page after the current page exists;
     *         {@code false} otherwise.
     */
    boolean hasNextPage() {

        return this.getWizard().hasNextPage();
    }

    /**
     * Returns whether or not the {@code IWizard} contains a page before the
     * current page.
     *
     * @return {@code true}, if a page before the current page exists;
     *         {@code false} otherwise.
     */
    boolean hasPriorPage() {

        return this.getWizard().hasPriorPage();
    }

    /**
     * Navigate to next page if it exists.
     */
    void nextPage() {

        this.getWizard().nextPage();
    }

    /**
     * Navigate to previous page if it exists.
     */
    void priorPage() {

        this.getWizard().priorPage();
    }

    /**
     * Close the wizard.
     */
    void finish() {

        this.getWizard().finish();
    }

    /**
     * Navigate to a page with a specific string id.
     *
     * @param id
     *            string value of page.
     */
    void navTo(final String id) {

        this.getWizard().navTo(id);
    }

    /**
     * Navigate to a page with a specific string id.
     *
     * @param id
     *            string value of page.
     */
    void navTo(final int id) {

        this.getWizard().navTo(id);
    }

    /**
     * Returns the {@link IWizard Wizard}.
     * 
     * @return the {@link IWizard Wizard}.
     */
    IWizard getWizard() {

        Node node = this;
        // int i = 0;
        while (node != null) {

            node = node.getParent();
            if (node instanceof IWizard) {
                // System.out.println("In " + this.getClass() + ". Parent " + i
                // + " levels up.");
                return (IWizard) node;
            }
            // i++;
        }

        return null;
    }

    /**
     * Manages button visibility
     */
    public void manageButtons() {

        if (!this.hasPriorPage()) {

            this.priorButton.setDisable(true);
        }

        if (!this.hasNextPage()) {

            this.nextButton.setDisable(true);
        }
    }

    /**
     * Returns the id of the page.
     * 
     * @return the id of the page.
     */
    String getByID() {

        return this.getId();
    }
}
