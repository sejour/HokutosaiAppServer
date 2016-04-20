package hokutosai.server.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class StatusResponse {

	@JsonProperty("status_code")
	@Getter
	private Integer code;
	
	public StatusResponse(Integer code) {
		this.code = code;
	}
	
	public StatusResponse(org.springframework.http.HttpStatus status) {
		this.code = status.value();
	}
	
	public StatusResponse(hokutosai.server.data.document.log.HttpStatus status) {
		this.code = status.getCode();
	}
	
}
