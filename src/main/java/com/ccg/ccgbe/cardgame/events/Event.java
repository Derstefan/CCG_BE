package com.ccg.ccgbe.cardgame.events;


import java.util.Date;

/**
 * For tracking
 */
public abstract class Event {

    private Date date;

    public Event(){
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public abstract String toString();
}
