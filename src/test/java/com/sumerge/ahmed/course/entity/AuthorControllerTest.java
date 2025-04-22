package com.sumerge.ahmed.course.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.ahmed.controller.AuthorController;
import com.sumerge.ahmed.course.dto.AuthorDTO;
import com.sumerge.ahmed.course.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());


    @Test
    void testGetAllAuthors() throws Exception {
        AuthorDTO author = new AuthorDTO();
        author.setId(1L);
        author.setName("John");
        author.setEmail("john@email.com");
        author.setBirthdate(LocalDate.of(1990, 1, 1));

        when(authorService.getAllAuthors()).thenReturn(List.of(author));

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    void testAddAuthor() throws Exception {
        AuthorDTO author = new AuthorDTO();
        author.setName("Jane");
        author.setEmail("jane@email.com");
        author.setBirthdate(LocalDate.of(1992, 2, 2));

        when(authorService.addAuthor(any())).thenReturn(author);

        mockMvc.perform(post("/api/authors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"));
    }

    @Test
    void testGetAuthorByEmail() throws Exception {
        AuthorDTO author = new AuthorDTO();
        author.setName("Doe");
        author.setEmail("doe@email.com");
        author.setBirthdate(LocalDate.of(1985, 5, 5));

        when(authorService.getAuthorByEmail("doe@email.com")).thenReturn(Optional.of(author));

        mockMvc.perform(get("/api/authors/by-email")
                        .param("email", "doe@email.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("doe@email.com"));
    }
}
