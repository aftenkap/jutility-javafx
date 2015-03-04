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



import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * The {@code WizardWithStatus} class provides a customizable {@link IWizard
 * Wizard} with a status pane.
 *
 * @author Shawn P. Neuman
 * @version 0.1.2
 * @since 0.1.0
 * @deprecated since 0.1.2 in favor of {@link org.controlsfx.dialog.Wizard}.
 */
@Deprecated
public abstract class WizardWithStatus
        extends GridPane
        implements IWizard {

    private Stage                            owner;

    private final Pane                       statusPane;
    private final StackPane                  stackPane;

    private static final int                 UNDEFINED  = -1;
    private final ObservableList<WizardPage> pages      = FXCollections
                                                                .observableArrayList();
    private final Stack<Integer>             history    = new Stack<>();
    private int                              curPageIdx = WizardWithStatus.UNDEFINED;



    /**
     * Returns the status pane.
     *
     * @return the status pane.
     */
    protected Pane getStatusPane() {

        return this.statusPane;
    }


    @Override
    public void setOwner(final Stage owner) {

        this.owner = owner;
    }

    /**
     * Creates a new instance of the {@code WizardWithStatus} class.
     *
     * @param owner
     *            the owner of this {@code WizardWithStatus}.
     * @param statusPane
     *            the status {@link Pane}.
     * @param pages
     *            the {@link WizardPage Pages}.
     */
    public WizardWithStatus(final Stage owner, final Pane statusPane,
            final WizardPage... pages) {

        super();
        this.owner = owner;

        this.statusPane = statusPane;
        this.stackPane = new StackPane();

        this.setHgap(25);
        this.setVgap(10);
        this.setPadding(new Insets(10, 25, 25, 25));


        this.add(this.stackPane, 0, 0);
        this.add(this.statusPane, 1, 0);

        this.pages.addAll(pages);
        this.navTo(0);
        this.setStyle("-fx-padding: 10; -fx-background-color: cornsilk;");
    }


    @Override
    public WizardPage getPage(final int index) {

        if (index < this.pages.size()) {
            return this.pages.get(index);
        }
        return null;
    }


    @Override
    public void removePage(final int index) {

        if (index < this.pages.size()) {
            this.pages.remove(index);
        }
    }


    @Override
    public void addPage(final WizardPage page) {

        this.pages.add(page);
    }


    @Override
    public void addPage(final int index, final WizardPage page) {

        this.pages.add(index, page);
    }



    @Override
    public void nextPage() {

        if (this.hasNextPage()) {
            this.navTo(this.curPageIdx + 1);
        }
    }



    @Override
    public void priorPage() {

        if (this.hasPriorPage()) {
            this.navTo(this.history.pop(), false);
        }
    }



    @Override
    public boolean hasNextPage() {

        return (this.curPageIdx < (this.pages.size() - 1));
    }



    @Override
    public boolean hasPriorPage() {

        return !this.history.isEmpty();
    }



    @Override
    public void navTo(final int nextPageIdx, final boolean pushHistory) {

        if ((nextPageIdx < 0) || (nextPageIdx >= this.pages.size())) {
            return;
        }
        if (this.curPageIdx != WizardWithStatus.UNDEFINED) {
            if (pushHistory) {
                this.history.push(this.curPageIdx);
            }
        }

        final WizardPage nextPage = this.pages.get(nextPageIdx);
        this.curPageIdx = nextPageIdx;

        this.stackPane.getChildren().clear();
        this.stackPane.getChildren().add(nextPage);

        nextPage.manageButtons();
    }



    @Override
    public void navTo(final int nextPageIdx) {

        this.navTo(nextPageIdx, true);
    }



    @Override
    public void navTo(final String id) {

        for (final WizardPage page : this.pages) {
            if (page.getByID().equals(id)) {
                this.navTo(this.pages.indexOf(page));
            }
        }

    }



    @Override
    public int getCurrentPageIndex() {

        return this.curPageIdx;
    }



    @Override
    public void finish() {

        this.owner.close();
    }



    @Override
    public void cancel() {

        this.owner.close();
    }
}
