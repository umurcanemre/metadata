package com.umurcanemre.services.metadata.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.Data;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Data
public class AppConfig {
	@Value("${redis.address}")
	private String redisServerAddress;
	@Value("${redis.auth}")
	private String redisServerAuth;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfoBuilder().title("Metadata API's")
						.description("Metadata service for GUI's and other services").version("0.0.1")
						.contact(new Contact("Umurcan Emre", "http://www.umurcanemre.com",
								"ibrahimumurcanemre@hotmail.com"))
						.build());

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
}
