package hokutosai.server.config;

import hokutosai.server.security.ApiUserAuthenticationFilter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FilterConfiguration extends WebMvcConfigurerAdapter {

	@Bean
    public FilterRegistrationBean apiUserAuthenticationFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new ApiUserAuthenticationFilter());
        bean.setOrder(1);
        return bean;
    }

}
