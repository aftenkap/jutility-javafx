package org.jutility.javafx.control.wizard;


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



import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The {@code WizardDialog} class provides a dialog for a {@link IWizard Wizard}
 * .
 * 
 * @author Shawn P. Neuman
 * @version 0.1.2
 * @since 0.1.0
 * @deprecated since 0.1.2 in favor of {@link org.controlsfx.dialog.Wizard}.
 */
@Deprecated
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
     * Creates a new instance of the {@code WizardDialog} class with the
     * provided {@link IWizard}.
     * 
     * @param wizard
     *            the {@link IWizard}.
     */
    public WizardDialog(final IWizard wizard) {

        Scene scene = new Scene(new VBox(), 1600, 800);
        this.wizard = wizard;


        ((VBox) scene.getRoot()).getChildren().add((Node) wizard);

        this.setScene(scene);
    }
}
