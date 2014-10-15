package org.jutility.javafx.events;

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



import java.util.Collections;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Custom event that contains information of a changed selection of an item.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @param <T>
 *            the content type of the list.
 * 
 */
public class MultiSelectionItemChangedEvent<T>
        extends Event {

    /**
     * Defines the event type MULTI_SELECTION_ITEM_CHANGED.
     */
    public static final EventType<MultiSelectionItemChangedEvent<?>> MULTI_SELECTION_ITEM_CHANGED = new EventType<>(
                                                                                                          "MULTI_SELECTION_ITEM_CHANGED");

    private static final long                                        serialVersionUID             = 1L;
    private final List<T>                                            oldValues;
    private final List<T>                                            newValues;



    /**
     * Returns the old values.
     * 
     * @return the old values.
     */
    public List<T> getOldValues() {

        return Collections.unmodifiableList(this.oldValues);
    }


    /**
     * Returns the new values.
     * 
     * @return the new values.
     */
    public List<T> getNewValues() {

        return Collections.unmodifiableList(this.newValues);
    }


    /**
     * Creates a new {@link MultiSelectionItemChangedEvent} with the provided
     * old and new values.
     * 
     * @param oldValues
     *            the old selection values.
     * @param newValues
     *            the new selection values.
     */
    public MultiSelectionItemChangedEvent(List<T> oldValues, List<T> newValues) {

        this(MULTI_SELECTION_ITEM_CHANGED, oldValues, newValues);
    }

    /**
     * Creates a new {@link MultiSelectionIndexChangedEvent} with the provided
     * event type, old and new values.
     * 
     * @param eventType
     *            The event type
     * @param oldValues
     *            the old selection values.
     * @param newValues
     *            the new selection values.
     */
    public MultiSelectionItemChangedEvent(EventType<? extends Event> eventType,
            List<T> oldValues, List<T> newValues) {

        super(eventType);

        this.oldValues = oldValues;
        this.newValues = newValues;
    }



    /**
     * Creates a new {@link MultiSelectionIndexChangedEvent} with the provided
     * source, target, event type, old and new values.
     * 
     * @param source
     *            The source of the event.
     * @param target
     *            The event target.
     * @param eventType
     *            The event type
     * @param oldValues
     *            the old selection values.
     * @param newValues
     *            the new selection values.
     */
    public MultiSelectionItemChangedEvent(Object source, EventTarget target,
            EventType<? extends Event> eventType, List<T> oldValues,
            List<T> newValues) {

        super(source, target, eventType);
        this.oldValues = oldValues;
        this.newValues = newValues;
    }

}
