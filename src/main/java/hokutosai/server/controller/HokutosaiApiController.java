package hokutosai.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/api")
public class HokutosaiApiController {

	 @RequestMapping("/hello")
	 @ResponseBody
	 public String hello() {
	     return "Hello HokutosaiAPI";
	 }

}
