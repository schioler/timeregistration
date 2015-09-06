package dk.bitmovers.timeregistration.client.gui.data;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.client.gui.MenuManager;
import dk.bitmovers.timeregistration.client.gui.MenuManager.MenuInfo;
import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.data.provider.RegistrationProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.SearchCriteriaImpl;
import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.ProviderAccount;
import dk.bitmovers.timeregistration.model.RegistrationEvent;
import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

@Component("dataProvider")
public class DataProviderImpl implements DataProvider {

	protected Logger logger;
	@Autowired
	UserProvider userProvider;

	@Autowired
	ClientProvider clientProvider;

	@Autowired
	ProviderProvider providerProvider;

	@Autowired
	RegistrationProvider registrationProvider;

	@Autowired
	WorkClockEventProvider workClockEventProvider;

	public DataProviderImpl() {
		super();
		logger = LoggerFactory.getLogger(this.getClass());
	}

	public List<Client> lookupClients(User user) {
		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));

		List<Client> clients = clientProvider.queryClients(sc);
		logger.debug("clients={}", clients);
		return clients;
	}

	public List<ClientProject> lookupClientProjects(User user, Client client) {
		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));
		sc.getCriteria().put(SearchCriteria.CLIENT_ID, String.valueOf(client.getId()));

		Set<String> keySet = sc.getCriteria().keySet();
		for (String string : keySet) {
			System.out.println(string + "=" + sc.getCriteria().get(string));
		}

		List<ClientProject> clientProjects = clientProvider.queryClientProjects(sc);

		return clientProjects;
	}

	public List<Provider> lookupProviders(User user) {
		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));

		List<Provider> providers = providerProvider.queryProviders(sc);
		logger.debug("providers={}", providers);
		return providers;
	}

	@Override
	public List<MenuInfo> getMenuItems(User user) {
		return MenuManager.MENU_ITEMS;
	}

	public WorkClockEventProvider getWorkClockEventProvider() {
		return workClockEventProvider;
	}

	@Override
	public List<ProviderAccount> lookupProviderAccounts(User user, Provider provider) {

		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));
		sc.getCriteria().put(SearchCriteria.PROVIDER_ID, String.valueOf(provider.getId()));

		Set<String> keySet = sc.getCriteria().keySet();
		for (String string : keySet) {
			System.out.println(string + "=" + sc.getCriteria().get(string));
		}

		List<ProviderAccount> queryProviderAccounts = providerProvider.queryProviderAccounts(sc);

		return queryProviderAccounts;
	}

	@Override
	public Integer save(RegistrationItem item) {
		return registrationProvider.saveRegistrationItem(item);
	}

	@Override
	public ClientProject retrieveClientProject(Integer id) {
		return clientProvider.retrieveClientProject(id);

	}

	@Override
	public ProviderAccount retrieveProviderAccount(Integer id) {

		return providerProvider.retrieveProviderAccount(id);
	}

	@Override
	public List<RegistrationItem> lookupRegistrations(User u, Client cp, Provider pa) {
		return registrationProvider.getRegistrationItems(u, pa, cp);

	}

	@Override
	public Integer saveRegistrationEvent(RegistrationEvent event) {

		return registrationProvider.saveRegistrationEvent(event);
	}

	@Override
	public Provider lookupProvider(User user, Integer id) {
		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));
		sc.getCriteria().put(SearchCriteria.PROVIDER_ID, String.valueOf(id));
		List<Provider> queryClients = providerProvider.queryProviders(sc);

		return queryClients.get(0);
	}

	@Override
	public Client lookupClient(User user, Integer clientId) {
		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));
		sc.getCriteria().put(SearchCriteria.CLIENT_ID, String.valueOf(clientId));
		Client client = clientProvider.retrieveClient(clientId);

		return client;
	}

}
