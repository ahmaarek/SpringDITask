package com.sumerge.ahmed.course.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssessmentEntityTest {

    @Test
    void testSettersAndGetters() {
        Course course = new Course();
        Assessment a = new Assessment();
        a.setId(1L);
        a.setContent("Quiz 1");
        a.setCourse(course);

        assertEquals(1L, a.getId());
        assertEquals("Quiz 1", a.getContent());
        assertEquals(course, a.getCourse());
    }

    @Test
    void testEqualsHashCodeToString() {
        Course course = new Course();

        Assessment a1 = new Assessment();
        a1.setId(1L);
        a1.setContent("Quiz 1");
        a1.setCourse(course);

        Assessment a2 = new Assessment();
        a2.setId(1L);
        a2.setContent("Quiz 1");
        a2.setCourse(course);

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertTrue(a1.toString().contains("Quiz"));
    }

    @Test
    void testEqualsDifferentClassTriggersCanEqual() {
        Assessment a = new Assessment();
        a.setId(1L);
        a.setContent("Quiz");

        assertNotEquals(new Object(), a);
    }
}
