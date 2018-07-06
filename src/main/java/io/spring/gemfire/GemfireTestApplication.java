package io.spring.gemfire;

import io.spring.gemfire.configuration.GemfireConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.PeerCacheApplication;

@SpringBootApplication
@Import(GemfireConfiguration.class)
public class GemfireTestApplication {

	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {

		SpringApplication.run(GemfireTestApplication.class, args);
	}

	@Profile("works")
	@Configuration
	@PeerCacheApplication
	@EnableEntityDefinedRegions
	public static class WorksConfiguration extends GemfireConfiguration {

	}

}
