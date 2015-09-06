package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.WrappedSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;

import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventProviderUpdate;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.ProviderAccount;
import dk.bitmovers.timeregistration.model.User;

public class ProviderAccountComponent extends AbstractTimeregistrationAbstractComponentComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ProviderAccountComponent() {
		super(new GridLayout(2, 1), new Label("ProviderAccounts"), new OptionGroup());

	}

	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {
		User user = trSession.getUser();
		Provider provider = trSession.getCurrentProvider();

		List<ProviderAccount> providerAccounts = dataProvider.lookupProviderAccounts(user, provider);

		OptionGroup clientsCmb = (OptionGroup) component;
		clientsCmb.clear();

		for (ProviderAccount p : providerAccounts) {

			clientsCmb.setItemCaption(String.valueOf(p.getId()), p.getName());
			clientsCmb.addItem(String.valueOf(p.getId()));
		}
		//		clientsCmb.setTextInputAllowed(false);

		ProviderAccount pa = trSession.getCurrentProviderAccount();

		if (pa != null) {
			clientsCmb.setCaption(pa.getName());
			clientsCmb.setValue(pa.getId());
		}
	}

	@Override
	protected TimeregistrationEvent getTimeregistrationEvent(String value, AbstractField<?> component) {
		ComboBox cmb = (ComboBox) component;
		String itemCaption = cmb.getItemCaption(value);
		return new TimeregistrationEventProviderUpdate(value, itemCaption);

	}

	@Override
	public void clear() {
		this.component.clear();

	}
	// @Override
	// protected void update(TimeRegistrationSession trSession) {
	// // clients.clear();
	// List<Client> clients = trSession.getClients();
	// logger.debug("fillClients: {}", clients);
	// ComboBox clientsCmb = (ComboBox) component;
	//
	// for (Client c : clients) {
	// logger.debug("adding client={}", c.getName());
	// clientsCmb.addItem(c.getName());
	// }
	// clientsCmb.setTextInputAllowed(false);
	//
	// // lbl.setCaption("Client: ");
	//
	// Client currentClient = trSession.getCurrentClient();
	// // String curClient = "n/a"
	// if (currentClient != null) {
	//
	// clientsCmb.setValue(currentClient.getName());
	// }
	// // status.setCaption("Current selected client: " + curClient);
	//
	// }

	// @Override
	// protected ClickListener getClickListener() {
	//
	// ClickListener cl = new ClickListener() {
	//
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public void buttonClick(ClickEvent event) {
	// ComboBox clientsCmb = (ComboBox) component;
	// String value = (String) clientsCmb.getValue();
	// logger.debug("Value=" + value);
	//
	// if (value != null) {
	// TimeregistrationEvent te = new TimeregistrationEventClientUpdate(value);
	// timeregistrationEventListener.handleEvent(te);
	// } else {
	// logger.info("Skipping event, since value = null");
	// }
	//
	// }
	// };
	//
	// return cl;
	// }

}
