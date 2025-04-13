package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.CourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServicePrimary extends CourseService {

    @Autowired
    public CourseServicePrimary(CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }
}
