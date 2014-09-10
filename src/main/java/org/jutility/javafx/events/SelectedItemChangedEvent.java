package org.jutility.javafx.events;


import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


/**
 * Custom event that contains information of a changed selection of an item.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class SelectedItemChangedEvent
        extends Event {

    /**
     * Defines the event type SELECTED_ITEM_CHANGED.
     */
    public final static EventType<SelectedItemChangedEvent> SELECTED_ITEM_CHANGED = new EventType<>(
                                                                                             "SELECTED_ITEM_CHANGED");



    private static final long serialVersionUID = 1L;
    private final Object           oldValue;
    private final Object           newValue;



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
     * Creates a new {@link SelectedItemChangedEvent} with the provided old and
     * new value.
     * 
     * @param oldValue
     *            the old value.
     * @param newValue
     *            the new value.
     */
    public SelectedItemChangedEvent(Object oldValue, Object newValue) {

        this(SELECTED_ITEM_CHANGED, oldValue, newValue);
    }

    /**
     * Creates a new {@link SelectedItemChangedEvent} with the provided event
     * type, old value, and new value.
     * 
     * @param eventType
     *            the type of the event.
     * @param oldValue
     *            the old value.
     * @param newValue
     *            the new value.
     */
    public SelectedItemChangedEvent(EventType<? extends Event> eventType,
            Object oldValue, Object newValue) {

        super(eventType);

        this.oldValue = oldValue;
        this.newValue = newValue;
    }



    /**
     * Creates a new {@link SelectedItemChangedEvent} with the provided source,
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
    public SelectedItemChangedEvent(Object source, EventTarget target,
            EventType<? extends Event> eventType, Object oldValue,
            Object newValue) {

        super(source, target, eventType);

        this.oldValue = oldValue;
        this.newValue = newValue;
    }


}
