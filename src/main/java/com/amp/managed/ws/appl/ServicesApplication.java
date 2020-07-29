package com.amp.managed.ws.appl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amp.managed.ws.config.ApplicationConstants;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.amp.managed.ws"})
@SpringBootApplication(scanBasePackages = {"com.amp.managed.ws"})
@PropertySource("classpath:" + ApplicationConstants.PROPERTY_FILE_NAME)
public class ServicesApplication extends SpringBootServletInitializer
{
	/**
	 * This is common configuration for app with embedded tomcat and for standalone war deployment
	 * @param application
	 * @return
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServicesApplication.class);
    }
	
	public static void main(String[] args) {
		new SpringApplication(ServicesApplication.class).run(args);
	}

}