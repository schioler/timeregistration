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
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.User;

public class ClientProjectComponent extends AbstractTimeregistrationAbstractComponentComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ClientProjectComponent() {
		super(new GridLayout(2, 1), new Label("ClientProjects"), new OptionGroup());
	}

	
	
	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {
		User user = trSession.getUser();
		Client client = trSession.getCurrentClient();

		List<ClientProject> clientProjects = dataProvider.lookupClientProjects(user, client);

		OptionGroup clientsCmb = (OptionGroup) component;
		clientsCmb.clear();

		for (ClientProject c : clientProjects) {
			clientsCmb.setItemCaption(String.valueOf(c.getId()), c.getName());
			clientsCmb.addItem(String.valueOf(c.getId()));
		}

		ClientProject currentClientProject = trSession.getCurrentClientProject();

		if (currentClientProject != null) {
			clientsCmb.setCaption(currentClientProject.getName());
			clientsCmb.setValue(currentClientProject.getId());
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
