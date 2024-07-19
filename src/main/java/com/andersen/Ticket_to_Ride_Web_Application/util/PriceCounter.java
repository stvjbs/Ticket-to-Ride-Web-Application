package com.andersen.Ticket_to_Ride_Web_Application.util;

import java.math.BigDecimal;

public class PriceCounter {
    public static BigDecimal countPrice(Integer segments) {
        int fullPriceGroupsOfThree = segments / 3;
        int remainingSegmentsAfterThree = segments % 3;

        int fullPriceGroupsOfTwo = remainingSegmentsAfterThree / 2;
        int remainingSegmentsAfterTwo = remainingSegmentsAfterThree % 2;

        BigDecimal price = BigDecimal.valueOf(fullPriceGroupsOfThree * 10.0);
        price = price.add(BigDecimal.valueOf(fullPriceGroupsOfTwo * 7.0));

        if (remainingSegmentsAfterTwo == 1) {
            price = price.add(BigDecimal.valueOf(5.0));
        }

        return price;
    }
}

