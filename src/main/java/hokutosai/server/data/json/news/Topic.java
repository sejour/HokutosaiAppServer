package hokutosai.server.data.json.news;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Topic {

	@JsonProperty("title")
	public String getTitle();

	@JsonProperty("media_url")
	public String getMediaUrl();

	@JsonProperty("news_id")
	public Integer getNewsId();

}
