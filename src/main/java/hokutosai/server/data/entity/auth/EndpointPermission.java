package hokutosai.server.data.entity.auth;

import hokutosai.server.data.entity.EndpointCategory;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "api_endpoints_permissions")
@IdClass(EndpointPermissionId.class)
public class EndpointPermission extends Permission {

	@Id
	@Getter @Setter
	private String path;

	@Id
	@Getter @Setter
	private String method;

	@Id
	@Getter @Setter
	private String role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", insertable = false, updatable = false)
	@Getter @Setter
	private EndpointCategory category;

}

