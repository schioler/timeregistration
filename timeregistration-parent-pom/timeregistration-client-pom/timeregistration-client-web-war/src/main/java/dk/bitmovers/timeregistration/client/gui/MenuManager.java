package dk.bitmovers.timeregistration.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dk.bitmovers.timeregistration.client.gui.view.ClientView;
import dk.bitmovers.timeregistration.client.gui.view.IndexView;
import dk.bitmovers.timeregistration.client.gui.view.PreferencesView;
import dk.bitmovers.timeregistration.client.gui.view.ProviderView;
import dk.bitmovers.timeregistration.client.gui.view.RegistrationEventOverviewView;
import dk.bitmovers.timeregistration.client.gui.view.RegistrationItemOverviewView;
import dk.bitmovers.timeregistration.client.gui.view.ReportsView;

public class MenuManager {

	public static final MenuInfo INDEX = new MenuInfo("navigation.home", IndexView.VIEW_NAME);
	public static final MenuInfo PROVIDER = new MenuInfo("navigation.providers", ProviderView.VIEW_NAME);
	public static final MenuInfo CLIENTS = new MenuInfo("navigation.clients", ClientView.VIEW_NAME);
	public static final MenuInfo REGISTRATION_PARENT = new MenuInfo("navigation.registration.parent", null);
	public static final MenuInfo REGISTRATION_ITEM = new MenuInfo("navigation.registration.item", RegistrationItemOverviewView.VIEW_NAME);
	public static final MenuInfo REGISTRATION_EVENT = new MenuInfo("navigation.registration.event", RegistrationEventOverviewView.VIEW_NAME);
	public static final MenuInfo REPORTS = new MenuInfo("navigation.reports", ReportsView.VIEW_NAME);
	public static final MenuInfo PREFERENCES = new MenuInfo("navigation.preferences", PreferencesView.VIEW_NAME);
	public static final MenuInfo LOGOUT = new MenuInfo("navigation.logout", IndexView.VIEW_NAME);
	//	public static final ViewInfo DECLARATIVE = new ViewInfo("navigation.declarative", DeclarativeView.class.getSimpleName(), DeclarativeView.class);

	public static final List<MenuInfo> MENU_ITEMS;
	static {

		List<MenuInfo> tmp = new ArrayList<MenuInfo>();
		tmp.add(INDEX);
		tmp.add(PROVIDER);
		tmp.add(CLIENTS);
		REGISTRATION_PARENT.children.add(REGISTRATION_ITEM);
		REGISTRATION_PARENT.children.add(REGISTRATION_EVENT);
		tmp.add(REGISTRATION_PARENT);
		tmp.add(REPORTS);
		tmp.add(PREFERENCES);
		tmp.add(LOGOUT);
		MENU_ITEMS = Collections.unmodifiableList(tmp);
	}

	public static final class MenuInfo {
		public final String labelKey;
		public final String navigateTo;
		public final List<MenuInfo> children = new ArrayList<MenuInfo>();

		public MenuInfo(String labelKey, String navigateTo) {
			super();
			this.labelKey = labelKey;
			this.navigateTo = navigateTo;
			//			this.implementation = implementation;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((labelKey == null) ? 0 : labelKey.hashCode());
			result = prime * result + ((navigateTo == null) ? 0 : navigateTo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MenuInfo other = (MenuInfo) obj;
			if (labelKey == null) {
				if (other.labelKey != null)
					return false;
			} else if (!labelKey.equals(other.labelKey))
				return false;
			if (navigateTo == null) {
				if (other.navigateTo != null)
					return false;
			} else if (!navigateTo.equals(other.navigateTo))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ViewInfo [labelKey=" + labelKey + ", navigateTo=" + navigateTo + ", children=" + children + "]";
		}

	}

}
