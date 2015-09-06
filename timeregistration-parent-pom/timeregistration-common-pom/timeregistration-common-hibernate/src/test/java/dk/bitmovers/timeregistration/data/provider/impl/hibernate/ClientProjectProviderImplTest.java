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
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.SearchCriteriaImpl;
import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring-ctx.xml" })
public class ClientProjectProviderImplTest extends AbstractHibernateTestBase {

	@Autowired
	ClientProvider clientProvider;

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

		List<ClientProject> queryClientProjects = clientProvider.queryClientProjects(sc);

		for (ClientProject clientProject : queryClientProjects) {
			System.out.println(clientProject.getName());

		}
		assertNotNull(queryClientProjects);
		assertEquals(2, queryClientProjects.size());

		this.endTransactionAndCloseSession(false);
		this.openSessionAndStartTransaction();
		sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));
		sc.getCriteria().put(SearchCriteria.CLIENT_ID, String.valueOf("100")); // Nordea

		queryClientProjects = clientProvider.queryClientProjects(sc);

		for (ClientProject clientProject : queryClientProjects) {
			System.out.println(clientProject.getName());

		}

		return true;
	}

}
