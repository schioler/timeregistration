package dk.bitmovers.timeregistration.client.gui.event;

import dk.bitmovers.timeregistration.common.TimeregistrationException;

public abstract class AbstractTimeregistrationEventListenerImpl implements TimeregistrationEventListener {

	public AbstractTimeregistrationEventListenerImpl() {
	
	}



	@Override
	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
	
		return null;
	}

}
