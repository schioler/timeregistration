package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.UserPassword;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring-ctx.xml" })
public class UserProviderImplRetrieveUserByLoginTest extends AbstractHibernateTestBase {

	@Autowired
	UserProvider userProvider;

	public boolean doTest() {
		logger.debug("testRetrieveUserByName");
		logger.debug("" + userProvider);

		User user = userProvider.retrieveUserByName("lasc");
		logger.debug("user={}", user);
		assertNotNull(user);
		assertEquals("lasc", user.getLogin());
		Set<UserPassword> userPasswords = user.getUserPasswords();
		logger.debug("userPasswords={}", userPasswords);
		assertNotNull(userPasswords);
		assertEquals(1, userPasswords.size());
		return true;
	}


}
