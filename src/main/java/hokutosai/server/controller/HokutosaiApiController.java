package hokutosai.server.controller;

import hokutosai.server.data.json.system.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/system")
public class HokutosaiApiController {

	@Autowired
	private Config config;

	@RequestMapping("/config")
	public Config get() {
		return this.config;
	}

}
