package dk.bitmovers.timeregistration.client.gui.event;


public interface TimeregistrationEventEmitter {
	public void addTimeregistrationEventListener(TimeregistrationEventListener listener);
}
