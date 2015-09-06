package dk.bitmovers.timeregistration.client.gui.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.declarative.Design;

import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.StatusComponent;

@DesignRoot
@SpringView(name = PreferencesView.VIEW_NAME)
public class PreferencesView extends AbstractView<PreferencesView> {

	public static final String VIEW_NAME = "PreferencesView";

	private static final long serialVersionUID = 1L;



	protected Menu menu;

	private StatusComponent statusComponent;

	public PreferencesView() {
		super();
		Design.read(this);
	}

	@Override
	public StatusComponent getStatusComponent() {
		return statusComponent;
	}

	@Override
	public String getName() {
		return VIEW_NAME;
	}
	public Menu getMenu() {
		return menu;
	}
	
	@Override
	public void doEnter(ViewChangeEvent event) {
		
	}
}
