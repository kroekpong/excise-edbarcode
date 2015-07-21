package com.baiwa.framework.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(
	basePackages = {
		"th.go.excise.edbarcode"
	},
	includeFilters = {
		@ComponentScan.Filter(
			type = FilterType.REGEX,
			pattern = ".*.service"
		),
		@ComponentScan.Filter(
			type = FilterType.REGEX,
			pattern = ".*.dao"
		)
	}
)
@PropertySource(value = {
	"classpath:/application.properties"
})
@Import({
	DataAccessConfig.class
})
public class AppConfig {
	
//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setMaxUploadSize(10485760);
//		resolver.setMaxInMemorySize(1024000);
//		return resolver;
//	}
	
}
