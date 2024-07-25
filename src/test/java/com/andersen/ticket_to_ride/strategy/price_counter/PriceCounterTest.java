package com.andersen.ticket_to_ride.strategy.price_counter;

import com.andersen.ticket_to_ride.exception.SegmentsNegativeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PriceCounterTest {

    private PriceCounter priceCounter;

    @BeforeEach
    void setUp() {
        priceCounter = new PriceCounter();
    }

    @Test
    void countPrice_NoSegments_ReturnsZero() {
        BigDecimal price = priceCounter.countPrice(0);
        assertEquals(BigDecimal.valueOf(0.0), price);
    }

    @Test
    void countPrice_LessThanA_ReturnsSingleSegmentPrice() {
        BigDecimal price = priceCounter.countPrice(2);
        assertEquals(BigDecimal.valueOf(7.0), price);
    }

    @Test
    void countPrice_ExactMultipleOfA_ReturnsDiscountedPrice() {
        BigDecimal price = priceCounter.countPrice(3);
        assertEquals(BigDecimal.valueOf(10.0), price);
    }

    @Test
    void countPrice_MixedSegments_ReturnsCorrectPrice() {
        BigDecimal price = priceCounter.countPrice(5);
        assertEquals(BigDecimal.valueOf(17.0), price);
    }

    @Test
    void countPrice_MultipleOfAAndB_ReturnsCorrectPrice() {
        BigDecimal price = priceCounter.countPrice(10);
        assertEquals(BigDecimal.valueOf(35.0), price);
    }

    @Test
    void countPrice_NegativeArgument_ReturnsCorrectPrice() {
        Exception exception = assertThrows(SegmentsNegativeException.class, () -> priceCounter.countPrice(-1));
        assertEquals("Segments cannot be negative", exception.getMessage());
    }
}