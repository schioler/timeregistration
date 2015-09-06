package dk.bitmovers.timeregistration.client.gui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.StatusComponent;

public interface TimeRegistrationView extends View {
	public String getName();

	public StatusComponent getStatusComponent();

	public Menu getMenu();

	public void doEnter(ViewChangeEvent event);
	
	public boolean isInit();
}
