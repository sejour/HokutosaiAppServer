package hokutosai.server.data.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class SecureAccount {

	@Id
	@Column(name = "account_id")
	@JsonProperty("account_id")
	private String accountId;

	@Column(name = "user_name")
	@JsonProperty("name")
	private String name;

	@Column(name = "media_url")
	@JsonProperty("media_url")
	private String mediaUrl;

}
