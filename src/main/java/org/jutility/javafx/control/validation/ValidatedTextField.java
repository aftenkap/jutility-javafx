package org.jutility.javafx.control.validation;


//@formatter:off
/*
* #%L
 * * jutility-javafx 
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org 
 * *
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


import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import org.jutility.javafx.control.icon.ErrorIcon;
import org.jutility.javafx.control.labeled.LabeledTextField;


/**
 * The {@link ValidatedTextField} class provides the capability to visually
 * represent the validity of a {@link LabeledTextField}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 * @deprecated since 0.1.2
 */
public class ValidatedTextField
        extends LabeledTextField {

    private static final double                        iconSize = 15;

    private final Orientation                          orientation;

    private final SimpleBooleanProperty                valid;
    private final SimpleBooleanProperty                alwaysShowingGraphic;

    private final ObjectProperty<Node>                 invalidGraphic;
    private final ObjectProperty<Node>                 validGraphic;
    private final ObjectProperty<NotificationSeverity> notificationSeverity;

    private final Label                                notificationLabel;
    private Node                                       graphicsNode;


    /**
     * Returns the validity property.
     * 
     * @return the validity property.
     */
    public SimpleBooleanProperty valid() {

        return this.valid;
    }

    /**
     * Returns whether or not the contents of the {@link TextField} are valid.
     * 
     * @return {@code true}, if the contents of the {@link TextField} are valid;
     *         {@code false} otherwise.
     */
    public Boolean isValid() {

        return this.valid.get();
    }

    /**
     * Sets the validity of the contents of the {@link TextField} are valid
     * 
     * @param validity
     *            set to {@code true}, if the contents of the {@link TextField}
     *            are valid; {@code false} otherwise.
     */
    public void setValid(boolean validity) {

        this.valid.set(validity);
    }

    /**
     * Returns the alwaysShowingGraphic property.
     * 
     * @return the alwaysShowingGraphic property.
     */
    public SimpleBooleanProperty alwaysShowingGraphic() {

        return this.alwaysShowingGraphic;
    }

    /**
     * Returns whether or not the {@link ValidatedTextField} always shows the
     * notification {@link Node Graphic} (even if the contents are valid).
     * 
     * @return {@code true}, if the {@link ValidatedTextField} always shows the
     *         notification {@link Node Graphic}; {@code false} otherwise.
     */
    public boolean isAlwaysShowingGraphic() {

        return this.alwaysShowingGraphic.get();
    }

    /**
     * Sets whether or not the {@link ValidatedTextField} always shows the
     * notification {@link Node Graphic} (even if the contents are valid).
     * 
     * @param alwaysShowingGraphic
     *            set to {@code true}, if the {@link ValidatedTextField} should
     *            always show the notification {@link Node Graphic};
     *            {@code false} otherwise.
     */
    public void setAlwaysShowingGraphic(boolean alwaysShowingGraphic) {

        this.alwaysShowingGraphic.set(alwaysShowingGraphic);
    }

    /**
     * Returns the notificationText property.
     * 
     * @return the notificationText property.
     */
    public StringProperty notificationText() {

        return this.notificationLabel.textProperty();
    }

    /**
     * Returns the notification text.
     * 
     * @return the notification text.
     */
    public String getNotificationText() {

        return this.notificationText().get();
    }

    /**
     * Sets the notification text.
     * 
     * @param notificationText
     *            the notification text.
     */
    public void setNotificationText(String notificationText) {

        this.notificationText().set(notificationText);
    }

    /**
     * Returns the invalidGraphic property.
     * 
     * @return the invalidGraphic property.
     */
    public ObjectProperty<Node> invalidGraphic() {

        return this.invalidGraphic;
    }

    /**
     * Returns the notification graphic for invalid content.
     * 
     * @return the notification graphic for invalid content.
     */
    public Node getInvalidGraphic() {

        return this.invalidGraphic.get();
    }

    /**
     * Sets the notification graphic for invalid content.
     * 
     * @param invalidGraphic
     *            the notification graphic for invalid content.
     */
    public void setInvalidGraphic(Node invalidGraphic) {

        this.invalidGraphic.set(invalidGraphic);
    }



    /**
     * Returns the validGraphic property.
     * 
     * @return the validGraphic property.
     */
    public ObjectProperty<Node> validGraphic() {

        return this.validGraphic;
    }

    /**
     * Returns the notification graphic for valid content.
     * 
     * @return the notification graphic for valid content.
     */
    public Node getValidGraphic() {

        return this.validGraphic.get();
    }

    /**
     * Sets the notification graphic for valid content.
     * 
     * @param validGraphic
     *            the notification graphic for valid content.
     */
    public void setValidGraphic(Node validGraphic) {

        this.validGraphic.set(validGraphic);
    }


    /**
     * Returns the notificationSeverity property.
     * <p/>
     * The severity is used to automatically determine the notification graphic.
     * It is ignored if a notification graphic is set.
     * 
     * @return the invalidGraphic property.
     */
    public ObjectProperty<NotificationSeverity> notificationSeverity() {

        return this.notificationSeverity;
    }

    /**
     * Returns the notification severity.
     * <p/>
     * The severity is used to automatically determine the notification graphic.
     * It is ignored if a notification graphic is set.
     * 
     * @return the notification severity.
     */
    public NotificationSeverity getNotificationSeverity() {

        return this.notificationSeverity.get();
    }

    /**
     * Sets the notification severity.
     * <p/>
     * The severity is used to automatically determine the notification graphic.
     * It is ignored if a notification graphic is set.
     * 
     * @param notificationSeverity
     *            the notification severity.
     */
    public void setNotificationSeverity(
            NotificationSeverity notificationSeverity) {

        this.notificationSeverity.set(notificationSeverity);
    }

    /**
     * Creates a new instance of the {@link ValidatedTextField} class with the
     * provided label and warning text, positioning the {@link Label} to the
     * left of the {@link TextField} and the warning to the right.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param notificationText
     *            the text of the notification.
     */
    public ValidatedTextField(String labelText, String notificationText) {

        this(labelText, notificationText, Orientation.HORIZONTAL);
    }

    /**
     * Creates a new instance of the {@link ValidatedTextField} class with the
     * provided label text, positioning the {@link Label} and warning relative
     * to the {@link TextField} according to the provided {@link Orientation}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param notificationText
     *            the text of the notification.
     * @param orientation
     *            the desired {@link Orientation} of the
     *            {@link ValidatedTextField} (positioning of label and warning).
     */
    public ValidatedTextField(String labelText, String notificationText,
            Orientation orientation) {

        this(labelText, notificationText, orientation, null);
    }

    /**
     * Creates a new instance of the {@link ValidatedTextField} class with the
     * provided label text, positioning the {@link Label} and warning relative
     * to the {@link TextField} according to the provided {@link Orientation}.
     * 
     * @param labelText
     *            the text of the {@link Label}.
     * @param notificationText
     *            the text of the notification.
     * @param orientation
     *            the desired {@link Orientation} of the
     *            {@link ValidatedTextField} (positioning of label and warning).
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public ValidatedTextField(String labelText, String notificationText,
            Orientation orientation, String text) {

        this(new Label(labelText), notificationText, orientation, text);
    }

    /**
     * Creates a new instance of the {@link ValidatedTextField} class with the
     * provided {@link Label}, positioning the {@link Label} to the left of the
     * {@link TextField}.
     * 
     * @param label
     *            the {@link Label}.
     * @param notificationText
     *            the text of the notification.
     */
    public ValidatedTextField(Label label, String notificationText) {

        this(label, notificationText, Orientation.HORIZONTAL);
    }

    /**
     * Creates a new instance of the {@link ValidatedTextField} class. with the
     * provided {@link Label}, positioning the {@link Label} and warning
     * relative to the {@link TextField} according to the provided
     * {@link Orientation}.
     * 
     * @param label
     *            the {@link Label}.
     * @param notificationText
     *            the text of the notification.
     * @param orientation
     *            the desired {@link Orientation} of the
     *            {@link ValidatedTextField} (positioning of label and warning).
     */
    public ValidatedTextField(Label label, String notificationText,
            Orientation orientation) {

        this(label, notificationText, orientation, null);
    }

    /**
     * Creates a new instance of the {@link ValidatedTextField} class with the
     * provided {@link Label}, positioning the {@link Label} and warning
     * relative to the {@link TextField} according to the provided
     * {@link Orientation}.
     * 
     * @param label
     *            the {@link Label}.
     * @param notificationText
     *            the text of the notification.
     * @param orientation
     *            the desired {@link Orientation} of the
     *            {@link ValidatedTextField} (positioning of label and warning).
     * @param text
     *            the initial text of the {@link TextField}.
     */
    public ValidatedTextField(Label label, String notificationText,
            Orientation orientation, String text) {

        super(label, (orientation == Orientation.HORIZONTAL ? Pos.CENTER_LEFT
                : Pos.TOP_CENTER), text);

        this.valid = new SimpleBooleanProperty(true);
        this.alwaysShowingGraphic = new SimpleBooleanProperty(false);
        this.invalidGraphic = new SimpleObjectProperty<>();
        this.validGraphic = new SimpleObjectProperty<>();
        this.notificationSeverity = new SimpleObjectProperty<>(
                NotificationSeverity.WARNING);

        this.orientation = orientation;

        this.notificationLabel = new Label(notificationText);
        this.notificationLabel.setTextFill(Color.RED);
        this.notificationLabel.setVisible(false);

        this.notificationLabel.visibleProperty().bind(Bindings.not(this.valid));

        switch (this.orientation) {
            case HORIZONTAL:

                this.addNode(notificationLabel, Pos.CENTER_RIGHT);
                break;
            case VERTICAL:
                this.addNode(notificationLabel, Pos.BOTTOM_CENTER);
                break;
            default:
                break;

        }

        this.setUpEventHandlers();
        this.performLayout();
    }


    private void setUpEventHandlers() {

        this.valid.addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {

                ValidatedTextField.this.performLayout();
            }
        });

        this.alwaysShowingGraphic.addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {

                if (ValidatedTextField.this.isValid()) {

                    ValidatedTextField.this.performLayout();
                }
            }
        });

        this.invalidGraphic.addListener(new ChangeListener<Node>() {

            @Override
            public void changed(ObservableValue<? extends Node> observable,
                    Node oldValue, Node newValue) {

                ValidatedTextField.this.performLayout();
            }
        });
        this.validGraphic.addListener(new ChangeListener<Node>() {

            @Override
            public void changed(ObservableValue<? extends Node> observable,
                    Node oldValue, Node newValue) {

                ValidatedTextField.this.performLayout();
            }
        });

        this.notificationSeverity
                .addListener(new ChangeListener<NotificationSeverity>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends NotificationSeverity> observable,
                            NotificationSeverity oldValue,
                            NotificationSeverity newValue) {

                        ValidatedTextField.this.performLayout();
                    }
                });
    }

    /**
     * Performs the layouting operations.
     */
    @Override
    protected void performLayout() {

        Node notificationGraphic = null;

        if (this.isValid()) {

            notificationGraphic = this.getValidGraphic();

            if (notificationGraphic == null) {

                notificationGraphic = new ErrorIcon(ValidatedTextField.iconSize);
            }
        }
        else {

            notificationGraphic = this.getInvalidGraphic();

            if (notificationGraphic == null) {

                switch (this.getNotificationSeverity()) {
                    case ERROR:
                        notificationGraphic = new ErrorIcon(
                                ValidatedTextField.iconSize);
                        break;
                    case INFO:
                        notificationGraphic = new ErrorIcon(
                                ValidatedTextField.iconSize);
                        break;
                    case WARNING:
                        notificationGraphic = new ErrorIcon(
                                ValidatedTextField.iconSize);
                        break;
                    default:
                        break;
                }
            }
        }

        this.graphicsNode = notificationGraphic;
        this.graphicsNode.setVisible(this.showNotification());

        switch (this.orientation) {
            case HORIZONTAL:

                this.notificationLabel.setGraphic(this.graphicsNode);

                break;
            case VERTICAL:

                this.getChildren().remove(this.graphicsNode);
                this.addNode(notificationGraphic, Pos.CENTER_RIGHT);

                break;
            default:
                break;
        }
    }


    private boolean showNotification() {

        return this.isAlwaysShowingGraphic() || !this.isValid();
    }

    /**
     * The severity of a validation failure.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * @since 1.0
     */
    public static enum NotificationSeverity {

        /**
         * Notify about the existence of a validation failure.
         */
        INFO,
        /**
         * Warn about the existence of a validation failure.
         */
        WARNING,
        /**
         * Urgently warn about the existence of a validation failure.
         */
        ERROR;
    }


}
