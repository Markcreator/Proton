package me.Markcreator.Proton.event;

public class EventListener {

	private EventCaller caller;
	private Class<? extends Event> event;

	public EventListener(EventCaller caller, Class<? extends Event> event) {
		this.caller = caller;
		this.event = event;
	}

	public Class<? extends Event> getEvent() {
		return event;
	}

	public void awaitEventOnce() {
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
}
