package org.jutility.javafx.control.dialog;



import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;


/**
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
public abstract class PasswordDialog
        extends Dialog {

    private final TextField     txUserName;
    private final PasswordField txPassword;
    private final Action        loginAction;
    private final GridPane      content;


    /**
     * Returns the login action.
     * 
     * @return the login action.
     */
    protected Action getLoginAction() {

        return this.loginAction;
    }


    /**
     * Returns the user name textfield.
     * 
     * @return the user name textfield.
     */
    protected TextField getTxUserName() {

        return this.txUserName;
    }


    /**
     * Returns the password textfield.
     * 
     * @return the password textfield.
     */
    protected PasswordField getTxPassword() {

        return this.txPassword;
    }


    /**
     * Creates a new instance of the {@link PasswordDialog} class.
     * 
     * @param owner
     *            the owner.
     * @param title
     *            the title.
     */
    public PasswordDialog(Window owner, String title) {

        super(owner, title);
        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);
        this.setContent(content);

        // This dialog will consist of two input fields (username and password),
        // and have two buttons: Login and Cancel.
        txUserName = new TextField();
        txUserName.setPromptText("Enter Username");
        txUserName.setText("privacyworkbench");

        txPassword = new PasswordField();
        txPassword.setPromptText("Enter Password");
        txPassword.setText("Pr1vacy$ystem");

        txUserName.setMaxHeight(Double.MAX_VALUE);
        txUserName.setMaxWidth(Double.MAX_VALUE);
        txPassword.setMaxHeight(Double.MAX_VALUE);
        txPassword.setMaxWidth(Double.MAX_VALUE);



        loginAction = new DefaultDialogAction("Login") {

            {
                ButtonBar.setType(this, ButtonType.OK_DONE);
            }

            // This method is called when the login button is clicked...
            @Override
            public void handle(ActionEvent ae) {

                if (!isDisabled()) {
                    if (ae.getSource() instanceof Dialog) {

                        Dialog dlg = (Dialog) ae.getSource();
                        boolean loginSuccessful = PasswordDialog.this.login();

                        if (loginSuccessful) {

                            dlg.setResult(this);
                        }
                    }
                }
            }
        };


        // create the dialog with a custom graphic and the gridpane above as the
        // main content region

        // TODO: move to local resources
        // this.setGraphic(new
        // ImageView(DialogResources.getImage("login.image")));

        this.getActions().addAll(loginAction, Dialog.Actions.CANCEL);



        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                PasswordDialog.this.validate();
            }
        };
        this.txUserName.textProperty().addListener(changeListener);
        this.txPassword.textProperty().addListener(changeListener);
    }


    /**
     * @param startRow
     */
    protected void init(int startRow) {

        int row = startRow;



        content.add(new Label("Username"), 0, row);
        content.add(txUserName, 1, row);
        row++;
        GridPane.setHgrow(txUserName, Priority.ALWAYS);
        content.add(new Label("Password"), 0, row);
        content.add(txPassword, 1, row);
        GridPane.setHgrow(txPassword, Priority.ALWAYS);
        // request focus on the username field by default (so the user can
        // type immediately without having to click first)
        if (startRow == 0) {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {

                    txUserName.requestFocus();
                }
            });
        }


        this.validate();
    }

    //
    /**
     * This method is called when the user types into the username / password
     * fields
     */
    protected void validate() {

        loginAction.disabledProperty().set(
                txUserName.getText() == null
                        || txUserName.getText().trim().isEmpty()
                        || txPassword.getText() == null
                        || txPassword.getText().trim().isEmpty());

    }

    /**
     * The login method.
     * 
     * @return {@code true}, if login was successful; {@code false} otherwise.
     */
    public abstract boolean login();

}
