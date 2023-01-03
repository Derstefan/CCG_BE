package com.ccg.ccgbe.cardgame.events;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class EventHistroy {
    private ArrayList<Event> events = new ArrayList<>();

    public void addEvent(Event e){
      log.info(e.toString());
        events.add(e);
    }





}
