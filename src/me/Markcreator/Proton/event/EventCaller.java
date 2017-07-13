package me.Markcreator.Proton.event;

import java.util.ArrayList;

public interface EventCaller {
	
	ArrayList<EventListener> listeners = new ArrayList<>();
	
	public default void registerListener(EventListener l) {
		if(!listeners.contains(l)) {
			listeners.add(l);
		}
	}
	
	public default void unregisterListener(EventListener l) {
		listeners.remove(l);
	}
	
	public default void callEvent(Event event) {
		System.out.println("Calling " + event.getClass().getSimpleName());
		
		for(EventListener l : listeners) {
			if(l instanceof SingleEventWaiter) {
				SingleEventWaiter a = (SingleEventWaiter) l;
				
				if(a.getEventType().equals(event.getClass())) {
					synchronized (a) {
						a.notify();
					}
				}
				
			} else if(l instanceof EventHandler) {
				EventHandler h = (EventHandler) l;
				
				h.handle(event);
			}
		}
	}
}
