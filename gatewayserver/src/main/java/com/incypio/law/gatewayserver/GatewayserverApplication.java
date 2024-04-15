package com.incypio.law.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);


	}
	@Bean
	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/prod/user/**")
						.filters(f -> f.rewritePath("/prod/user/(?<remaining>.*)", "/${remaining}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("userCircuitBreaker"))
						)
						.uri("lb://USER"))
				.route(p -> p.path("/prod/consult/**")
						.filters(f -> f.rewritePath("/prod/consult/(?<remaining>.*)", "/${remaining}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString())
						)
						.uri("lb://CONSULT"))
				.build();
	}

}