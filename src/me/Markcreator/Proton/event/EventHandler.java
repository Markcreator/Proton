package me.Markcreator.Proton.event;

public abstract class EventHandler {

	private Class<? extends Event> eventType;
	
	public EventHandler(Class<? extends Event> eventType) {
		this.eventType = eventType;
	}
	
	public Class<? extends Event> getEventType() {
		return eventType;
	}
	
	public abstract void handle(Event event);
	
}