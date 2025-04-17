package com.sumerge.ahmed.controller;

import com.sumerge.ahmed.course.dto.AuthorDTO;
import com.sumerge.ahmed.course.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorDTO addAuthor(@RequestBody AuthorDTO dto) {
        return authorService.addAuthor(dto);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/by-email")
    public ResponseEntity<AuthorDTO> getByEmail(@RequestParam String email) {
        return authorService.getAuthorByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
