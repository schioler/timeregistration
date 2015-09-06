package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.WrappedSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;

import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventSelectTimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

public class RegistrationItemComponent extends AbstractTimeregistrationAbstractComponentComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public RegistrationItemComponent() {
		super(new GridLayout(2, 1), new Label("Registration Items"), new OptionGroup());

	}

	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {
		User user = trSession.getUser();
		Provider currentProvider = trSession.getCurrentProvider();
		Client currentClient = trSession.getCurrentClient();

		List<RegistrationItem> registrationItems = dataProvider.lookupRegistrations(user, currentClient, currentProvider);

		OptionGroup regItemsOptionGroup = (OptionGroup) component;
		regItemsOptionGroup.clear();

		for (RegistrationItem registrationItem : registrationItems) {
			regItemsOptionGroup.addItem(registrationItem.getName());
		}

	}

	@Override
	protected TimeregistrationEvent getTimeregistrationEvent(String value, AbstractField<?> component) {
		OptionGroup cmb = (OptionGroup) component;
		String itemCaption = cmb.getItemCaption(value);
		return new TimeregistrationEventSelectTimeregistrationEvent(value, itemCaption);

	}

	@Override
	public void clear() {
		this.component.clear();

	}
}
