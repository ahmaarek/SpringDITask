package com.sumerge.ahmed.course.recommender;

import com.sumerge.ahmed.course.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class HumanitiesRecommender implements CourseRecommender{
    public List<Course> recommendedCourses() {
        List<Course> courses = new ArrayList<Course>();
//        Author JKRowling = new Author("J.K. Rowling", "jkrowling@email.com", LocalDate.of(1965, 7,31));
//        Author agathaChristie = new Author("Agatha Christie", "agathachristie@email.com", LocalDate.of(1890, 9,15));
//        courses.add(new Course("Literature", "Literature course", 1, JKRowling));
//        courses.add(new Course("Philosophy", "Philosophy course", 2, agathaChristie));
        return courses;
    }
}
