package dk.bitmovers.timeregistration.client.gui.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;

public abstract class AbstractTimeregistrationAbstractComponentComponent extends AbstractTimeregistrationComponent implements ValueChangeListener {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected AbstractLayout layout;

	protected Label lbl;

	protected AbstractField<?> component;

	public AbstractTimeregistrationAbstractComponentComponent(AbstractLayout layout, Label lbl, AbstractField<?> component) {
		super();
		this.layout = layout;
		this.lbl = lbl;
		this.component = component;
		if (lbl != null) {
			layout.addComponent(this.lbl);
		}
		layout.addComponent(this.component);
		setCompositionRoot(this.layout);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		logger.debug("eventCaptured");
		String v = (String) event.getProperty().getValue();
		logger.debug("Event={}", v);

		if (v != null) {

			TimeregistrationEvent te = getTimeregistrationEvent(v, component);

			this.notify(te);
		}
	}
	
	public String getValue(){
		return (String) this.component.getValue();
	}

	protected abstract TimeregistrationEvent getTimeregistrationEvent(String value, AbstractField<?> component);

	//	protected void notify(TimeregistrationEvent event) {
	//		for (TimeregistrationEventListener timeregistrationEventListener : eventListeners) {
	//			if (timeregistrationEventListener.supports(event)) {
	//				logger.debug("Will notify listener={}", timeregistrationEventListener);
	//				timeregistrationEventListener.handleEvent(event);
	//			}
	//		}
	//	}
	//
	//	public void updateContent(DataProvider dataProvider) {
	//		WrappedSession session = VaadinSession.getCurrent().getSession();
	//		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
	//		updateContent(session, trSession, dataProvider);
	//	}
	//
	//	public abstract void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider);
}
