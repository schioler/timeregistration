package dk.bitmovers.timeregistration.client.gui.data;

import java.util.List;

import dk.bitmovers.timeregistration.client.gui.MenuManager.MenuInfo;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.ProviderAccount;
import dk.bitmovers.timeregistration.model.RegistrationEvent;
import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

public interface DataProvider {

	public List<Client> lookupClients(User user);

	public Client lookupClient(User user, Integer clientId);

	public List<ClientProject> lookupClientProjects(User user, Client client);

	public List<Provider> lookupProviders(User user);

	public List<ProviderAccount> lookupProviderAccounts(User user, Provider provider);

	public Provider lookupProvider(User user, Integer id);

	public List<MenuInfo> getMenuItems(User user);

	public WorkClockEventProvider getWorkClockEventProvider();

	public Integer save(RegistrationItem item);
	
	public Integer saveRegistrationEvent(RegistrationEvent event);

	public ClientProject retrieveClientProject(Integer id);

	public ProviderAccount retrieveProviderAccount(Integer id);
	
	public List<RegistrationItem> lookupRegistrations(User user , Client client, Provider provider);

}
