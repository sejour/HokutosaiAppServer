package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "api_categories")
@Data
public class EndpointCategory {

	@Id
	@Column(name = "category")
	private String category;

	@Column(name = "permission")
	private String permission;

}
