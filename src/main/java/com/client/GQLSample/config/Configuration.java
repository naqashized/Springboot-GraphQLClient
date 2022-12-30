package com.client.GQLSample.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.client.CustomGraphQLClient;
import com.netflix.graphql.dgs.client.GraphQLClient;
import com.netflix.graphql.dgs.client.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class Configuration {
    @Value("${client.baseUrl}")
    private String graphqlEndpoint;

    @Bean
    public CustomGraphQLClient getCustomGraphQLClient() {
        HttpHeaders httpHeaders = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        return GraphQLClient.createCustom(graphqlEndpoint,  (url, headers, body) -> {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> exchange = restTemplate.exchange(graphqlEndpoint, HttpMethod.POST, new HttpEntity<>(body, httpHeaders), String.class);
            return new HttpResponse(exchange.getStatusCodeValue(), exchange.getBody());
        });
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
