package com.sumerge.ahmed.controller;

import com.sumerge.ahmed.course.dto.CourseDTO;
import com.sumerge.ahmed.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public Page<CourseDTO> getAllCourses(@ParameterObject Pageable pageable) {
        return courseService.getAllCourses(pageable);
    }


    @PostMapping
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }


    @PutMapping("/{name}")
    public CourseDTO updateCourse(@PathVariable String name, @RequestBody CourseDTO updatedDTO) {
        return courseService.updateCourseByName(name, updatedDTO);
    }

    @DeleteMapping("/{name}")
    public String deleteCourse(@PathVariable String name) {
        return courseService.deleteCourseByName(name);
    }

    @GetMapping("/{name}")
    public CourseDTO getCourseByName(@PathVariable String name) {
        return courseService.getCourseByName(name);
    }

    @GetMapping("/by-author/{authorId}")
    public List<CourseDTO> getCoursesByAuthor(@PathVariable Long authorId) {
        return courseService.getCoursesByAuthorId(authorId);
    }
}
