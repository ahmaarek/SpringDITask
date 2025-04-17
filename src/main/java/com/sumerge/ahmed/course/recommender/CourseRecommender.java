package com.sumerge.ahmed.course.recommender;

import com.sumerge.ahmed.course.entity.Course;

import java.util.List;

public interface CourseRecommender {
    List<Course> recommendedCourses();
}
