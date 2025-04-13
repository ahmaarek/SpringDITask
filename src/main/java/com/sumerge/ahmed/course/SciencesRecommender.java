package com.sumerge.ahmed.course;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class SciencesRecommender implements CourseRecommender{
    public List<Course> recommendedCourses() {
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course("Physics", "Physics course"));
        courses.add(new Course("Chemistry", "Chemistry course"));
        return courses;
    }
}
