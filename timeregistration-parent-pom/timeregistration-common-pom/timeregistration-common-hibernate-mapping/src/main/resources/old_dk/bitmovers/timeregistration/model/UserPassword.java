package dk.bitmovers.timeregistration.model;

// Generated Jun 1, 2015 4:50:21 AM by Hibernate Tools 4.3.1

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
 * UserPassword generated by hbm2java
 */
@Entity
@Table(name = "user_password", catalog = "timereg")
public class UserPassword implements java.io.Serializable {

	private Integer id;
	private User user;
	private Date created;
	private String isActive;
	private Date ended;
	private String password;

	public UserPassword() {
	}

	public UserPassword(User user, Date created, String isActive,
			String password) {
		this.user = user;
		this.created = created;
		this.isActive = isActive;
		this.password = password;
	}

	public UserPassword(User user, Date created, String isActive, Date ended,
			String password) {
		this.user = user;
		this.created = created;
		this.isActive = isActive;
		this.ended = ended;
		this.password = password;
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
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "is_active", nullable = false, length = 2)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ended", length = 19)
	public Date getEnded() {
		return this.ended;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}

	@Column(name = "password", nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
