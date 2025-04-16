package com.sumerge.ahmed.controller;

import com.sumerge.ahmed.course.Course;
import com.sumerge.ahmed.course.services.CourseService;
import com.sumerge.ahmed.course.services.CourseServicePrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    private final List<Course> userCourses = new ArrayList<>();

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name) {
        return getUnifiedCourseList().stream()
                .filter(course -> course.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        userCourses.add(course);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Course> updateCourse(@PathVariable String name, @RequestBody Course updatedCourse) {
        for (int i = 0; i < userCourses.size(); i++) {
            if (userCourses.get(i).getName().equalsIgnoreCase(name)) {
                userCourses.set(i, updatedCourse);
                return ResponseEntity.ok(updatedCourse);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCourse(@PathVariable String name) {
        boolean removed = userCourses.removeIf(c -> c.getName().equalsIgnoreCase(name));
        return removed
                ? ResponseEntity.ok("Deleted " + name)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Course> getUnifiedCourseList() {
        List<Course> combined = new ArrayList<>();
        combined.addAll(courseService.getRecommendedCourses());
        combined.addAll(userCourses);
        return combined;
    }
}
