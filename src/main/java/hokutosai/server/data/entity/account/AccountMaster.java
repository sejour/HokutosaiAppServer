package hokutosai.server.data.entity.account;

import hokutosai.server.data.entity.auth.Permission;
import hokutosai.server.data.type.PermissionEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
public class AccountMaster extends Permission {

	@Id
	@Column(name = "account_id")
	@JsonProperty("account_id")
	@Getter @Setter
	private String accountId;

	@Column(name = "password")
	@JsonProperty("account_pass")
	@Getter @Setter
	private String password;

	@Column(name = "role")
	@JsonIgnore
	@Getter @Setter
	private String roleName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role", insertable = false, updatable = false)
	@JsonIgnore
	@Getter @Setter
	private AccountRole role;

	@Column(name = "name")
	@JsonProperty("user_name")
	@Getter @Setter
	private String name;

	@Column(name = "media_url")
	@JsonProperty("media_url")
	@Getter @Setter
	private String mediaUrl;

	public AccountMaster() { }

	public AccountMaster(String accountId, String password, String roleName, PermissionEnum permission) {
		super(permission);
		this.accountId = accountId;
		this.password = password;
		this.roleName = roleName;
	}

}
