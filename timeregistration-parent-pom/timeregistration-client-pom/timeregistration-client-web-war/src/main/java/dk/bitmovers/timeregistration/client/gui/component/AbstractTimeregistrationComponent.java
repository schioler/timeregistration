package dk.bitmovers.timeregistration.client.gui.component;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.CustomComponent;

import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventEmitter;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.gui.view.ViewTokens;

public abstract class AbstractTimeregistrationComponent extends CustomComponent implements TimeregistrationEventEmitter {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private final List<TimeregistrationEventListener> eventListeners = new ArrayList<TimeregistrationEventListener>();

	public AbstractTimeregistrationComponent() {
		super();
	
	}

	public void addTimeregistrationEventListener(TimeregistrationEventListener listener) {
		eventListeners.add(listener);
	}

	protected void notify(TimeregistrationEvent event) {
		for (TimeregistrationEventListener timeregistrationEventListener : eventListeners) {
			if (timeregistrationEventListener.supports(event)) {
				logger.debug("Will notify listener={}", timeregistrationEventListener);
				timeregistrationEventListener.handleEvent(event);
			}
		}
	}

	public void updateContent(DataProvider dataProvider) {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		updateContent(session, trSession, dataProvider);
	}
	
	public abstract void  clear();

	public abstract void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider);
}
