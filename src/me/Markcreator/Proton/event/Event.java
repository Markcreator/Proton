package me.Markcreator.Proton.event;

public abstract class Event {

	EventCaller caller;
	
	public Event(EventCaller caller) {
		this.caller = caller;
	}
	
	public EventCaller getCaller() {
		return caller;
	}
}
