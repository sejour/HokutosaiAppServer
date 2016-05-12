package hokutosai.server.data.entity.assessments;

import hokutosai.server.data.entity.account.SecureAccount;
import hokutosai.server.error.InvalidParameterValueException;
import hokutosai.server.security.ParamValidator;

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

	private static final int SCORE_MIN = 1;
	private static final int SCORE_MAX = 5;
	private static final int COMMENT_LENGTH_MAX = 140;

	@Id
	@Column(name = "id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("assessment_id")
	private Integer id;

	@Column(name = "account_id", nullable = false)
	@JsonIgnore
	private String accountId;

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

	public Assess(SecureAccount account, Date datetime, Integer score, String comment) throws InvalidParameterValueException {
		this.accountId = account.getAccountId();
		this.user = account;
		this.datetime = datetime;
		this.score = ParamValidator.range("score", score, SCORE_MIN, SCORE_MAX);
		this.comment = ParamValidator.text("comment", comment, COMMENT_LENGTH_MAX);
	}

}
