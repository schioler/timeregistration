package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vaadin.data.Item;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;

import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Provider;

@Component
public class StatusComponent extends CustomComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	GridLayout layout = new GridLayout(2, 4);

	protected Label clockLabel = new Label("workclock");
	protected Label providerLabel = new Label("provider");
	protected Label clientLabel = new Label("client");

	protected Label client;
	protected Label provider;

//	protected OptionGroup option = new OptionGroup();
	//	protected OptionGroup greenOption = new OptionGroup();

	protected final TextField eventField;

	public StatusComponent() {
//		Item startedItem = option.addItem("Started");
//		Item stoppedItem = option.addItem("Stopped");

		//		eventLabel = new Label("LastEvent:");
		eventField = new TextField();

		logger.debug("constructor, {}", this);
		eventField.setEnabled(false);

		setCompositionRoot(layout);

	}

	public void updateContent(TimeRegistrationSession trSession) {
		Provider currentProvider = trSession.getCurrentProvider();
		if (currentProvider != null) {
//			currentProvider.s
		}
	}

	public void setStatus(String status) {
		this.eventField.setValue(status);
	}

	public void setLastEventLines(List<String> lines) {
		String status = "";
		for (String string : lines) {
			status = status + string + ", ";
		}
		int length = status.length();
		eventField.setWidth(String.valueOf(length) + "rem");
		this.eventField.setValue(status);
	}

	public void setCurrentState(TimeRegistrationSession trSession) {

	}
}
