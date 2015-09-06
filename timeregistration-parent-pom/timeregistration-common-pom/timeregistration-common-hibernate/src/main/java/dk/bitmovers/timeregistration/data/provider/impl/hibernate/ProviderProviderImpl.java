package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.data.provider.impl.hibernate.AbstractHibernateProviderBase.ProviderExecutor;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.ProviderAccount;

@Component("providerProvider")
public class ProviderProviderImpl extends AbstractHibernateProviderBase implements ProviderProvider {

	public ProviderProviderImpl() {

	}

	@Override
	public Provider persistProvider(Provider provider) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider updateClient(Provider provider) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProvider(long id) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Provider retrieveProvider(long id) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Provider> queryProviders(final SearchCriteria criteria) throws TimeregistrationProviderException {
		return (List<Provider>) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				final String string = criteria.getCriteria().get(SearchCriteria.USER_ID);
				Query query = session.createQuery("from Provider where user_id = :login_id");

				query.setParameter("login_id", string);
				List<Provider> list = query.list();

				return list;
			}
		});

	}

	@Override
	public ProviderAccount persistProviderAccount(ProviderAccount clientProject) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProviderAccount updateProviderAccount(ProviderAccount clientProject) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public int deleteProviderAccount(long clientProjectId) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProviderAccount retrieveProviderAccount(final long id) throws TimeregistrationProviderException {
		return (ProviderAccount) this.execute(new ProviderExecutor() {

			@SuppressWarnings("unchecked")
			@Override
			public Object doIt(Session session) {
				ProviderAccount c = null;
				Query createQuery = session.createQuery("From ProviderAccount where id = :id");
				createQuery.setLong("id", id);

				List<ProviderAccount> list = createQuery.list();
				c = list.get(0);

				return c;

			}
		});
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ProviderAccount> queryProviderAccounts(final SearchCriteria searcCriteria) throws TimeregistrationProviderException {
		return (List<ProviderAccount>) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Map<String, String> criteria = searcCriteria.getCriteria();

				String providerId = criteria.get(SearchCriteria.PROVIDER_ID);
				String userId = criteria.get(SearchCriteria.USER_ID);
				Query query = session.createQuery("From ProviderAccount where user = :uid AND provider = :providerId");
				query.setInteger("uid", Integer.parseInt(userId));
				query.setInteger("providerId", Integer.parseInt(providerId));

				List<ProviderAccount> providerAccounts = query.list();

				return providerAccounts;
			}
		});

	}

}
