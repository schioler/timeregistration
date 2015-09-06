package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.WrappedSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventProviderUpdate;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Provider;

public class ProviderComponent extends AbstractTimeregistrationAbstractComponentComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ProviderComponent() {
		super(new GridLayout(2, 1), new Label("Provider"), new ComboBox());
		this.component.addValueChangeListener(this);
	}

	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {

		ComboBox providerCmb = (ComboBox) component;
		List<Provider> providers = dataProvider.lookupProviders(trSession.getUser());
		logger.debug("updateContent: {}", providers);
		for (Provider c : providers) {

			logger.debug("adding provider={}", c.getName());
			providerCmb.addItem(String.valueOf(c.getId()));
			providerCmb.setItemCaption(String.valueOf(c.getId()), c.getName());
		}

		Integer currentProviderId = trSession.getCurrentProvider() == null ? null : trSession.getCurrentProvider().getId();
		if (currentProviderId != null)
			providerCmb.setValue(currentProviderId);

		providerCmb.setTextInputAllowed(false);

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

}
