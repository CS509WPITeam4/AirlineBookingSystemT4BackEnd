package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    void testGettersSetters() {
        Location loc = new Location();
        loc.setId(42L);
        loc.setCityName("Boston");
        loc.setCityName("Boston, MA");

        assertEquals(42, loc.getId());
        assertEquals("Boston", loc.getCityName());
        assertEquals("Boston, MA", loc.getCityName());
    }
}
