package com.andersen.ticket_to_ride.config;

import com.andersen.ticket_to_ride.strategy.price_counter.PriceCountStrategy;
import com.andersen.ticket_to_ride.strategy.price_counter.PriceCounter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for price counting strategies.
 */
@Configuration
public class PriceCountingConfig {
    /**
     * Bean definition for the default price counting strategy.
     *
     * @return a new instance of PriceCounter.
     */
    @Bean
    @ConditionalOnProperty(name = "spring.application.pricecounting.type", havingValue = "default", matchIfMissing = true)
    public PriceCountStrategy priceCountStrategy() {
        return new PriceCounter();
    }
}
