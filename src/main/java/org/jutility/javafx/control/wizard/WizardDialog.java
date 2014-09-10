package org.jutility.javafx.control.wizard;



import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Wizard in Dialog Style
 * 
 * @author spn2460
 * 
 */
public class WizardDialog
        extends Stage {

    private final IWizard wizard;


    /**
     * Returns the wizard.
     * 
     * @return the Wizard.
     */
    public IWizard getWizard() {

        return wizard;
    }

    /**
     * constructor
     * 
     * @param wizard
     */
    public WizardDialog(final IWizard wizard) {

        Scene scene = new Scene(new VBox(), 1600, 800);
        this.wizard = wizard;


        ((VBox) scene.getRoot()).getChildren().add((Node) wizard);

        this.setScene(scene);

    }



}
