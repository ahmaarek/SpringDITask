package com.sumerge.ahmed.course.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseDTOTest {

    @Test
    void testSettersAndGetters() {
        CourseDTO dto = new CourseDTO();
        dto.setId(5L);
        dto.setName("Math");
        dto.setDescription("Basic Math Course");
        dto.setCredit(3);
        dto.setAuthorId(42L);

        assertEquals(5L, dto.getId());
        assertEquals("Math", dto.getName());
        assertEquals("Basic Math Course", dto.getDescription());
        assertEquals(3, dto.getCredit());
        assertEquals(42L, dto.getAuthorId());
    }

    @Test
    void testEqualsHashCodeToString() {
        CourseDTO c1 = new CourseDTO();
        c1.setId(1L);
        c1.setName("Science");
        c1.setDescription("Biology");
        c1.setCredit(4);
        c1.setAuthorId(10L);

        CourseDTO c2 = new CourseDTO();
        c2.setId(1L);
        c2.setName("Science");
        c2.setDescription("Biology");
        c2.setCredit(4);
        c2.setAuthorId(10L);

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
        assertTrue(c1.toString().contains("Science"));
    }
}
