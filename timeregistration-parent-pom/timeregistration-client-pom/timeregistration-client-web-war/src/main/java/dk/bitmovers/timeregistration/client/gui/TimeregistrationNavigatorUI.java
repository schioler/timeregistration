package dk.bitmovers.timeregistration.client.gui;

import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@Theme("timereg")
@Title("Timeregistration")
@SpringUI
@SuppressWarnings("serial")
public class TimeregistrationNavigatorUI extends UI {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@WebServlet(urlPatterns = { "/gui/*", "/VAADIN/*" }, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TimeregistrationNavigatorUI.class)
	public static class Servlet extends SpringVaadinServlet {

	}

	@Autowired
	private SpringViewProvider viewProvider;

	@Autowired
	TimeregistrationConfiguration timeregistrationConfiguration;

	@Override
	protected void init(VaadinRequest request) {
		logger.debug("*******************************");
		Design.setComponentFactory(timeregistrationConfiguration.timeregistrationVaadinComponentFactory());

		final VerticalLayout root = new VerticalLayout();
		//		root.setSizeFull();
		root.setMargin(true);
		root.setSpacing(true);
		setContent(root);

		final Panel viewContainer = new Panel();
		//		viewContainer.setSizeFull();
		root.addComponent(viewContainer);
		root.setExpandRatio(viewContainer, 1.0f);
		viewContainer.addStyleName("timereg-viewContainer");

		Navigator navigator = new Navigator(this, viewContainer);

		navigator.addProvider(viewProvider);
		logger.debug("*******************************");
	}

}
