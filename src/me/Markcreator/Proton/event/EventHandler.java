package me.Markcreator.Proton.event;

public abstract class EventHandler extends EventListener {

	public EventHandler(EventCaller caller) {
		super(caller);
	}
	
	public abstract void handle(Event event);
}