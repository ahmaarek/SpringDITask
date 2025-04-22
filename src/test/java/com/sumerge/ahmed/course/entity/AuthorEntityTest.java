package com.sumerge.ahmed.course.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorEntityTest {

    @Test
    void testAuthorFields() {
        Author author = new Author("Test Name", "test@email.com", LocalDate.of(1990, 1, 1));

        assertEquals("Test Name", author.getName());
        assertEquals("test@email.com", author.getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), author.getBirthdate());

        author.setCourses(List.of());
        assertNotNull(author.getCourses());
    }

    @Test
    void testEqualsHashCodeToString() {
        Author author1 = new Author("Ali", "ali@example.com", LocalDate.of(1990, 1, 1));
        Author author2 = new Author("Ali", "ali@example.com", LocalDate.of(1990, 1, 1));

        author1.setId(1L);
        author2.setId(1L);

        // hashCode and equals
        assertEquals(author1, author2);
        assertEquals(author1.hashCode(), author2.hashCode());

        // toString
        String output = author1.toString();
        assertTrue(output.contains("Ali"));
        assertTrue(output.contains("ali@example.com"));
    }

    @Test
    void testSetters() {
        Author author = new Author();
        author.setId(10L);
        author.setName("Nada");
        author.setEmail("nada@email.com");
        author.setBirthdate(LocalDate.of(1980, 5, 5));

        assertEquals(10L, author.getId());
        assertEquals("Nada", author.getName());
    }

}
