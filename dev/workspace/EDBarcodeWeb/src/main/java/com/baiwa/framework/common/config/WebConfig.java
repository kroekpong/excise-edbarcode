package com.baiwa.framework.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

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
			value = Controller.class
		)
	}
)
public class WebConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		logger.debug("configureViewResolvers");
		
		registry.tiles();
//			.prefix("")
//			.suffix("");
	}
	
	@Bean(name = "tilesConfigurer")
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesconfigurer = new TilesConfigurer();
		tilesconfigurer.setDefinitions(new String[] {
			"/WEB-INF/defs/excise/excise.xml"
		});
		tilesconfigurer.setCheckRefresh(true);
		return tilesconfigurer;
	}

}
