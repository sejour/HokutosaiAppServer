package hokutosai.server.data.entity.news;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@Data
public class News {

	@Id
	@Column(name = "news_id") @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("news_id")
	private Integer newsId;

	@Column(name = "title", nullable = false)
	@JsonProperty("title")
	@NotBlank
	private String title;

	@Column(name = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("datetime")
	private Date datetime;

	@Column(name = "topic", nullable = false)
	@JsonProperty("topic")
	@NotNull
	private Boolean isTopic;

	@Column(name = "tag")
	@JsonProperty("tag")
	private String tag;

	@Column(name = "text", nullable = false)
	@JsonProperty("text")
	@NotBlank
	private String text;

}
