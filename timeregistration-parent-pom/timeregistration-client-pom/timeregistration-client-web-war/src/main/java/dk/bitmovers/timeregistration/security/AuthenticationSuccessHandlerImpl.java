package dk.bitmovers.timeregistration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import dk.bitmovers.timeregistration.client.gui.util.GlobalDataCache;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.gui.view.ViewTokens;
import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.model.User;

public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	GlobalDataCache globalData;

	@Autowired
	ClientProvider clientProvider;

	@Autowired
	ProviderProvider providerProvider;

	public AuthenticationSuccessHandlerImpl(String defaultTargetUrl) {
		super(defaultTargetUrl);
	}

	public AuthenticationSuccessHandlerImpl() {

	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException,
			ServletException {

		UserDetailsImpl ud = (UserDetailsImpl) auth.getPrincipal();
		User user = ud.getUser();
		TimeRegistrationSession ses = new TimeRegistrationSession(user);

		req.getSession().setAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION, ses);
		logger.debug("timeregSession={}", ses);
		super.onAuthenticationSuccess(req, resp, auth);
	}
}
