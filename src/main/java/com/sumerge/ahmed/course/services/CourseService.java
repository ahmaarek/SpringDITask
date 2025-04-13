package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.Course;
import com.sumerge.ahmed.course.CourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public abstract class CourseService {
    protected CourseRecommender courseRecommender;

    public List<Course> getRecommendedCourses() {
        return courseRecommender.recommendedCourses();
    }
}
