package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import com.vaadin.server.WrappedSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Client;

public class ClientComponent extends AbstractTimeregistrationAbstractComponentComponent {

	private static final long serialVersionUID = 1L;

	public ClientComponent() {
		super(new GridLayout(2, 1), new Label("Client"), new ComboBox());
		this.component.addValueChangeListener(this);

	}

	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {

		List<Client> clients = dataProvider.lookupClients(trSession.getUser());

		logger.debug("fillClients: {}", clients);
		ComboBox clientsCmb = (ComboBox) component;

		int maxWidth = 0;
		for (Client c : clients) {
			logger.debug("adding client={}", c.getName());
			maxWidth = Math.max(maxWidth, c.getName().length());
			clientsCmb.setItemCaption(String.valueOf(c.getId()), c.getName());
			clientsCmb.addItem(String.valueOf(c.getId()));
		}
		clientsCmb.setTextInputAllowed(false);

		Integer currentClient = trSession.getCurrentClient() == null ? null : trSession.getCurrentClient().getId();

		if (currentClient != null) {
			clientsCmb.setValue(currentClient);
		}

	}

	@Override
	protected TimeregistrationEvent getTimeregistrationEvent(String value, AbstractField<?> component) {
		ComboBox cmb = (ComboBox) component;
		String itemCaption = cmb.getItemCaption(value);
		return new TimeregistrationEventClientUpdate(value, itemCaption);
	}

	@Override
	public void clear() {
		this.component.clear();
		
	}

}
