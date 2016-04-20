package hokutosai.server.data.entity.auth;

import hokutosai.server.data.type.PermissionEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class Permission {

	@Column(name = "permission") @Enumerated(EnumType.STRING)
	@JsonIgnore
	@Getter @Setter
	private PermissionEnum permission;

	public Permission() { }

	public Permission(PermissionEnum permission) {
		this.permission = permission;
	}

}
