package org.jutility.javafx.control.wizard;


import javafx.stage.Stage;


/**
 * @author spn2460
 * 
 */
public interface IWizard {


    /**
     * Sets the owner of the wizard.
     * 
     * @param owner
     *            the owner.
     */
    public void setOwner(Stage owner);

    /**
     * gets the page at a given index
     * 
     * @param index
     *            the integer value of a page
     * @return the Wizard page at the given index
     */
    public WizardPage getPage(int index);

    /**
     * removes a page form the stack
     * 
     * @param index
     *            the index of the page to be removed
     */
    public void removePage(int index);

    /**
     * adds a page to the stack. used for dynamic adding
     * 
     * @param page
     *            the page to be added
     */
    public void addPage(WizardPage page);

    /**
     * adds a page at a specific index
     * 
     * @param index
     *            the index of the page to be added
     * @param page
     *            the page to be added
     */
    public void addPage(int index, WizardPage page);

    /**
     * navigate to next page if it exists
     */
    public void nextPage();

    /**
     * navigate to previous page if it exists
     */
    public void priorPage();

    /**
     * @return true if next page exists
     */
    public boolean hasNextPage();

    /**
     * @return true if previous page exists
     */
    public boolean hasPriorPage();

    /**
     * navigate to page and push page on stack
     * 
     * @param nextPageIdx
     *            index of next page
     * @param pushHistory
     *            add page to stack
     */
    void navTo(int nextPageIdx, boolean pushHistory);

    /**
     * navigate to next page with this index
     * 
     * @param nextPageIdx
     *            index to navigate to
     */
    public void navTo(int nextPageIdx);

    /**
     * navigate to a page with a specific string id
     * 
     * @param id
     *            string value of page
     */
    public void navTo(String id);

    /**
     * gets the index value of a given page
     * 
     * @return index of this page
     */
    public int getCurrentPageIndex();

    /**
     * close the wizard
     */
    public void finish();

    /**
     * cancel the wizard
     */
    public void cancel();
}
