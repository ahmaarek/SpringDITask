package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.Course;
import com.sumerge.ahmed.course.CourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


//this is the setter based injection with @Qualiifier

@Service
public class CourseServiceQualifier extends CourseService {

    @Autowired
    public void setCourseRecommender(@Qualifier("humanitiesRecommender") CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }
}
