package com.andersen.ticket_to_ride.strategy.priceCounter;

import java.math.BigDecimal;

public interface PriceCountStrategy {
    BigDecimal countPrice(Integer segments);
}
