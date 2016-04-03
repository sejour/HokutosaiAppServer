package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@Data
public class Assess {

	@Id
	@Column(name = "id", nullable = false)
	@JsonIgnore
	private Integer id;

	@Column(name = "account_id", nullable = false)
	@JsonIgnore
	private String accountId;

	@Column(name = "score", nullable = false)
	@JsonProperty("score")
	private String score;

}
