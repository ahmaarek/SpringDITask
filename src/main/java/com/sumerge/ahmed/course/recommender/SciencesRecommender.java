package com.sumerge.ahmed.course.recommender;

import com.sumerge.ahmed.course.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class SciencesRecommender implements CourseRecommender{
    public List<Course> recommendedCourses() {
        List<Course> courses = new ArrayList<Course>();
//        Author newton = new Author("Isaac Newton", "newton@email.com", LocalDate.of(1643, 1, 4));
//        Author marieCurie = new Author("Marie Curie", "mariecurie@email.com", LocalDate.of(1867, 11, 7));
//        courses.add(new Course("Physics", "Physics course", 3, newton));
//        courses.add(new Course("Chemistry", "Chemistry course", 4, marieCurie));
        return courses;
    }
}
