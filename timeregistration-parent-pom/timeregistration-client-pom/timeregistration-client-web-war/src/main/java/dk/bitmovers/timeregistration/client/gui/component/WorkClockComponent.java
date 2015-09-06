package dk.bitmovers.timeregistration.client.gui.component;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import com.vaadin.server.WrappedSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;

import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStart;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStop;
import dk.bitmovers.timeregistration.client.gui.view.TimeRegistrationSession;

@SpringComponent
public class WorkClockComponent extends AbstractTimeregistrationComponent implements ClickListener {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired()
	@Qualifier("messageSource")
	MessageSource messageSource;

	GridLayout layout = new GridLayout(4, 1);
	protected final Label lbl;

	private static String START = "workclock.start";
	private static String STOP = "workclock.stop";
	private static String STARTED = "workclock.started";
	private static String STOPPED = "workclock.stopped";
	private static String LABEL = "workclock.label";

	protected final Button buttonStop;
	protected final Button buttonStart;
	OptionGroup status = new OptionGroup();
	private Locale locale;

	public WorkClockComponent() {

		logger.debug("constructor,***** {}", this.messageSource);
		status.addItem(STARTED);

		status.addItem(STOPPED);

		this.buttonStart = new Button();
		this.buttonStop = new Button();
		this.buttonStart.addClickListener(this);
		this.buttonStart.setId(START);

		this.buttonStop.addClickListener(this);
		this.buttonStop.setId(STOP);
		this.lbl = new Label();

		//		status.setItemIcon(STARTED, new ThemeResource("img/50px-Bullet-green.png"));
		//		status.setItemIcon(STOPPED, new ThemeResource("img/50px-Bullet-red.png"));

		layout.addComponent(lbl);
		layout.addComponent(buttonStart);
		layout.addComponent(buttonStop);
		layout.addComponent(status);

		setCompositionRoot(layout);
	}

	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {
		logger.debug("messageSource=" + messageSource);
//		User user = trSession.getUser();
		locale = trSession.getUserLocale();
		buttonStart.setCaption(messageSource.getMessage(START, null, locale));
		buttonStop.setCaption(messageSource.getMessage(STOP, null, locale));
		status.setItemCaption(STARTED, messageSource.getMessage(STARTED, null, locale));
		status.setItemCaption(STOPPED, messageSource.getMessage(STOPPED, null, locale));

		//		lbl.setValue("DoIt");
		lbl.setValue(messageSource.getMessage(LABEL, null, locale));
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Object source = event.getSource();
		logger.debug("{}", event);
		if (source instanceof Button) {
			Button b = (Button) source;
			b.setComponentError(null);
			//			logger.debug("Event={}", event);
			//			Button button = event.getButton();
			//			logger.debug("button={}", button);
			TimeregistrationEvent te = null;
			if (b.getId().equals(this.buttonStart.getId())) {
				te = new TimeregistrationEventWorkClockStart(new Date());
				status.setValue("Started");
			} else if (b.getId().equals(this.buttonStop.getId())) {
				te = new TimeregistrationEventWorkClockStop(new Date());
				status.setValue("Stopped");
			}
			this.notify(te);

		}

	}

	@Override
	public void clear() {
		status.clear();

	}

}
