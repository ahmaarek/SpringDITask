package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.dto.AuthorDTO;
import com.sumerge.ahmed.course.entity.Author;
import com.sumerge.ahmed.course.mapper.AuthorMapper;
import com.sumerge.ahmed.course.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() {
        AuthorDTO dto = new AuthorDTO();
        dto.setName("John");
        dto.setEmail("john@email.com");
        dto.setBirthdate(LocalDate.of(1990, 1, 1));

        Author entity = new Author("John", "john@email.com", LocalDate.of(1990, 1, 1));

        when(authorMapper.toEntity(dto)).thenReturn(entity);
        when(authorRepository.save(entity)).thenReturn(entity);
        when(authorMapper.toDTO(entity)).thenReturn(dto);

        AuthorDTO result = authorService.addAuthor(dto);

        assertEquals("John", result.getName());
        verify(authorRepository).save(entity);
    }

    @Test
    void testGetAllAuthors() {
        Author author = new Author("Alice", "alice@email.com", LocalDate.of(1995, 2, 15));
        AuthorDTO dto = new AuthorDTO();
        dto.setName("Alice");

        when(authorRepository.findAll()).thenReturn(List.of(author));
        when(authorMapper.toDTO(author)).thenReturn(dto);

        List<AuthorDTO> result = authorService.getAllAuthors();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    void testGetAuthorByEmail() {
        Author author = new Author("Bob", "bob@email.com", LocalDate.of(1988, 3, 10));
        AuthorDTO dto = new AuthorDTO();
        dto.setName("Bob");

        when(authorRepository.findByEmail("bob@email.com")).thenReturn(Optional.of(author));
        when(authorMapper.toDTO(author)).thenReturn(dto);

        Optional<AuthorDTO> result = authorService.getAuthorByEmail("bob@email.com");

        assertTrue(result.isPresent());
        assertEquals("Bob", result.get().getName());
    }
}
