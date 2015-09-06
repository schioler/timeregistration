package dk.bitmovers.timeregistration.client.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vaadin.spring.annotation.EnableVaadin;

import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.WorkClockComponent;
import dk.bitmovers.timeregistration.client.gui.util.TimeregistrationVaadinComponentFactory;

@EnableVaadin
@Configuration
public class TimeregistrationConfiguration {

	@Autowired
	ApplicationContext applicationContext;

	//	@Autowired
	//	MessageSource messageSource;

	public TimeregistrationConfiguration() {

	}

	@Bean
	public Menu menu() {

		return applicationContext.getBean(Menu.class);
	}

	@Bean
	public WorkClockComponent workClockComponent() {

		return new WorkClockComponent();
	}

	@Bean
	public TimeregistrationVaadinComponentFactory timeregistrationVaadinComponentFactory() {
		TimeregistrationVaadinComponentFactory componentFactory = new TimeregistrationVaadinComponentFactory();
		return componentFactory;
	}

}
