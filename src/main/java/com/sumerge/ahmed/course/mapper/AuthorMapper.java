package com.sumerge.ahmed.course.mapper;

import com.sumerge.ahmed.course.entity.Author;
import com.sumerge.ahmed.course.dto.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(Author author);
    Author toEntity(AuthorDTO dto);
}