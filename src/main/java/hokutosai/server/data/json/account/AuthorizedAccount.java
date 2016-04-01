package hokutosai.server.data.json.account;

import hokutosai.server.data.entity.account.Account;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class AuthorizedAccount {

	@JsonProperty("user_id")
	private String accountId;

	@JsonProperty("user_name")
	private String name;

	@JsonProperty("media_url")
	private String mediaUrl;

	public AuthorizedAccount(Account account) {
		this.accountId = account.getAccountId();
		this.name = account.getName();
		this.mediaUrl = account.getMediaUrl();
	}

}
