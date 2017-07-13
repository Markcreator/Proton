package me.Markcreator.Proton.event;

public class SingleEventWaiter extends EventListener {

	private Class<? extends Event> eventType;
	
	// Wait for a specific event to trigger once
	public SingleEventWaiter(EventCaller caller, Class<? extends Event> eventType) {
		super(caller);
		this.eventType = eventType;
		
		synchronized (this) {
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		caller.unregisterListener(this);
	}
	
	public Class<? extends Event> getEventType() {
		return eventType;
	}
}
