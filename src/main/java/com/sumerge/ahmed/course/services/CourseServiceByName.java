package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.Course;
import com.sumerge.ahmed.course.CourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

// Field-based injection by variable name
@Service

public class CourseServiceByName extends CourseService {

    @Autowired
    CourseRecommender humanitiesRecommender;

    //Post construct is needed as the field name is different in the super class -
    // normally field injection does not need a post construct but
    // because the attribute names are different I believe this is needed
    @PostConstruct
    public void init() {
        this.courseRecommender = humanitiesRecommender;
    }
}