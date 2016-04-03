package hokutosai.server.data.json.account;

import hokutosai.server.data.document.auth.AuthorizationTarget;
import hokutosai.server.data.entity.account.Account;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizedAccount extends AuthorizationTarget {

	@JsonProperty("user_name")
	@Getter
	private String name;

	@JsonProperty("media_url")
	@Getter
	private String mediaUrl;

	public AuthorizedAccount(Account account) {
		super(account.getAccountId(), account.getRole().getRole());
		this.name = account.getName();
		this.mediaUrl = account.getMediaUrl();
	}

}
