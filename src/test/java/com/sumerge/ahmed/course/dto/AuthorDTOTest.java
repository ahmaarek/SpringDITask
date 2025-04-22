package com.sumerge.ahmed.course.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDTOTest {

    @Test
    void testSettersAndGetters() {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(1L);
        dto.setName("Jane Doe");
        dto.setEmail("jane@example.com");
        dto.setBirthdate(LocalDate.of(1990, 1, 1));

        assertEquals(1L, dto.getId());
        assertEquals("Jane Doe", dto.getName());
        assertEquals("jane@example.com", dto.getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), dto.getBirthdate());
    }

    @Test
    void testEqualsHashCodeToString() {
        AuthorDTO a1 = new AuthorDTO();
        a1.setId(1L);
        a1.setName("John");
        a1.setEmail("john@email.com");
        a1.setBirthdate(LocalDate.of(1990, 5, 15));

        AuthorDTO a2 = new AuthorDTO();
        a2.setId(1L);
        a2.setName("John");
        a2.setEmail("john@email.com");
        a2.setBirthdate(LocalDate.of(1990, 5, 15));

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertTrue(a1.toString().contains("John"));
    }
}
