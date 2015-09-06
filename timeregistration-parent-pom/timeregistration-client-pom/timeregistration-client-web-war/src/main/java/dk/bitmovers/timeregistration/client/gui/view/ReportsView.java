package dk.bitmovers.timeregistration.client.gui.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.declarative.Design;

import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.StatusComponent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.common.TimeregistrationException;

@DesignRoot
@SpringView(name = ReportsView.VIEW_NAME)
public class ReportsView extends AbstractView<ReportsView> {
	public static final String VIEW_NAME = "ReportsView";

	private static final long serialVersionUID = 1L;

	private StatusComponent statusComponent;
	protected Menu menu;

	public ReportsView() {
		super();
		Design.read(this);
	}

	@Override
	public StatusComponent getStatusComponent() {
		return statusComponent;
	}

	@Override
	public void doEnter(ViewChangeEvent event) {
		Notification.show(getName());
	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {

		return false;
	}

	@Override
	public String getName() {

		return VIEW_NAME;
	}

	public Menu getMenu() {
		return menu;
	}
}
