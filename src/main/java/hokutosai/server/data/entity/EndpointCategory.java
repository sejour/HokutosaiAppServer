package hokutosai.server.data.entity;

import hokutosai.server.data.entity.auth.Permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "api_categories")
public class EndpointCategory extends Permission {

	@Id
	@Column(name = "category")
	@Getter @Setter
	private String category;

}
