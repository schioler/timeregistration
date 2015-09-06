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
import javax.persistence.UniqueConstraint;

/**
 * ProviderAccount generated by hbm2java
 */
@Entity
@Table(name = "provider_account", catalog = "timereg", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class ProviderAccount implements java.io.Serializable {

	private Integer id;
	private Provider provider;
	private User user;
	private Date created;
	private String code;
	private String name;
	private String isActive;
	private Set<RegistrationItem> registrationItems = new HashSet<RegistrationItem>(
			0);

	public ProviderAccount() {
	}

	public ProviderAccount(Provider provider, User user, Date created,
			String code, String name, String isActive) {
		this.provider = provider;
		this.user = user;
		this.created = created;
		this.code = code;
		this.name = name;
		this.isActive = isActive;
	}

	public ProviderAccount(Provider provider, User user, Date created,
			String code, String name, String isActive,
			Set<RegistrationItem> registrationItems) {
		this.provider = provider;
		this.user = user;
		this.created = created;
		this.code = code;
		this.name = name;
		this.isActive = isActive;
		this.registrationItems = registrationItems;
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
	@JoinColumn(name = "provider_id", nullable = false)
	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
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

	@Column(name = "code", unique = true, nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "is_active", nullable = false, length = 2)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "providerAccount")
	public Set<RegistrationItem> getRegistrationItems() {
		return this.registrationItems;
	}

	public void setRegistrationItems(Set<RegistrationItem> registrationItems) {
		this.registrationItems = registrationItems;
	}

}
