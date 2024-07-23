package com.andersen.ticket_to_ride.util;

import java.math.BigDecimal;

/**
 * Utility class for calculating the price of a trip based on segments.
 * <p>
 * This class provides a method to calculate the price of a trip given the number of segments.
 * The pricing structure is based on different rates for groups of segments.
 * </p>
 */
public class PriceCounter {

    /** The number of segments for the biggest discount price. */
    private static final int A = 3;

    /** The number of segments for the second discount price. */
    private static final int B = 2;

    /** The biggest discount price. */
    private static final double PRICE_FOR_EVERY_A_SEGMENTS = 10.0;

    /** The second discount price. */
    private static final double PRICE_FOR_EVERY_B_SEGMENTS = 7.0;

    /** The price for every single remaining segment. */
    private static final double PRICE_FOR_EVERY_SINGLE_SEGMENT = 5.0;

    private PriceCounter() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Calculates the price based on the number of segments.
     *
     * @param segments the number of segments
     * @return the calculated price
     */
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

