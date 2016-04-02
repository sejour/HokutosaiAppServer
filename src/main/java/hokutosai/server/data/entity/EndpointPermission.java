package hokutosai.server.data.entity;

import hokutosai.server.data.entity.auth.Permission;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class EndpointPermission extends Permission {

	@Id
	@Column(name = "id")
	@Getter @Setter
	private Integer id;

	@Column(name = "path")
	@Getter @Setter
	private String path;

	@Column(name = "method")
	@Getter @Setter
	private String method;

}
