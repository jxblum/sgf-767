/**
 * Copyright 2018 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.gemfire.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.PeerCacheApplication;

import io.spring.gemfire.domain.Item;

/**
 * @author David Turanski
 */
@PeerCacheApplication
@EnableEntityDefinedRegions(basePackageClasses = Item.class)
@SuppressWarnings("unused")
public class GemfireConfiguration {

	@Bean
	public GemfireTemplate itemsTemplate(org.apache.geode.cache.Region<Object, Object> region) {

		assertThat(region).isNotNull();
		assertThat(region.getName()).isEqualTo("Items");

		Item testItem = new Item("testKey".getBytes(), "testItem".getBytes());

		region.put(1L, testItem);

		return new GemfireTemplate(region);
	}
}
