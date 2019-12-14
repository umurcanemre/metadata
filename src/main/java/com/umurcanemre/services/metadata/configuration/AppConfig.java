package com.umurcanemre.services.metadata.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.Data;

@Configuration
@Data
public class AppConfig {
	@Value("${redis.address}")
	private String redisServerAddress;
	@Value("${redis.auth}")
	private String redisServerAuth;
	
	@Bean
	public OpenAPI customOpenAPI() {
		Contact contact = new Contact();
		contact.setEmail("ibrahimumurcanemre@hotmail.com");
		contact.setUrl("http://www.umurcanemre.com");
		contact.setName("Umurcan Emre");
		
		return new OpenAPI()
	    		.components(new Components())
	            .info(new Info().title("Metadata API's")
	            		.version("0.0.1")
	            		.description("Metadata service for GUI's and other services")
	            		.contact(contact));
	}

	@Bean (name = "languageConnection")
	public RedisCommands<String, String> redisDb0Commands() {
		RedisURI uri = RedisURI.Builder
				.redis("localhost",6379)
				.withPassword(redisServerAuth)
				.withDatabase(0)
				.build();

		RedisClient client = RedisClient.create(uri);
		StatefulRedisConnection<String, String> conn = client.connect();

		return conn.sync();
	}

	@Bean (name = "localizationConnection")
	public RedisCommands<String, String> redisDb1Commands() {
		RedisURI uri = RedisURI.Builder
				.redis("localhost",6379)
				.withPassword(redisServerAuth)
				.withDatabase(1)
				.build();

		RedisClient client = RedisClient.create(uri);
		StatefulRedisConnection<String, String> conn = client.connect();

		return conn.sync();
	}

	@Bean (name = "countryConnection")
	public RedisCommands<String, String> redisDb2Commands() {
		RedisURI uri = RedisURI.Builder
				.redis("localhost",6379)
				.withPassword(redisServerAuth)
				.withDatabase(2)
				.build();

		RedisClient client = RedisClient.create(uri);
		StatefulRedisConnection<String, String> conn = client.connect();

		return conn.sync();
	}
}
