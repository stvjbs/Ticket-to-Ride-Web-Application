package com.andersen.ticket_to_ride.util;

import java.math.BigDecimal;

public class PriceCounter {
    private static final int A = 3;
    private static final int B = 2;
    private static final double PRICE_FOR_EVERY_A_SEGMENTS = 10.0;
    private static final double PRICE_FOR_EVERY_B_SEGMENTS = 7.0;
    private static final double PRICE_FOR_EVERY_SINGLE_SEGMENT = 5.0;

    private PriceCounter() {
        throw new IllegalStateException("Utility class");
    }

    public static BigDecimal countPrice(Integer segments) {
        int fullCountGroupsOfThree = segments / A;
        int remainingSegmentsAfterThree = segments % A;
        int fullCountGroupsOfTwo = remainingSegmentsAfterThree / B;
        int remainingSegmentsAfterTwo = remainingSegmentsAfterThree % B;
        BigDecimal price = BigDecimal.valueOf(fullCountGroupsOfThree * PRICE_FOR_EVERY_A_SEGMENTS);
        price = price.add(BigDecimal.valueOf(fullCountGroupsOfTwo * PRICE_FOR_EVERY_B_SEGMENTS));
        if (remainingSegmentsAfterTwo == 1) {
            price = price.add(BigDecimal.valueOf(PRICE_FOR_EVERY_SINGLE_SEGMENT));
        }
        return price;
    }
}

