package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dk.bitmovers.timeregistration.data.provider.RegistrationProvider;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.RegistrationEvent;
import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

@Component("registrationProvider")
public class RegistrationItemProviderImpl extends AbstractHibernateProviderBase implements RegistrationProvider {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<RegistrationItem> getRegistrationItems(final User user) {
		return (List<RegistrationItem>) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				//				Query createQuery = session.createQuery("From RegistrationItem as RI join RI.user U where U.id= :userId");
				//				createQuery.setInteger("userId", user.getId());
				//				return createQuery.list();
				//				
				Criteria criteria = session.createCriteria(RegistrationItem.class);
				criteria.createCriteria("user").add(Restrictions.eq("id", user.getId()));
				criteria.createCriteria("providerAccount").add(Restrictions.eq("provider.id", 100));
				criteria.createCriteria("clientProject").add(Restrictions.eq("client.id", 100));
				List list = criteria.list();
				logger.debug("****** list=" + list);
				for (Object object : list) {
					logger.debug("*************" + object);
				}
				return list;
			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RegistrationItem> getRegistrationItems(final User user, final Provider provider, final Client client)
			throws TimeregistrationProviderException {
		return (List<RegistrationItem>) this.execute(new ProviderExecutor() {
			@Override
			public Object doIt(Session session) {

				Criteria criteria = session.createCriteria(RegistrationItem.class);
				//				criteria.createCriteria("user").add(Restrictions.eq("userId", user.getId()));
				//				Query createQuery = session
				//						.createQuery("From RegistrationItem as RI join RI.providerAccount PA join PA.provider join RI.clientProject CP join CP.client join RI.user U where U.id= :userId and CP.client_id = :cp and PA.providerid=:pa ");
				//				createQuery.setInteger("userId", user.getId());
				//				createQuery.setInteger("cp", client.getId());
				//				createQuery.setInteger("pa", provider.getId());
				//				return createQuery.list();
				List list = criteria.list();
				logger.debug("****** list=" + list);
				for (Object object : list) {
					logger.debug("*************" + object);
				}
				return list;
			}
		});
		//		return null;s

	}

	@Override
	public Integer saveRegistrationItem(final RegistrationItem item) throws TimeregistrationProviderException {
		return (Integer) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Serializable save = session.save(item);
				return save;
			}
		});
	}

	@Override
	public Integer deleteRegistrationItem(final RegistrationItem item) throws TimeregistrationProviderException {
		return (Integer) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				session.delete(item);
				return 1;
			}
		});
	}

	@Override
	public Integer updateRegistrationItem(final RegistrationItem item) throws TimeregistrationProviderException {
		return (Integer) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				session.update(item);
				return 1;
			}
		});
	}

	@Override
	public Integer saveRegistrationEvent(final RegistrationEvent event) throws TimeregistrationProviderException {
		return (Integer) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Serializable save = session.save(event);
				return save;
			}
		});
	}

}
