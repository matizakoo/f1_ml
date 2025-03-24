package pl.zak.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getURI().toString();
        String method = exchange.getRequest().toString();
        logger.info("Request przechodzi przez gateway: [{}] {}", method, requestPath);
        logger.info("auth-token: {}", exchange.getRequest().getHeaders().getFirst("auth-token"));

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            int statusCode = exchange.getResponse().getStatusCode().value();
            logger.info("Response status: {}", statusCode);
        }));
    }

    @Override
    public int getOrder() {
        return -1; // Im niższa wartość, tym wyżej w kolejności filtrów
    }
}
