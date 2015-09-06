package dk.bitmovers.timeregistration.model;

// Generated Jun 1, 2015 5:56:38 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Client generated by hbm2java
 */
@Entity
@Table(name = "client", catalog = "timereg")
public class Client implements java.io.Serializable {

	private Integer id;
	private User user;
	private String name;
	private Date created;
	private Set<ClientProject> clientProjects = new HashSet<ClientProject>(0);

	public Client() {
	}

	public Client(User user, String name, Date created) {
		this.user = user;
		this.name = name;
		this.created = created;
	}

	public Client(User user, String name, Date created,
			Set<ClientProject> clientProjects) {
		this.user = user;
		this.name = name;
		this.created = created;
		this.clientProjects = clientProjects;
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

	@Column(name = "name", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	public Set<ClientProject> getClientProjects() {
		return this.clientProjects;
	}

	public void setClientProjects(Set<ClientProject> clientProjects) {
		this.clientProjects = clientProjects;
	}

}
