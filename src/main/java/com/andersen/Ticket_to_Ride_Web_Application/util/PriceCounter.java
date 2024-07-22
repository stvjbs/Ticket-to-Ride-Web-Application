package com.andersen.Ticket_to_Ride_Web_Application.util;

import java.math.BigDecimal;

public class PriceCounter {
    private static final double PRICE_FOR_EVERY_3_SEGMENTS = 10.0;
    private static final double PRICE_FOR_EVERY_2_SEGMENTS = 7.0;
    private static final double PRICE_FOR_EVERY_SINGLE_SEGMENT = 5.0;

    public static BigDecimal countPrice(Integer segments) {
        int fullCountGroupsOfThree = segments / 3;
        int remainingSegmentsAfterThree = segments % 3;
        int fullCountGroupsOfTwo = remainingSegmentsAfterThree / 2;
        int remainingSegmentsAfterTwo = remainingSegmentsAfterThree % 2;
        BigDecimal price = BigDecimal.valueOf(fullCountGroupsOfThree * PRICE_FOR_EVERY_3_SEGMENTS);
        price = price.add(BigDecimal.valueOf(fullCountGroupsOfTwo * PRICE_FOR_EVERY_2_SEGMENTS));
        if (remainingSegmentsAfterTwo == 1) {
            price = price.add(BigDecimal.valueOf(PRICE_FOR_EVERY_SINGLE_SEGMENT));
        }
        return price;
    }
}

