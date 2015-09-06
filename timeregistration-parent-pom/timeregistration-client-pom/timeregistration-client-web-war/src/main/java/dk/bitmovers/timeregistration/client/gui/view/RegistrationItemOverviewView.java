package dk.bitmovers.timeregistration.client.gui.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.declarative.Design;

import dk.bitmovers.timeregistration.client.gui.component.ClientComponent;
import dk.bitmovers.timeregistration.client.gui.component.ClientProjectComponent;
import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.ProviderAccountComponent;
import dk.bitmovers.timeregistration.client.gui.component.ProviderComponent;
import dk.bitmovers.timeregistration.client.gui.component.StatusComponent;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventProviderUpdate;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;

@Title("RegistrationItemOverviewView")
@DesignRoot
@SpringView(name = RegistrationItemOverviewView.VIEW_NAME)
public class RegistrationItemOverviewView extends AbstractView<RegistrationItemOverviewView> {

	public static final String VIEW_NAME = "RegistrationItemOverviewView";

	ClientComponent clientComponent; //= new ClientComponent(TimeregStyle.TIMEREG_COMPONENT);
	ClientProjectComponent clientProjectComponent;// = new ClientProjectComponent(TimeregStyle.TIMEREG_COMPONENT);
	//
	ProviderComponent providerComponent; //= new ProviderComponent(TimeregStyle.TIMEREG_COMPONENT);
	ProviderAccountComponent providerAccountComponent;// = new ProviderAccountComponent(TimeregStyle.TIMEREG_COMPONENT);

	Button buttonSaveRegistrationItem;

	private static final long serialVersionUID = 1L;

	private Menu menu;
	private StatusComponent statusComponent;

	@Override
	public StatusComponent getStatusComponent() {
		return statusComponent;
	}

	//	public RegistrationView(TimeregistrationNavigatorUI timeregistrationNavigatorUI, ViewInfo viewInfo, DataProvider dataProvider) {
	//		super(timeregistrationNavigatorUI, viewInfo, dataProvider);
	//		clientComponent.addTimeregistrationEventListener(this);
	//		providerComponent.addTimeregistrationEventListener(this);
	//		//		clientProjectComponent.addTimeregistrationEventListener(this);
	//
	//		buttonSaveRegistrationItem.setCaption("Dut");
	//		buttonSaveRegistrationItem.addClickListener(new Button.ClickListener() {
	//
	//			@Override
	//			public void buttonClick(ClickEvent event) {
	//				String value = clientProjectComponent.getValue();
	//				String value2 = providerAccountComponent.getValue();
	//				
	//				DataProvider dataProvider2 = getDataProvider();
	//				ClientProject retrieveClientProject = dataProvider2.retrieveClientProject(Integer.parseInt(value));
	//				ProviderAccount retrieveProviderAccount = dataProvider2.retrieveProviderAccount(Integer.parseInt(value2));
	//
	//				TimeRegistrationSession timeregistrationSession = getTimeregistrationSession();
	//				User user = timeregistrationSession.getUser();
	//
	//				RegistrationItem ri = new RegistrationItem();
	//
	//				ri.setUser(user);
	//				ri.setClientProject(retrieveClientProject);
	//				ri.setProviderAccount(retrieveProviderAccount);
	//				ri.setCreated(new Date());
	//				ri.setEnded(null);
	//				ri.setIsActive("Y");
	//				ri.setName(retrieveClientProject.getName() + "(" + retrieveClientProject.getCode() + ")" + ", " + retrieveProviderAccount.getName());
	//				dataProvider2.save(ri);
	//
	//				logger.debug("ÅÅÅÅÅÅÅÅÅÅ)value={}", value);
	//				Notification.show("clientProject=" + value + ", providerAccount=" + value2);
	//			}
	//		});
	//	}

	public RegistrationItemOverviewView() {
		super();
		Design.read(this);

	}

	@Override
	public void doEnter(ViewChangeEvent event) {
		Notification.show(getName());
		initUserSession();
	}

	boolean isInit = false;

	protected void initUserSession() {
		logger.debug("init. isInit={}", isInit);
		if (!isInit) {

			clientComponent.updateContent(getDataProvider());
			providerComponent.updateContent(getDataProvider());
		
			isInit = true;
		}
	}

	protected TimeregistrationEvent handleEventInView(TimeregistrationEvent event) {
		TimeregistrationEvent retval = null;
		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		DataProvider dataProvider2 = getDataProvider();
		if (event instanceof TimeregistrationEventClientUpdate) {
			TimeregistrationEventClientUpdate e = (TimeregistrationEventClientUpdate) event;
			String value = e.getValue();
			String caption = e.getCaption();
			logger.debug("TimeregistrationView: value={}, caption={}", value, caption);
			Client lookupClient = dataProvider2.lookupClient(trSession.getUser(), Integer.parseInt(value));

			trSession.setCurrentClient(lookupClient);

			clientProjectComponent.updateContent(getDataProvider());
			e.updateStatus("Client selected=" + caption + ", id=" + value + ", has been set in session");
			retval = e;

		} else if (event instanceof TimeregistrationEventProviderUpdate) {
			TimeregistrationEventProviderUpdate e = (TimeregistrationEventProviderUpdate) event;
			String value = e.getValue();
			String caption = e.getCaption();

			Provider p = dataProvider2.lookupProvider(trSession.getUser(), Integer.parseInt(value));
			trSession.setCurrentProvider(p);

			providerAccountComponent.updateContent(dataProvider2);
			e.updateStatus("Provider selected=" + caption + ", id=" + value + ", has been set in session");
			retval = e;
		}

		//		TimeregistrationEvent retval = null;
		//		WrappedSession session = VaadinSession.getCurrent().getSession();
		//		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		//
		//		if (event instanceof TimeregistrationEventClientUpdate) {
		//			TimeregistrationEventClientUpdate e = (TimeregistrationEventClientUpdate) event;
		//			String value = e.getValue();
		//			String caption = e.getCaption();
		//
		//			Client currentClient = null;
		//			List<Client> clients2 = trSession.getClients();
		//			for (Client client : clients2) {
		//				if (String.valueOf(client.getId()).equals(value)) {
		//					currentClient = client;
		//					break;
		//				}
		//			}
		//
		//			trSession.setCurrentClientId(currentClient.getId());
		//			e.updateStatus("Client selected=" + caption + ", id=" + value + ", has been set in session");
		//			retval = e;
		//
		//		} else if (event instanceof TimeregistrationEventProviderUpdate) {
		//			TimeregistrationEventProviderUpdate e = (TimeregistrationEventProviderUpdate) event;
		//			String value = e.getValue();
		//			String caption = e.getCaption();
		//			// TODO fix lookup from db..
		//			Provider currentClient = null;
		//			List<Provider> clients2 = trSession.getProviders();
		//			for (Provider client : clients2) {
		//				if (String.valueOf(client.getId()).equals(value)) {
		//					currentClient = client;
		//					break;
		//				}
		//			}
		//
		//			trSession.setCurrentProviderId(currentClient.getId());
		//			e.updateStatus("Provider selected=" + caption + ", id=" + value + ", has been set in session");
		//			retval = e;
		//
		//		}

		return retval;

	}

	public Menu getMenu() {
		return menu;
	}

	@Override
	public String getName() {

		return VIEW_NAME;
	}

}
