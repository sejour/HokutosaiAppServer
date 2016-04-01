package hokutosai.server.data.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {

	@Id
	@Column(name = "account_id")
	private String accountId;

	@Column(name = "password")
	private String password;

	@Column(name = "permission")
	private String permission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role", insertable = false, updatable = false)
	private AccountRole role;

	@Column(name = "name")
	private String name;

	@Column(name = "media_url")
	private String mediaUrl;

}
