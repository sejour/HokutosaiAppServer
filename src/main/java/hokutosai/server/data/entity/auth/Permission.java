package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class Permission {

	@Column(name = "permission")
	@Getter @Setter
	private String permission;
	
	public Permission() { }
	
	public Permission(String permission) {
		this.permission = permission;
	}

}
