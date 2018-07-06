package io.spring.gemfire;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.geode.cache.Region;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.util.RegionUtils;

import io.spring.gemfire.configuration.GemfireConfiguration;
import io.spring.gemfire.domain.Item;

@SpringBootApplication
@Import(GemfireConfiguration.class)
@SuppressWarnings("unused")
public class GemfireTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemfireTestApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(GemfireTemplate itemsTemplate) {

		return args -> {

			Region<?, ?> items = itemsTemplate.getRegion();

			assertThat(items).isNotNull();
			assertThat(items.getName()).isEqualTo("Items");
			assertThat(items.getFullPath()).isEqualTo(RegionUtils.toRegionPath("Items"));

			Item item = itemsTemplate.get(1L);

			assertThat(item).isNotNull();
			assertThat(new String(item.getRecord())).isEqualTo("testItem");

			System.err.printf("Item is [%s]%n", item);

			System.err.println("SUCCESS!");
		};
	}
}
