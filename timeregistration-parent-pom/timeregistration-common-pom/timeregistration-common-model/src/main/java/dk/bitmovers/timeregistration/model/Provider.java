package dk.bitmovers.timeregistration.model;

// Generated Jun 1, 2015 4:47:34 AM by Hibernate Tools 4.3.1

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
 * Provider generated by hbm2java
 */
@Entity
@Table(name = "provider", catalog = "timereg")
public class Provider implements java.io.Serializable {

	private Integer id;
	private User user;
	private String name;
	private Date created;
	private Set<ProviderAccount> providerAccounts = new HashSet<ProviderAccount>(
			0);

	public Provider() {
	}

	public Provider(User user, String name, Date created) {
		this.user = user;
		this.name = name;
		this.created = created;
	}

	public Provider(User user, String name, Date created,
			Set<ProviderAccount> providerAccounts) {
		this.user = user;
		this.name = name;
		this.created = created;
		this.providerAccounts = providerAccounts;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
	public Set<ProviderAccount> getProviderAccounts() {
		return this.providerAccounts;
	}

	public void setProviderAccounts(Set<ProviderAccount> providerAccounts) {
		this.providerAccounts = providerAccounts;
	}

}
