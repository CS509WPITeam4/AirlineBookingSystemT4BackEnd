package com.cs509team4.AirlineReservation;

import com.cs509team4.AirlineReservation.UserDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {
    @Test
    public void testDTOFields() {
        UserDTO dto = new UserDTO();

        dto.setUsername("user");
        dto.setEmail("email@example.com");

        assertEquals("user", dto.getUsername());
        assertEquals("email@example.com", dto.getEmail());
    }
}
