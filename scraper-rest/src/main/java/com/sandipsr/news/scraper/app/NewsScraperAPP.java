package com.sandipsr.news.scraper.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.sandipsr.news.scraper.author.dao,com.sandipsr.news.scraper.articles.dao")
@EnableJpaRepositories("com.sandipsr.news.scraper")
@EntityScan("com.sandipsr.news.scraper")
@ComponentScan({"com.sandipsr.news*, com.sandipsr.news.scraper"})
public class NewsScraperAPP {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(NewsScraperAPP.class);
	
	public static void main(String[] args) {
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("=============================================");
			LOGGER.debug("DEBUG: Starting application now");
			LOGGER.debug("=============================================");
		}
		else if(LOGGER.isInfoEnabled()){
			LOGGER.debug("=============================================");
			LOGGER.debug("INFO: Starting application now");
			LOGGER.debug("=============================================");
		}
		else if(LOGGER.isWarnEnabled()){
			LOGGER.debug("=============================================");
			LOGGER.debug("WARN: Starting application now");
			LOGGER.debug("=============================================");
		}
		SpringApplication.run(NewsScraperAPP.class, args);
	}
}
