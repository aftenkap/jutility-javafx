package org.jutility.javafx.events;


import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Custom event that contains information of a changed selection index.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class SelectedIndexChangedEvent
        extends Event {

    /**
     * Defines the event type SELECTED_INDEX_CHANGED.
     */
    public static final EventType<SelectedIndexChangedEvent> SELECTED_INDEX_CHANGED = new EventType<>(
                                                                                            "SELECTED_ITEM_CHANGED");

    private static final long                                serialVersionUID       = 1L;
    private final Number                                     oldValue;
    private final Number                                     newValue;



    /**
     * Returns the old value.
     * 
     * @return the old value.
     */
    public Object getOldValue() {

        return oldValue;
    }


    /**
     * Returns the new value.
     * 
     * @return the new value.
     */
    public Object getNewValue() {

        return newValue;
    }


    /**
     * Creates a new {@link SelectedIndexChangedEvent} with the provided old and
     * new value.
     * 
     * @param oldValue
     *            the old value.
     * @param newValue
     *            the new value.
     */
    public SelectedIndexChangedEvent(Number oldValue, Number newValue) {

        this(SELECTED_INDEX_CHANGED, oldValue, newValue);
    }

    /**
     * Creates a new {@link SelectedIndexChangedEvent} with the provided event
     * type, old value, and new value.
     * 
     * @param eventType
     *            the type of the event.
     * @param oldValue
     *            the old value.
     * @param newValue
     *            the new value.
     */
    public SelectedIndexChangedEvent(EventType<? extends Event> eventType,
            Number oldValue, Number newValue) {

        super(eventType);

        this.oldValue = oldValue;
        this.newValue = newValue;
    }



    /**
     * Creates a new {@link SelectedIndexChangedEvent} with the provided source,
     * target, event type, old value, and new value.
     * 
     * @param source
     *            The source of the event.
     * @param target
     *            The event target.
     * @param eventType
     *            The event type
     * @param oldValue
     *            the old value.
     * @param newValue
     *            the new value.
     */
    public SelectedIndexChangedEvent(Object source, EventTarget target,
            EventType<? extends Event> eventType, Number oldValue,
            Number newValue) {

        super(source, target, eventType);

        this.oldValue = oldValue;
        this.newValue = newValue;
    }


}
