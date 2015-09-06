package dk.bitmovers.timeregistration.client.gui.event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractTimeregistrationEventWorkClock extends TimeregistrationEvent {

	private static final long serialVersionUID = 1L;

	private final Date time;

	public AbstractTimeregistrationEventWorkClock(Date time) {
		super(new SimpleDateFormat().format(time), "");
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

}
