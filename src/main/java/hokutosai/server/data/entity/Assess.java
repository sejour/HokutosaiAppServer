package hokutosai.server.data.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@Data
public abstract class Assess {

	@Id
	@Column(name = "id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer id;

	@Column(name = "account_id", nullable = false)
	@JsonIgnore
	private String accountId;

	@Column(name = "score", nullable = false)
	@JsonProperty("score")
	private Integer score;

	public Assess() {}

	public Assess(String accountId, Integer score) {
		this.accountId = accountId;
		this.score = score;
	}

}
