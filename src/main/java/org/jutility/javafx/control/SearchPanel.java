package org.jutility.javafx.control;


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



import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.filter.StringFilter;


/**
 * Panel for searching through other panel. Works off CTRL+F and filters through
 * list views to show only items that match search filter
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * @version 0.1.2
 * @since 0.1.1
 * 
 * @param <T>
 *            the type of the objects to be searched for.
 */
public class SearchPanel<T>
        extends LabeledTextField {


    private final StringFilter<T> stringFilter;
    private final Hyperlink       close;

    /**
     * Returns the string filter of this {@code SearchPanel}.
     * 
     * @return the string filter of this {@code SearchPanel}.
     */
    public StringFilter<T> getStringFilter() {

        return this.stringFilter;
    }


    /**
     * Creates a new instance of the {@link SearchPanel} class.
     */
    public SearchPanel() {

        super((String) null);

        this.stringFilter = new StringFilter<>();

        this.setWrapped(TextFields.createClearableTextField());
        this.getWrapped().setPromptText("Find");
        if (this.getWrapped() instanceof CustomTextField) {

            ((CustomTextField) this.getWrapped()).setLeft(new Glyph(
                    "FontAwesome", FontAwesome.Glyph.SEARCH));
        }

        GridPane.setHgrow(this.getWrapped(), Priority.SOMETIMES);
        this.stringFilter.filterStringProperty().bindBidirectional(
                this.getWrapped().textProperty());


        this.close = new Hyperlink("Close");
        this.close.addEventHandler(ActionEvent.ACTION, (actionEvent) -> {

            this.getWrapped().setText("");
            this.setVisible(false);
            this.close.setVisited(false);
        });
        this.setEast(this.close);
    }
}
