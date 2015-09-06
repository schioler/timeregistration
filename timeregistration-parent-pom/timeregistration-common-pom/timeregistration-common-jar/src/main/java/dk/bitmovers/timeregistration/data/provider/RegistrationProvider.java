package dk.bitmovers.timeregistration.data.provider;

import java.util.List;

import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.RegistrationEvent;
import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

public interface RegistrationProvider {
	public List<RegistrationItem> getRegistrationItems(User user) throws TimeregistrationProviderException;

	public List<RegistrationItem> getRegistrationItems(User user, Provider provider, Client client)
			throws TimeregistrationProviderException;

	public Integer saveRegistrationItem(RegistrationItem item) throws TimeregistrationProviderException;

	public Integer deleteRegistrationItem(RegistrationItem item) throws TimeregistrationProviderException;

	public Integer updateRegistrationItem(RegistrationItem item) throws TimeregistrationProviderException;

	public Integer saveRegistrationEvent(RegistrationEvent item) throws TimeregistrationProviderException;
}
