package dk.bitmovers.timeregistration.client.gui.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design.DefaultComponentFactory;
import com.vaadin.ui.declarative.DesignContext;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationConfiguration;
import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.WorkClockComponent;

@SpringComponent
public class TimeregistrationVaadinComponentFactory extends DefaultComponentFactory {

	private static final long serialVersionUID = 1L;

	@Autowired
	TimeregistrationConfiguration timeregistrationConfiguration;

	public TimeregistrationVaadinComponentFactory() {

	}

	ThreadLocal<Menu> menuCache = new ThreadLocal<Menu>();
	ThreadLocal<WorkClockComponent> wcCache = new ThreadLocal<WorkClockComponent>();

	@Override
	public Component createComponent(String fullyQualifiedClassName, DesignContext context) {

		Class<? extends Component> componentClass = resolveComponentClass(fullyQualifiedClassName, context);
		if (componentClass.equals(WorkClockComponent.class)) {
			WorkClockComponent workClockComponent = wcCache.get();
			if (workClockComponent == null) {
				workClockComponent = timeregistrationConfiguration.workClockComponent();
				wcCache.set(workClockComponent);
			}
			return workClockComponent;
		} else if (componentClass.equals(Menu.class)) {
			Menu menu = menuCache.get();

			if (menu == null) {
				menu = timeregistrationConfiguration.menu();
				menuCache.set(menu);
			}
			return menu;
		} else {
			return super.createComponent(fullyQualifiedClassName, context);

		}

	}

}
