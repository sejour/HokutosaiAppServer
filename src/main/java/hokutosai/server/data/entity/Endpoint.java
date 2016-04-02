package hokutosai.server.data.entity;

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
@Table(name = "api_endpoints")
@IdClass(EndpointId.class)
@Data
public class Endpoint {

	@Id
	private String path;

	@Id
	private String method;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", insertable = false, updatable = false)
	private EndpointCategory category;

	@Column(name = "account_need")
	private String accountNeed;

}
