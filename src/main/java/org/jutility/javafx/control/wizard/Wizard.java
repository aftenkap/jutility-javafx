package org.jutility.javafx.control.wizard;

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




import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * creates stack pane for storing multiple pages for wizard navigation
 * 
 * @author spn2460
 * 
 */
public abstract class Wizard
        extends StackPane
        implements IWizard {

    private static final int                  UNDEFINED  = -1;
    private static ObservableList<WizardPage> pages      = FXCollections
                                                                 .observableArrayList();
    private Stack<Integer>                    history    = new Stack<>();
    private int                               curPageIdx = UNDEFINED;

    private Stage                             owner;

    Wizard(WizardPage... nodes) {

        pages.addAll(nodes);
        navTo(0);
        setStyle("-fx-padding: 10; -fx-background-color: cornsilk;");
    }

    /**
     * gets the page at a given index
     * 
     * @param index
     *            the integer value of a page
     * @return the Wizard page at the given index
     */
    @Override
    public WizardPage getPage(int index) {

        if (index < pages.size()) {
            return pages.get(index);
        }
        return null;
    }

    /**
     * removes a page form the stack
     * 
     * @param index
     *            the index of the page to be removed
     */
    @Override
    public void removePage(int index) {

        if (index < pages.size()) {
            pages.remove(index);
        }
    }

    /**
     * adds a page to the stack. used for dynamic adding
     * 
     * @param page
     *            the page to be added
     */
    @Override
    public void addPage(WizardPage page) {

        pages.add(page);
    }

    /**
     * adds a page at a specific index
     * 
     * @param index
     *            the index of the page to be added
     * @param page
     *            the page to be added
     */
    @Override
    public void addPage(int index, WizardPage page) {

        pages.add(index, page);
    }

    /**
     * navigate to next page if it exists
     */
    @Override
    public void nextPage() {

        if (hasNextPage()) {
            navTo(curPageIdx + 1);
        }
    }

    /**
     * navigate to previous page if it exists
     */
    @Override
    public void priorPage() {

        if (hasPriorPage()) {
            navTo(history.pop(), false);
        }
    }

    /**
     * @return true if next page exists
     */
    @Override
    public boolean hasNextPage() {

        return (curPageIdx < pages.size() - 1);
    }

    /**
     * @return true if previous page exists
     */
    @Override
    public boolean hasPriorPage() {

        return !history.isEmpty();
    }

    /**
     * navigate to page and push page on stack
     * 
     * @param nextPageIdx
     *            index of next page
     * @param pushHistory
     *            add page to stack
     */
    @Override
    public void navTo(int nextPageIdx, boolean pushHistory) {

        if (nextPageIdx < 0 || nextPageIdx >= pages.size())
            return;
        if (curPageIdx != UNDEFINED) {
            if (pushHistory) {
                history.push(curPageIdx);
            }
        }

        WizardPage nextPage = pages.get(nextPageIdx);
        curPageIdx = nextPageIdx;
        getChildren().clear();
        getChildren().add(nextPage);
        nextPage.manageButtons();
    }

    /**
     * navigate to next page with this index
     * 
     * @param nextPageIdx
     *            index to navigate to
     */
    @Override
    public void navTo(int nextPageIdx) {

        navTo(nextPageIdx, true);
    }

    /**
     * navigate to a page with a specific string id
     * 
     * @param id
     *            string value of page
     */
    @Override
    public void navTo(String id) {

        for (WizardPage page : pages) {
            if (page.getByID().equals(id)) {
                navTo(pages.indexOf(page));
            }
        }

    }

    /**
     * gets the index value of a given page
     * 
     * @return index of this page
     */
    @Override
    public int getCurrentPageIndex() {

        return curPageIdx;
    }

    /**
     * close the wizard
     */
    @Override
    public void finish() {

        owner.close();
    }

    /**
     * cancel the wizard
     */
    @Override
    public void cancel() {

        owner.close();
    }


    @Override
    public void setOwner(Stage owner) {

        this.owner = owner;

    }

}
