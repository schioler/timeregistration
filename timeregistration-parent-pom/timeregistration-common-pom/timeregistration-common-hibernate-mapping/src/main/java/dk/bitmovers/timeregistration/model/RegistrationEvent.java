package dk.bitmovers.timeregistration.model;

// Generated Jun 1, 2015 5:56:38 AM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RegistrationEvent generated by hbm2java
 */
@Entity
@Table(name = "registration_event", catalog = "timereg")
public class RegistrationEvent implements java.io.Serializable {

	private Integer id;
	private RegistrationItem registrationItem;
	private User user;
	private WorkClockEvent workClockEvent;
	private Date created;
	private Date eventTime;
	private String comments;

	public RegistrationEvent() {
	}

	public RegistrationEvent(RegistrationItem registrationItem, User user,
			WorkClockEvent workClockEvent, Date created, Date eventTime) {
		this.registrationItem = registrationItem;
		this.user = user;
		this.workClockEvent = workClockEvent;
		this.created = created;
		this.eventTime = eventTime;
	}

	public RegistrationEvent(RegistrationItem registrationItem, User user,
			WorkClockEvent workClockEvent, Date created, Date eventTime,
			String comments) {
		this.registrationItem = registrationItem;
		this.user = user;
		this.workClockEvent = workClockEvent;
		this.created = created;
		this.eventTime = eventTime;
		this.comments = comments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "registration_item_id", nullable = false)
	public RegistrationItem getRegistrationItem() {
		return this.registrationItem;
	}

	public void setRegistrationItem(RegistrationItem registrationItem) {
		this.registrationItem = registrationItem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "work_clock_event_id", nullable = false)
	public WorkClockEvent getWorkClockEvent() {
		return this.workClockEvent;
	}

	public void setWorkClockEvent(WorkClockEvent workClockEvent) {
		this.workClockEvent = workClockEvent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "event_time", nullable = false, length = 19)
	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	@Column(name = "comments", length = 220)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
