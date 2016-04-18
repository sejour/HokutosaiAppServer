package hokutosai.server.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediaConfiguration {

	@Value("${hokutosai.server.media.saveDirectory}")
	@Getter
	private String saveDirectory;

	@Value("${hokutosai.server.media.maxMediaSize}")
	@Getter
	private Long maxMediaSize;

	@Value("${hokutosai.server.media.minTotalResolution}")
	@Getter
	private Integer minTotalResolution;

	@Value("${hokutosai.server.media.extension}")
	@Getter
	private String[] mediaExtension;

	@Value("${hokutosai.server.media.url}")
	@Getter
	private String mediaUrl;

}
