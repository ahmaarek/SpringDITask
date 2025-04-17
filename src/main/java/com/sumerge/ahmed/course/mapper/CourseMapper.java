package com.sumerge.ahmed.course.mapper;

import com.sumerge.ahmed.course.entity.Course;
import com.sumerge.ahmed.course.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "author.id", target = "authorId")
    CourseDTO toDTO(Course course);

    @Mapping(target = "author", ignore = true)
    Course toEntity(CourseDTO courseDTO);
}
