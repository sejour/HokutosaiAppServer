package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class Permission {

	@Column(name = "permission")
	@JsonIgnore
	@Getter @Setter
	private String permission;

	public Permission() { }

	public Permission(String permission) {
		this.permission = permission;
	}

}
