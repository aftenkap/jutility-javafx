package org.jutility.javafx.control.labeled;

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

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;



/**
 * The {@code ILabeledControl} interface defines the contract for labeled
 * {@link javafx.scene.control.Control Controls}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 */
public interface ILabeledControl {


    /**
     * Returns the label property.
     * 
     * @return the label property.
     */
    ObjectProperty<Label> labelProperty();

    /**
     * Returns the {@link Label}.
     * 
     * @return the {@link Label}.
     */
    Label getLabel();

    /**
     * Sets the {@link Label}.
     * 
     * @param label
     *            the {@link Label}.
     */
    void setLabel(final Label label);

    /**
     * Returns the label {@link Pos Position} property.
     * 
     * @return the label {@link Pos Position} property.
     */
    ObjectProperty<Pos> labelPositionProperty();

    /**
     * Returns the {@link Pos Position} of the {@link Label}.
     * 
     * @return the {@link Pos Position} of the {@link Label}.
     */
    Pos getLabelPosition();

    /**
     * Sets the {@link Pos Position} of the {@link Label}.
     * 
     * @param labelPosition
     *            the {@link Pos Position} of the {@link Label}.
     */
    void setLabelPosition(final Pos labelPosition);
}
