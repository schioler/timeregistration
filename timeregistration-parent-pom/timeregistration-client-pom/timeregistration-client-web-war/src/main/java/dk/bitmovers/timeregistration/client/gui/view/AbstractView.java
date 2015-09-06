package dk.bitmovers.timeregistration.client.gui.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import dk.bitmovers.timeregistration.client.gui.MenuManager.MenuInfo;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.util.VaadinUtil;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.model.User;

public abstract class AbstractView<T> extends VerticalLayout implements TimeRegistrationView, TimeregistrationEventListener {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	@Autowired
	private DataProvider dataProvider;

	private boolean isInit = false;

	public TimeRegistrationSession getTimeregistrationSession() {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		return (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
	}

	public DataProvider getDataProvider() {
		return dataProvider;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String message = getName();
		try {
			if (!isInit) {
				User user = VaadinUtil.getTimeregistrationSession().getUser();

				List<MenuInfo> menuItems = dataProvider.getMenuItems(user);
				getMenu().fill(getUI().getNavigator(), menuItems);
				doEnter(event);
				isInit = true;
			}
			message = message + " succesfully loaded.";
		} catch (TimeregistrationException e) {
			message = message + e.getMessage();
		}
		Notification.show(message);
	}

	protected TimeregistrationEvent handleEventInView(TimeregistrationEvent event) {
		throw new TimeregistrationException("Abstract view impl called - not what you want....");

	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {
		return true;
	}

	@Override
	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
		TimeregistrationEvent e = handleEventInView(event);
		getStatusComponent().setStatus(e.getStatus());
		return "updated status";
	}

	public boolean isInit() {
		return isInit;
	}

}
