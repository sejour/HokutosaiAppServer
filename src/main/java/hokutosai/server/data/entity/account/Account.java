package hokutosai.server.data.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

	@Column(name = "role")
	private String role;

	@Column(name = "permission")
	private String permission;

	@Column(name = "name")
	private String name;

	@Column(name = "media_url")
	private String mediaUrl;

}
