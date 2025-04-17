package com.sumerge.ahmed.course.repository;

import com.sumerge.ahmed.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByAuthorId(Long authorId);
}
