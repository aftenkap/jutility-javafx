package org.jutility.javafx.control.wizard;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * Wizard page abstract class
 * 
 * @author spn2460
 * 
 */
public abstract class WizardPage
        extends VBox {

    Button           priorButton  = new Button("_Previous");
    /**
     * next button, to have access within all Wizard Pages
     */
    protected Button nextButton   = new Button("N_ext");
    Button           cancelButton = new Button("Cancel");
    Button           finishButton = new Button("_Finish");

    /**
     * Wizard page constructor
     * 
     * @param title
     *            the title of the page
     */
    WizardPage(String title) {

        Label label = new Label(title);
        label.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 5 0;");
        getChildren().add(label);
        setId(title);
        setSpacing(5);
        setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");

        Region spring = new Region();
        VBox.setVgrow(spring, Priority.ALWAYS);
        getChildren().addAll(getContent(), spring, getButtons());


        priorButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                priorPage();
            }
        });
        nextButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {


                nextPage();
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                getWizard().cancel();
            }
        });
        finishButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {

                finish();
            }
        });
    }

    /**
     * @return horizontal bar of buttons
     */
    HBox getButtons() {

        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);
        HBox buttonBar = new HBox(5);
        cancelButton.setCancelButton(true);
        finishButton.setDefaultButton(true);
        buttonBar.getChildren().addAll(spring, priorButton, nextButton,
                cancelButton, finishButton);
        return buttonBar;
    }

    /**
     * @return the content of the parent
     */
    abstract Parent getContent();


    /**
     * @return true if next page exists
     */
    boolean hasNextPage() {

        return getWizard().hasNextPage();
    }

    /**
     * @return true if previous page exists
     */
    boolean hasPriorPage() {

        return getWizard().hasPriorPage();
    }

    /**
     * get the next page
     */
    void nextPage() {

        getWizard().nextPage();
    }

    /**
     * get the previous page
     */
    void priorPage() {

        getWizard().priorPage();
    }

    void finish() {

        getWizard().finish();
    }

    /**
     * go to a page with this string id
     * 
     * @param id
     *            sting value of page
     */
    void navTo(String id) {

        getWizard().navTo(id);
    }

    /**
     * go to page with this integer id
     * 
     * @param id
     *            integer index of page
     */
    void navTo(int id) {

        getWizard().navTo(id);
    }

    /**
     * @return this wizard
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
     * manages button visibility
     */
    public void manageButtons() {

        if (!hasPriorPage()) {
            priorButton.setDisable(true);
        }

        if (!hasNextPage()) {
            nextButton.setDisable(true);
        }
    }

    /**
     * @return the string value of a page
     */
    String getByID() {

        return this.getId();
    }


}
