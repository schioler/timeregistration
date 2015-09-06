package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

import dk.bitmovers.timeregistration.client.gui.MenuManager.MenuInfo;
import dk.bitmovers.timeregistration.client.gui.util.VaadinUtil;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;

@Component
public class Menu extends CustomComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MessageSource messageSource;

	MenuBar menubar = new MenuBar();
	boolean isInit = false;

	Locale locale;

	public Menu() {
		super();

		logger.debug("MenuConstructor -------------------");
		setCompositionRoot(menubar);
	}

	public void fill(final Navigator navigator, final List<MenuInfo> items) {
		if (!isInit) {
			TimeRegistrationSession timeregistrationSession = VaadinUtil.getTimeregistrationSession();
			locale = timeregistrationSession.getUserLocale();
			addItems(navigator, null, items);
			isInit = true;
		}
	}

	@SuppressWarnings("serial")
	private MenuItem addItems(final Navigator navigator, MenuItem parentVaadinMenuItem, final List<MenuInfo> items) {
		for (final MenuInfo menuItemInfo : items) {

			MenuItem currentVaadinMenuItem = null;
			String label = messageSource.getMessage(menuItemInfo.labelKey, null, locale);

			if (parentVaadinMenuItem == null) {
				// Adding to menu
				if (menuItemInfo.navigateTo == null) {
					currentVaadinMenuItem = this.menubar.addItem(label, null, null);

				} else {
					currentVaadinMenuItem = this.menubar.addItem(label, new MenuBar.Command() {
						@Override
						public void menuSelected(MenuItem selectedItem) {
							navigator.navigateTo(menuItemInfo.navigateTo);
						}
					});

				}
			} else {

				if (menuItemInfo.navigateTo == null) {
					currentVaadinMenuItem = parentVaadinMenuItem.addItem(label, null, null);
				} else {
					currentVaadinMenuItem = parentVaadinMenuItem.addItem(label, new MenuBar.Command() {
						@Override
						public void menuSelected(MenuItem selectedItem) {
							navigator.navigateTo(menuItemInfo.navigateTo);
						}
					});

				}
			}
			currentVaadinMenuItem.setStyleName("timereg-menu-item");
			if (!menuItemInfo.children.isEmpty()) {
				addItems(navigator, currentVaadinMenuItem, menuItemInfo.children);
			}
		}
		return parentVaadinMenuItem;
	}

	@Override
	public void setStyleName(String style) {

		menubar.setStyleName(style);
//		menubar.setPrimaryStyleName(style);/
	}

	@Override
	public void addStyleName(String style) {
//		menubar.setPrimaryStyleName(style);
		menubar.addStyleName(style);
	}

}
