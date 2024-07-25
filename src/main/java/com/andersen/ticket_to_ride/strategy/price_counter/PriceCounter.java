package com.andersen.ticket_to_ride.strategy.price_counter;

import com.andersen.ticket_to_ride.exception.SegmentsNegativeException;

import java.math.BigDecimal;

/**
 * Utility class for calculating the price of a trip based on segments.
 * <p>
 * This class provides a method to calculate the price of a trip given the number of segments.
 * The pricing structure is based on different rates for groups of segments.
 * </p>
 */
public final class PriceCounter implements PriceCountStrategy {

    /**
     * The number of segments for the biggest discount price.
     */
    private static final int A = 3;

    /**
     * The number of segments for the second discount price.
     */
    private static final int B = 2;

    /**
     * The biggest discount price.
     */
    private static final double PRICE_FOR_EVERY_A_SEGMENTS = 10.0;

    /**
     * The second discount price.
     */
    private static final double PRICE_FOR_EVERY_B_SEGMENTS = 7.0;

    /**
     * The price for every single remaining segment.
     */
    private static final double PRICE_FOR_EVERY_SINGLE_SEGMENT = 5.0;

    /**
     * Calculates the price based on the number of segments.
     *
     * @param segments the number of segments
     * @return the calculated price
     */
    @Override
    public BigDecimal countPrice(Integer segments) {
        if (segments < 0) {
            throw new SegmentsNegativeException("Segments cannot be negative");
        }
        int countGroupsOfA = segments / A;
        int remainingSegmentsAfterA = segments % A;
        int countGroupsOfB = remainingSegmentsAfterA / B;
        int remainingSegmentsAfterB = remainingSegmentsAfterA % B;
        return BigDecimal.valueOf(countGroupsOfA * PRICE_FOR_EVERY_A_SEGMENTS)
                .add(BigDecimal.valueOf(countGroupsOfB * PRICE_FOR_EVERY_B_SEGMENTS))
                .add(BigDecimal.valueOf(remainingSegmentsAfterB * PRICE_FOR_EVERY_SINGLE_SEGMENT));
    }
}

