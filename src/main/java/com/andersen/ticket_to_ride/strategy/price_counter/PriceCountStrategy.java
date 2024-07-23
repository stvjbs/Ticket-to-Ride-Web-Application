package com.andersen.ticket_to_ride.strategy.price_counter;

import java.math.BigDecimal;

public interface PriceCountStrategy {
    BigDecimal countPrice(Integer segments);
}
