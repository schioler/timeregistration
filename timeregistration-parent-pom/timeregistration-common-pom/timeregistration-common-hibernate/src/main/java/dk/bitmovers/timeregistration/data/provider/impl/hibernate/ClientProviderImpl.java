package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;

@Component("clientProvider")
public class ClientProviderImpl extends AbstractHibernateProviderBase implements ClientProvider {

	public ClientProviderImpl() {

	}

	@Override
	public Client persistClient(Client client) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public Client updateClient(Client client) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public int deleteClient(long clientId) throws TimeregistrationProviderException {

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Client retrieveClient(final long clientId) throws TimeregistrationProviderException {
		return (Client) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Client c = null;
				Query createQuery = session.createQuery("From Client where id = " + clientId);

				List<Client> list = createQuery.list();
				c = list.get(0);

				return c;

			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Client> queryClients(final SearchCriteria criteria) throws TimeregistrationProviderException {
		return (List<Client>) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Map<String, String> criteria2 = criteria.getCriteria();
				String string = criteria2.get(SearchCriteria.USER_ID);

				Query createQuery = session.createQuery("From Client where user.id = :uid");
				createQuery.setInteger("uid", Integer.parseInt(string));
				List<Client> list = createQuery.list();
				return list;
			}
		});

	}

	@Override
	public ClientProject persistClientProject(ClientProject clientProject) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public ClientProject updateClientProject(ClientProject clientProject) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public int deleteClientProject(long clientProjectId) throws TimeregistrationProviderException {

		return 0;
	}

	@Override
	public ClientProject retrieveClientProject(final long clientProjectId) throws TimeregistrationProviderException {
		return (ClientProject) this.execute(new ProviderExecutor() {

			@SuppressWarnings("unchecked")
			@Override
			public Object doIt(Session session) {
				ClientProject c = null;
				Query createQuery = session.createQuery("From ClientProject where id = :id");
				createQuery.setLong("id", clientProjectId);

				List<ClientProject> list = createQuery.list();
				c = list.get(0);

				return c;

			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ClientProject> queryClientProjects(final SearchCriteria criteria) throws TimeregistrationProviderException {
		return (List<ClientProject>) this.execute(new ProviderExecutor() {
			@Override
			public Object doIt(Session session) {
				String clientId = criteria.getCriteria().get(SearchCriteria.CLIENT_ID);
				String userId = criteria.getCriteria().get(SearchCriteria.USER_ID);
				Query query = session.createQuery("From ClientProject where user = :uid AND client = :clientId");
				query.setInteger("uid", Integer.parseInt(userId));
				query.setInteger("clientId", Integer.parseInt(clientId));

				List<ClientProject> list = query.list();

				return list;

			}
		});

	}
}
