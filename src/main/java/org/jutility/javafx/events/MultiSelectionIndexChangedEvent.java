package org.jutility.javafx.events;


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
public class MultiSelectionIndexChangedEvent<T extends Number>
        extends Event {

    /**
     * Defines the event type MULTI_SELECTION_ITEM_CHANGED.
     */
    public static final EventType<MultiSelectionIndexChangedEvent<? extends Number>> MULTI_SELECTION_INDEX_CHANGED = new EventType<>(
                                                                                                          "MULTI_SELECTION_INDEX_CHANGED");

    private static final long                                        serialVersionUID             = 1L;
    private final List<T>                                                  oldValues;
    private final List<T>                                                  newValues;



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
     * Creates a new {@link MultiSelectionIndexChangedEvent} with the provided
     * old and new values.
     * 
     * @param oldValues
     *            the old selection values.
     * @param newValues
     *            the new selection values.
     */
    public MultiSelectionIndexChangedEvent(List<T> oldValues, List<T> newValues) {

        this(MULTI_SELECTION_INDEX_CHANGED, oldValues, newValues);
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
    public MultiSelectionIndexChangedEvent(EventType<? extends Event> eventType,
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
    public MultiSelectionIndexChangedEvent(Object source, EventTarget target,
            EventType<? extends Event> eventType, List<T> oldValues, List<T> newValues) {

        super(source, target, eventType);
        this.oldValues = oldValues;
        this.newValues = newValues;
    }

}
