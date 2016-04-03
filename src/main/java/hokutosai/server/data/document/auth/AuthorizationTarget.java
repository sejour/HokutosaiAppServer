package hokutosai.server.data.document.auth;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class AuthorizationTarget {

	@Getter
	@Field("role")
	@JsonIgnore
	private String role;

	@Getter
	@Field("id")
	@JsonProperty("id")
	private String id;

	public AuthorizationTarget(String id) {
		this.role = "<<unknown>>";
		this.id = id;
	}

	public AuthorizationTarget(String id, String role) {
		this.role = role;
		this.id = id;
	}

}
