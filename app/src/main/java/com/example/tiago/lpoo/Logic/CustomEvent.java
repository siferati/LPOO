package com.example.tiago.lpoo.Logic;

import android.view.MotionEvent;

/**
 * Works like a C++ struct and represents a Motion Event with additional info
 */
public class CustomEvent {

    /**
     * Char that represents what button sent this event: W(ater), A(ir), E(arth), F(ire)
     */
    public final char button;

    /**
     * The Motion Event
     */
    public MotionEvent event;

    /**
     * Constructor
     *
     * @param event
     * @param button
     */
    public CustomEvent(MotionEvent event, char button)
    {
        this.button = button;
        this.event = event;
    }
}
