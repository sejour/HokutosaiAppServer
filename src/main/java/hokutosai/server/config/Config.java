package hokutosai.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Component
public class Config {

	@Value("${hokutosai.server.name}")
	@JsonProperty("name")
	@Getter
	private String name;

	@Value("${hokutosai.server.target}")
	@JsonProperty("target")
	@Getter
	private Integer targetYear;

	@Value("${hokutosai.server.version}")
	@JsonProperty("version")
	@Getter
	private String version;

	@Value("${spring.jackson.dateFormat}")
	@JsonProperty("datetime_format")
	@Getter
	private String datetimeFormat;

	@Value("${spring.jackson.time-zone}")
	@JsonProperty("time_zone")
	@Getter
	private String timeZone;

}
