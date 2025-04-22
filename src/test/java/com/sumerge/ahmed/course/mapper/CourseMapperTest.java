package com.sumerge.ahmed.course.mapper;

import com.sumerge.ahmed.course.dto.CourseDTO;
import com.sumerge.ahmed.course.entity.Author;
import com.sumerge.ahmed.course.entity.Course;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {

    private final CourseMapper mapper = Mappers.getMapper(CourseMapper.class);

    @Test
    void testToDTO() {
        Author author = new Author();
        author.setId(10L);

        Course course = new Course("Math", "Algebra and Geometry", 3, author);
        course.setId(1L);

        CourseDTO dto = mapper.toDTO(course);

        assertEquals(1L, dto.getId());
        assertEquals("Math", dto.getName());
        assertEquals("Algebra and Geometry", dto.getDescription());
        assertEquals(3, dto.getCredit());
        assertEquals(10L, dto.getAuthorId());
    }

    @Test
    void testToEntity() {
        CourseDTO dto = new CourseDTO();
        dto.setId(5L);
        dto.setName("Biology");
        dto.setDescription("Cell Structure");
        dto.setCredit(4);
        dto.setAuthorId(99L);

        Course course = mapper.toEntity(dto);

        assertEquals("Biology", course.getName());
        assertEquals("Cell Structure", course.getDescription());
        assertEquals(4, course.getCredit());
        assertNull(course.getAuthor());
    }

    @Test
    void testToEntity_withNull_returnsNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void testToDTO_withNull_returnsNull() {
        assertNull(mapper.toDTO(null));
    }

    @Test
    void courseAuthorId_returnsNull_whenCourseIsNull() {
        assertNull(mapper.toDTO(null));
    }

    @Test
    void courseAuthorId_returnsNull_whenAuthorIsNull() {
        Course course = new Course();
        course.setAuthor(null);
        assertNull(mapper.toDTO(course).getAuthorId());
    }

    @Test
    void courseAuthorId_returnsNull_whenAuthorIdIsNull() {
        Course course = new Course();
        Author author = new Author();
        author.setId(null);
        course.setAuthor(author);
        assertNull(mapper.toDTO(course).getAuthorId());
    }

}
