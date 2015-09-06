package dk.bitmovers.timeregistration.client.gui.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.declarative.Design;

import dk.bitmovers.timeregistration.client.gui.component.ClientComponent;
import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.ProviderComponent;
import dk.bitmovers.timeregistration.client.gui.component.RegistrationItemComponent;
import dk.bitmovers.timeregistration.client.gui.component.StatusComponent;
import dk.bitmovers.timeregistration.client.gui.component.WorkClockComponent;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventProviderUpdate;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventSelectTimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStart;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStop;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.RegistrationEvent;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.WorkClockEvent;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

@Title("View")
@DesignRoot
@SpringView(name = IndexView.VIEW_NAME)
public class IndexView extends AbstractView<RegistrationItemOverviewView> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String VIEW_NAME = "";
	private static final long serialVersionUID = 1L;

	@Autowired
	MessageSource messageSource;

	@Autowired
	protected Menu menu;

	private StatusComponent statusComponent;

	WorkClockComponent workClockComponent;

	ClientComponent clientComponent;

	ProviderComponent providerComponent;

	RegistrationItemComponent registrationItemComponent;

	public IndexView() {
		Design.read(this);
	}

	@PostConstruct
	public void init() {
		clientComponent.addTimeregistrationEventListener(this);
		providerComponent.addTimeregistrationEventListener(this);
		workClockComponent.addTimeregistrationEventListener(this);
		registrationItemComponent.addTimeregistrationEventListener(this);

	}

	@Override
	public void doEnter(ViewChangeEvent event) {
		logger.debug("init. isInit={}", this.isInit());
		try {

			clientComponent.updateContent(getDataProvider());
			providerComponent.updateContent(getDataProvider());
			workClockComponent.updateContent(getDataProvider());

		} catch (Exception e) {
			throw new TimeregistrationException(e.getMessage(), e);
		}
	}

	public Menu getMenu() {
		return menu;
	}

	protected TimeregistrationEvent handleEventInView(TimeregistrationEvent event) {
		TimeregistrationEvent retval = null;

		if (event instanceof TimeregistrationEventClientUpdate) {
			retval = handleClientChange(event);
		} else if (event instanceof TimeregistrationEventProviderUpdate) {
			retval = handleProviderChange(event);
		} else if (event instanceof TimeregistrationEventSelectTimeregistrationEvent) {
			retval = handleTimeregistrationEvent(event);
		} else if (event instanceof TimeregistrationEventWorkClockStart) {
			retval = handleWorkClockStart(event);
		} else if (event instanceof TimeregistrationEventWorkClockStop) {
			retval = handleWorkClockStop(event);
		}

		return retval;

	}

	private TimeregistrationEvent handleWorkClockStop(TimeregistrationEvent event) {
		TimeregistrationEvent retval;
		TimeregistrationEventWorkClockStop cs = (TimeregistrationEventWorkClockStop) event;
		TimeRegistrationSession trSession = getTimeregistrationSession();
		DataProvider dataProvider2 = getDataProvider();

		Integer currentClient = trSession.getCurrentClient() == null ? null : trSession.getCurrentClient().getId();
		Integer currentProvider = trSession.getCurrentProvider() == null ? null : trSession.getCurrentProvider().getId();

		User user = trSession.getUser();
		if (currentClient == null || currentProvider == null) {
			String msg = messageSource.getMessage("workclock.error.missing-providerorclient", null, trSession.getUserLocale());
			cs.updateStatus(msg);
			throw new TimeregistrationException(msg);
		}

		WorkClockEventType type = null;
		List<WorkClockEventType> workClockEventTypes = dataProvider2.getWorkClockEventProvider().getWorkClockEventTypes();
		for (WorkClockEventType workClockEventType : workClockEventTypes) {
			if ("stop".equals(workClockEventType.getEventType())) {
				type = workClockEventType;
				break;
			}
		}

		WorkClockEvent wcEvent = new WorkClockEvent();
		wcEvent.setWorkClockEventType(type);
		wcEvent.setCreated(cs.getTime());
		wcEvent.setUser(user);
		WorkClockEventProvider workClockEventProvider = getDataProvider().getWorkClockEventProvider();
		Integer id = workClockEventProvider.saveWorkClockEvent(wcEvent);
		wcEvent.setId(id);
		trSession.setCurrentWorkClockEvent(wcEvent);
		cs.updateStatus("Stopped workCLock at " + wcEvent.getCreated());
		retval = cs;
		return retval;
	}

	private TimeregistrationEvent handleWorkClockStart(TimeregistrationEvent event) {
		TimeregistrationEvent retval;
		TimeregistrationEventWorkClockStart cs = (TimeregistrationEventWorkClockStart) event;
		TimeRegistrationSession trSession = getTimeregistrationSession();
		DataProvider dataProvider2 = getDataProvider();

		Integer currentClient = trSession.getCurrentClient() == null ? null : trSession.getCurrentClient().getId();
		Integer currentProvider = trSession.getCurrentProvider() == null ? null : trSession.getCurrentProvider().getId();
		User user = trSession.getUser();

		if (currentClient == null || currentProvider == null) {
			String msg = messageSource.getMessage("workclock.error.missing-providerorclient", null, trSession.getUserLocale());
			cs.updateStatus(msg);
			throw new TimeregistrationException(msg);
		}

		WorkClockEventType type = null;
		List<WorkClockEventType> workClockEventTypes = dataProvider2.getWorkClockEventProvider().getWorkClockEventTypes();
		for (WorkClockEventType workClockEventType : workClockEventTypes) {
			if ("start".equals(workClockEventType.getEventType())) {
				type = workClockEventType;
				break;
			}
		}

		WorkClockEvent wcEvent = new WorkClockEvent();
		wcEvent.setWorkClockEventType(type);
		wcEvent.setCreated(cs.getTime());
		wcEvent.setUser(user);
		WorkClockEventProvider workClockEventProvider = getDataProvider().getWorkClockEventProvider();
		Integer id = workClockEventProvider.saveWorkClockEvent(wcEvent);
		wcEvent.setId(id);
		trSession.setCurrentWorkClockEvent(wcEvent);
		cs.updateStatus("Started workCLock at " + wcEvent.getCreated());
		retval = cs;
		return retval;
	}

	private TimeregistrationEvent handleTimeregistrationEvent(TimeregistrationEvent event) {
		TimeregistrationEvent retval;
		TimeregistrationEventSelectTimeregistrationEvent e = (TimeregistrationEventSelectTimeregistrationEvent) event;
		String value = e.getValue();
		String caption = e.getCaption();
		TimeRegistrationSession trSession = getTimeregistrationSession();
		DataProvider dataProvider2 = getDataProvider();

		RegistrationEvent re = new RegistrationEvent();

		Integer saveRegistrationEvent = dataProvider2.saveRegistrationEvent(re);

		e.updateStatus("Provider selected=" + caption + ", id=" + value + ", has been saved");
		retval = e;
		return retval;
	}

	private TimeregistrationEvent handleProviderChange(TimeregistrationEvent event) {
		TimeregistrationEvent retval;
		TimeregistrationEventProviderUpdate e = (TimeregistrationEventProviderUpdate) event;
		String value = e.getValue();
		String caption = e.getCaption();

		TimeRegistrationSession trSession = getTimeregistrationSession();
		DataProvider dataProvider2 = getDataProvider();

		Provider p = dataProvider2.lookupProvider(trSession.getUser(), Integer.parseInt(value));
		trSession.setCurrentProvider(p);
		e.updateStatus("Provider selected=" + caption + ", id=" + value + ", has been set in session");

		retval = e;

		Client currentClient = trSession.getCurrentClient();
		if (currentClient != null) {
			this.registrationItemComponent.updateContent(getDataProvider());
		}
		return retval;
	}

	private TimeregistrationEvent handleClientChange(TimeregistrationEvent event) {
		TimeregistrationEvent retval;
		TimeregistrationEventClientUpdate e = (TimeregistrationEventClientUpdate) event;

		String value = e.getValue();
		String caption = e.getCaption();
		TimeRegistrationSession trSession = getTimeregistrationSession();
		DataProvider dataProvider2 = getDataProvider();

		Client lookupClient = dataProvider2.lookupClient(trSession.getUser(), Integer.parseInt(value));

		trSession.setCurrentClient(lookupClient);
		e.updateStatus("Client selected=" + caption + ", id=" + value + ", has been set in session");
		retval = e;

		if (trSession.getCurrentProvider() != null) {
			registrationItemComponent.updateContent(getDataProvider());
		} else {
			registrationItemComponent.clear();
		}
		return retval;
	}

	public StatusComponent getStatusComponent() {
		return statusComponent;
	}

	@Override
	public String getName() {
		return VIEW_NAME;
	}

}
