package com.sumerge.ahmed.course.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseEntityTest {

    @Test
    void testConstructorAndGetters() {
        Author author = new Author();
        Course course = new Course("Physics", "Study of matter", 4, author);

        assertEquals("Physics", course.getName());
        assertEquals("Study of matter", course.getDescription());
        assertEquals(4, course.getCredit());
        assertEquals(author, course.getAuthor());
    }

    @Test
    void testSetters() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Chemistry");
        course.setDescription("Chemical reactions");
        course.setCredit(5);

        Author author = new Author();
        course.setAuthor(author);

        course.setAssessments(List.of());
        course.setRatings(List.of());

        assertEquals(1L, course.getId());
        assertEquals("Chemistry", course.getName());
        assertEquals("Chemical reactions", course.getDescription());
        assertEquals(5, course.getCredit());
        assertNotNull(course.getAssessments());
        assertNotNull(course.getRatings());
    }

    @Test
    void testEqualsHashCodeToString() {
        Author author = new Author();
        Course course1 = new Course("Biology", "Life science", 3, author);
        Course course2 = new Course("Biology", "Life science", 3, author);

        course1.setId(100L);
        course2.setId(100L);

        assertEquals(course1, course2);
        assertEquals(course1.hashCode(), course2.hashCode());

        String out = course1.toString();
        assertTrue(out.contains("Biology"));
        assertTrue(out.contains("Life science"));
    }

    @Test
    void testEquals_sameFields_shouldBeEqual() {
        Course c1 = new Course("Java", "OOP course", 3, null);
        c1.setId(1L);
        Course c2 = new Course("Java", "OOP course", 3, null);
        c2.setId(1L);

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }


    @Test
    void testEquals_differentIds_shouldNotBeEqual() {
        Course c1 = new Course("Java", "OOP course", 3, null);
        c1.setId(1L);
        Course c2 = new Course("Java", "OOP course", 3, null);
        c2.setId(2L);

        assertNotEquals(c1, c2);
    }


    @Test
    void testEquals_nullAndDifferentClass() {
        Course c1 = new Course("Java", "OOP course", 3, null);

        assertNotEquals(null, c1);
        assertNotEquals("some string", c1);
    }

}
