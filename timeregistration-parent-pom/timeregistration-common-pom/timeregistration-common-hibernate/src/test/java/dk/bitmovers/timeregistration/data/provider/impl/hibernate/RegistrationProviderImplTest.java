package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.data.provider.RegistrationProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.SearchCriteriaImpl;
import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring-ctx.xml" })
public class RegistrationProviderImplTest extends AbstractHibernateTestBase {

	@Autowired
	RegistrationProvider registrationProvider;

	@Autowired
	UserProvider userProvider;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	public boolean doTest() {

		User user = userProvider.retrieveUserByName("lasc");

		this.endTransactionAndCloseSession(false);
		this.openSessionAndStartTransaction();
		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));
		sc.getCriteria().put(SearchCriteria.CLIENT_ID, String.valueOf("101")); // Nordea
		sc.getCriteria().put(SearchCriteria.PROVIDER_ID, String.valueOf("100")); // Acn

		List<RegistrationItem> registrationItems = registrationProvider.getRegistrationItems(user);

		for (RegistrationItem registrationItem : registrationItems) {
			System.out.println(registrationItem.getName());
		}
//		assertNotNull(queryClientProjects);
//		assertEquals(2, queryClientProjects.size());

		
		return true;
	}

}
