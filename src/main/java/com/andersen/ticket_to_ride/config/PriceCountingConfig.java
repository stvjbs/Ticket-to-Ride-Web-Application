package com.andersen.ticket_to_ride.config;

import com.andersen.ticket_to_ride.strategy.priceCounter.PriceCountStrategy;
import com.andersen.ticket_to_ride.strategy.priceCounter.PriceCounter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class PriceCountingConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.application.pricecounting.type", havingValue = "default", matchIfMissing = true)
    PriceCountStrategy priceCountStrategy() {
        return new PriceCounter();
    }
}
