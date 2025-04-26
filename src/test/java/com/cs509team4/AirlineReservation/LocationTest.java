package com.cs509team4.AirlineReservation.test;

import com.cs509team4.AirlineReservation.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    void testGettersAndSetters() {
        Location loc = new Location();
        loc.setId(42L);
        loc.setCityName("Chicago");

        assertEquals(42L, loc.getId());
        assertEquals("Chicago", loc.getCityName());
    }
}
