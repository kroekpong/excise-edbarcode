package com.baiwa.framework.common.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(
	basePackages = {
		"th.go.excise.edbarcode"
	},
	includeFilters = {
		@ComponentScan.Filter(
			type = FilterType.REGEX,
			pattern = ".*.controller"
		),
		@ComponentScan.Filter(
			type = FilterType.ANNOTATION,
			value = RestController.class
		)
	}
)
public class RestConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(new MappingJackson2HttpMessageConverter());
	}
}
