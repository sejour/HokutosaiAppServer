package hokutosai.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Embeddable
@Data
public class EndpointId implements Serializable {

	@Id
	@Column(name = "path")
	private String path;

	@Id
	@Column(name = "method")
	private String method;

}
