package hokutosai.server.data.entity;

import hokutosai.server.data.entity.account.SecureAccount;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", insertable = false, updatable = false)
	@JsonProperty("user")
	private SecureAccount user;

	@Column(name = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("datetime")
	private Date datetime;

	@Column(name = "score", nullable = false)
	@JsonProperty("score")
	private Integer score;

	@Column(name = "comment", nullable = false, length = 140)
	@JsonProperty("comment")
	private String comment;

	public Assess() {}

	public Assess(String accountId, Date datetime, Integer score, String comment) {
		this.accountId = accountId;
		this.datetime = datetime;
		this.score = score;
		this.comment = comment;
	}

}
