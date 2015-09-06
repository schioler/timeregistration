package dk.bitmovers.timeregistration.client.gui.util;

import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.OptionGroup;

import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.gui.view.ViewTokens;

public class VaadinUtil {

	
	private VaadinUtil() {

	}

	public static final OptionGroup createOptionGroup(String caption, List<String> options, Property.ValueChangeListener listener) {
		OptionGroup optionGroup = new OptionGroup(caption); 
		for (String string : options) {
			optionGroup.addItem(string);
		}
		
		optionGroup.addValueChangeListener(listener);
		return optionGroup;
	}
	public static TimeRegistrationSession getTimeregistrationSession() {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		return (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
	}
	
}
