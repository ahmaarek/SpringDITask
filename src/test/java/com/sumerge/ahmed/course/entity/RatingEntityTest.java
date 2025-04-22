package com.sumerge.ahmed.course.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RatingEntityTest {

    @Test
    void testSettersAndGetters() {
        Rating rating = new Rating();
        rating.setId(1L);
        rating.setNumber(5);

        Course course = new Course();
        rating.setCourse(course);

        assertEquals(1L, rating.getId());
        assertEquals(5, rating.getNumber());
        assertEquals(course, rating.getCourse());
    }

    @Test
    void testEqualsHashCode() {
        Rating r1 = new Rating();
        Rating r2 = new Rating();

        r1.setId(10L);
        r2.setId(10L);
        r1.setNumber(4);
        r2.setNumber(4);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}
