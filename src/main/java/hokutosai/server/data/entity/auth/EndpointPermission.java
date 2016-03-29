package hokutosai.server.data.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "api_endpoints_permissions")
@IdClass(EndpointPermissionId.class)
@Data
public class EndpointPermission {

	@Id
	private String path;

	@Id
	private String method;

	@Id
	private String role;

	@Column(name = "permission")
	private String permission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", insertable = false, updatable = false)
	private EndpointCategory category;

}

