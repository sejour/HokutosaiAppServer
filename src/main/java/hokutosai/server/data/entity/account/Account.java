package hokutosai.server.data.entity.account;

import hokutosai.server.data.entity.auth.Permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
public class Account extends Permission {

	@Id
	@Column(name = "account_id")
	@Getter @Setter
	private String accountId;

	@Column(name = "password")
	@Getter @Setter
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role", insertable = false, updatable = false)
	@Getter @Setter
	private AccountRole role;

	@Column(name = "name")
	@Getter @Setter
	private String name;

	@Column(name = "media_url")
	@Getter @Setter
	private String mediaUrl;

}
