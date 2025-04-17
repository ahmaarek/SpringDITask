package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.entity.Author;
import com.sumerge.ahmed.course.dto.AuthorDTO;
import com.sumerge.ahmed.course.mapper.AuthorMapper;
import com.sumerge.ahmed.course.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorDTO addAuthor(AuthorDTO dto) {
        Author author = authorMapper.toEntity(dto);
        return authorMapper.toDTO(authorRepository.save(author));
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    public Optional<AuthorDTO> getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email).map(authorMapper::toDTO);
    }
}
