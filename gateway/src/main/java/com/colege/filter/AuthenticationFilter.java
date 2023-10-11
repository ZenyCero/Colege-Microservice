package com.colege.filter;

import com.colege.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RouteValidator validator;
    public AuthenticationFilter() {
        super(Config.class);
    }
    private final String[] exchangeArray = {"0", "0"};
    private final boolean[] authArray = {false, false};

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            previous_path_and_previous_auth(exchange);

            if((!exchangeArray[1].contains("/api/v1/schools/with-students/") ||
                    !exchangeArray[1].contains("/api/v1/students")) && !authArray[1]){
                if ((validator.isSecured.test(exchange.getRequest()))){
                    if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                        throw new RuntimeException("missing Auth Header");
                    }
                    String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                    if(authHeader != null && authHeader.startsWith("Bearer ")){
                        authHeader = authHeader.substring(7);
                    }
                    try {
                        jwtUtil.validateToken(authHeader);
                    } catch (RestClientException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            return chain.filter(exchange);
        });
    }
    public void previous_path_and_previous_auth(ServerWebExchange exchange){
        authArray[1] = authArray[0];
        authArray[0] = exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION);

        exchangeArray[1] = exchangeArray[0];
        exchangeArray[0] = exchange.getRequest().getPath().toString();
    }

    public static class Config{

    }
}
