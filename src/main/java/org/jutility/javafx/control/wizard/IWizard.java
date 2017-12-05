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



import javafx.stage.Stage;


/**
 * The {@code IWizard} interface provides the shared contract for Wizards.
 * 
 * @author Shawn P. Neuman
 * @version 0.1.2
 * @since 0.1.0
 * @deprecated since 0.1.2 in favor of {@link org.controlsfx.dialog.Wizard}.
 */
@Deprecated
public interface IWizard {


    /**
     * Sets the owner of the wizard.
     *
     * @param owner
     *            the owner.
     */
    void setOwner(final Stage owner);

    /**
     * Gets the page at a given index.
     *
     * @param index
     *            the integer value of a page.
     * @return the Wizard page at the given index.
     */
    WizardPage getPage(final int index);

    /**
     * Removes a page form the stack.
     *
     * @param index
     *            the index of the page to be removed.
     */
    void removePage(final int index);

    /**
     * adds a page to the stack. used for dynamic adding.
     *
     * @param page
     *            the page to be added.
     */
    void addPage(final WizardPage page);

    /**
     * Adds a page at a specific index.
     *
     * @param index
     *            the index of the page to be added.
     * @param page
     *            the page to be added.
     */
    void addPage(int index, WizardPage page);

    /**
     * Navigate to next page if it exists.
     */
    void nextPage();

    /**
     * Navigate to previous page if it exists.
     */
    void priorPage();

    /**
     * Returns whether or not the {@code IWizard} contains a page after the
     * current page.
     *
     * @return {@code true}, if a page after the current page exists;
     *         {@code false} otherwise.
     */
    boolean hasNextPage();

    /**
     * Returns whether or not the {@code IWizard} contains a page before the
     * current page.
     *
     * @return {@code true}, if a page before the current page exists;
     *         {@code false} otherwise.
     */
    boolean hasPriorPage();

    /**
     * Navigate to page and push page on stack.
     *
     * @param nextPageIdx
     *            index of next page.
     * @param pushHistory
     *            add page to stack.
     */
    void navTo(final int nextPageIdx, final boolean pushHistory);

    /**
     * Navigate to next page with this index.
     *
     * @param nextPageIdx
     *            index to navigate to.
     */
    void navTo(final int nextPageIdx);

    /**
     * Navigate to a page with a specific string id.
     *
     * @param id
     *            string value of page.
     */
    void navTo(final String id);

    /**
     * Gets the index value of a given page.
     *
     * @return index of this page.
     */
    int getCurrentPageIndex();

    /**
     * Close the wizard.
     */
    void finish();

    /**
     * Cancel the wizard.
     */
    void cancel();
}
