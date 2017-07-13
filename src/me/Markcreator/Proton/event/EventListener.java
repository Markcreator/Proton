package me.Markcreator.Proton.event;

public abstract class EventListener {

	private EventCaller caller;
	
	public EventListener(EventCaller caller) {		
		caller.registerListener(this);
	}
	
	public EventCaller getEventCaller() {
		return caller;
	}
}
