package dk.bitmovers.timeregistration.client.gui.view;

import java.io.Serializable;
import java.util.Locale;

import dk.bitmovers.timeregistration.client.gui.util.Util;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.ProviderAccount;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.WorkClockEvent;

public class TimeRegistrationSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private Client currentClient;
	//	private Integer currentClientId = null;

	private ClientProject currentClientProject;

	private Provider currentProvider;

	private ProviderAccount currentProviderAccount;

	private WorkClockEvent currentWorkClockEvent;

	private String lastRequest;

	private final User user;

	private final Locale userLocale;

//	private List<Client> clients;
//
//	private List<Provider> providers;
//
//	private List<WorkClockEventType> workClockEventTypes;

	//	public List<WorkClockEventType> getWorkClockEventTypes() {
	//		return workClockEventTypes;
	//	}
	//
	//	public void setWorkClockEventTypes(List<WorkClockEventType> workCleckEventTypes) {
	//		this.workClockEventTypes = workCleckEventTypes;
	//	}

	public TimeRegistrationSession(User ueer) {
		super();
		this.user = ueer;
		this.userLocale = Util.establishLocale(user.getLanguage(), user.getLocale());
	}

	
	//	public Integer getCurrentClientId() {
	//		if (currentClient != null)
	//			return currentClient.getId();
	//		else
	//			return null;
	//	}

	//	public void setCurrentClientId(Integer currentClientId) {
	//		this.currentClientId = currentClientId;
	//	}
	//
	//	public Integer getCurrentProviderId() {
	//		if (currentProvider != null)
	//			return currentProvider.getId();
	//		else
	//			return null;
	//
	//	}

	//	public void setCurrentProviderId(Integer currentProviderId) {
	//		this.currentProviderId = currentProviderId;
	//	}

	public Locale getUserLocale() {
		return userLocale;
	}


	public String getLastRequest() {
		return lastRequest;
	}

	public void setLastRequest(String lastRequest) {
		this.lastRequest = lastRequest;
	}

	public User getUser() {
		return user;
	}

	//	public List<Client> getClients() {
	//		return clients;
	//	}
	//
	//	public void setClients(List<Client> clients) {
	//		this.clients = clients;
	//	}

	//	public List<Provider> getProviders() {
	//		return providers;
	//	}
	//
	//	public void setProviders(List<Provider> providers) {
	//		this.providers = providers;
	//	}

	public WorkClockEvent getCurrentWorkClockEvent() {
		return currentWorkClockEvent;
	}

	public void setCurrentWorkClockEvent(WorkClockEvent currentWorkClockEvent) {
		this.currentWorkClockEvent = currentWorkClockEvent;
	}

	public Client getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}

	public ClientProject getCurrentClientProject() {
		return currentClientProject;
	}

	public void setCurrentClientProject(ClientProject currentClientProject) {
		this.currentClientProject = currentClientProject;
	}

	public Provider getCurrentProvider() {
		return currentProvider;
	}

	public void setCurrentProvider(Provider currentProvider) {
		this.currentProvider = currentProvider;
	}

	public ProviderAccount getCurrentProviderAccount() {
		return currentProviderAccount;
	}

	public void setCurrentProviderAccount(ProviderAccount currentProviderAccount) {
		this.currentProviderAccount = currentProviderAccount;
	}

//	@Override
//	public String toString() {
//		return "TimeRegistrationSession [currentClient=" + currentClient + ", currentProvider=" + currentProvider + ", currentWorkClockEvent="
//				+ currentWorkClockEvent + ", lastRequest=" + lastRequest + ", user=" + user + ", clients=" + clients + ", providers=" + providers
//				+ ", workClockEventTypes=" + workClockEventTypes + "]";
//	}

}
