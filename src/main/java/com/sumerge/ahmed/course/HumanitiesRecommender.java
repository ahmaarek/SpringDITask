package com.sumerge.ahmed.course;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class HumanitiesRecommender implements CourseRecommender{
    public List<Course> recommendedCourses() {
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course("Literature", "Literature course"));
        courses.add(new Course("Philosophy", "Philosophy course"));
        return courses;
    }
}
