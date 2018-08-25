package com.baeldung.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {// @formatter:off
        return builder.routes()
            .route(r ->
                r.path("/foos/**")
                // .filters(f -> f.stripPrefix(1))
                .uri("http://localhost:8082/resource-server-mvc-2/foos")
            ).build();
    }// @formatter:on

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
