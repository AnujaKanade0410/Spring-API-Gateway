package com.an.apigw;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

public class CustomGlobalFilter implements GlobalFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomGlobalFilter.class);
	 
   @Override
   public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
   	Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
       String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
       Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
       URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
       LOGGER.info("Incoming request " + originalUri + " is routed to id: " + route.getId()
               + ", uri:" + routeUri);
       return chain.filter(exchange);
   }
}
