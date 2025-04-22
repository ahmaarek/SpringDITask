package com.sumerge.ahmed.course.mapper;

import com.sumerge.ahmed.course.dto.AuthorDTO;
import com.sumerge.ahmed.course.entity.Author;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    private final AuthorMapper mapper = Mappers.getMapper(AuthorMapper.class);

    @Test
    void testToDTO() {
        Author author = new Author("John Doe", "john@example.com", LocalDate.of(1990, 1, 1));
        author.setId(1L);

        AuthorDTO dto = mapper.toDTO(author);

        assertEquals(1L, dto.getId());
        assertEquals("John Doe", dto.getName());
        assertEquals("john@example.com", dto.getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), dto.getBirthdate());
    }

    @Test
    void testToEntity() {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(2L);
        dto.setName("Jane Smith");
        dto.setEmail("jane@example.com");
        dto.setBirthdate(LocalDate.of(1985, 5, 5));

        Author entity = mapper.toEntity(dto);

        assertNull(entity.getCourses());
        assertEquals("Jane Smith", entity.getName());
        assertEquals("jane@example.com", entity.getEmail());
        assertEquals(LocalDate.of(1985, 5, 5), entity.getBirthdate());
    }

    @Test
    void toDTO_shouldReturnNull_whenAuthorIsNull() {
        assertNull(mapper.toDTO(null));
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        assertNull(mapper.toEntity(null));
    }

}
