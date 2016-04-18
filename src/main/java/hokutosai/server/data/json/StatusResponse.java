package hokutosai.server.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class StatusResponse {

	@JsonProperty("status_code")
	@Getter
	private Integer code;
	
	public StatusResponse(Integer code) {
		
	}
	
}
