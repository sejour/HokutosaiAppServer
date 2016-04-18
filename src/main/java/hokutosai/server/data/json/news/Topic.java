package hokutosai.server.data.json.news;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Topic {
	
	@JsonProperty("title")
	public String getTitle();
	
	@JsonProperty("image_url")
	public String getImageUrl();
	
	@JsonProperty("link")
	public String getLink();
	
	@JsonProperty("event_id")
	public Integer getEventId();
	
	@JsonProperty("news_id")
	public Integer getNewsId();
	
}
