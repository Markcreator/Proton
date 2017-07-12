package me.Markcreator.Proton.event;

public class EventListener {

	private EventCaller caller;
	private Class<? extends Event> eventType;
	private EventHandler handler = null;

	public EventListener(EventCaller caller) {
		this.caller = caller;
	}
	
	public Class<? extends Event> getEventType() {
		return eventType;
	}
	
	public EventHandler getHandler() {
		return handler;
	}

	public void awaitEventOnce(Class<? extends Event> eventType) {
		this.eventType = eventType;
		
		caller.registerListener(this);

		synchronized (this) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		caller.unregisterListener(this);
	}

	public boolean hasHandler() {
		return getHandler() != null;
	}

	public void onEvent(EventHandler handler) {
		this.handler = handler;
		this.eventType = handler.getEventType();
		
		caller.registerListener(this);
	}
}
