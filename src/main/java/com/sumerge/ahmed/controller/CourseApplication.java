package com.sumerge.ahmed.controller;

import com.sumerge.ahmed.config.CourseConfig;
import com.sumerge.ahmed.course.*;
import com.sumerge.ahmed.course.services.CourseServiceByName;
import com.sumerge.ahmed.course.services.CourseServicePrimary;
import com.sumerge.ahmed.course.services.CourseServiceQualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CourseApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CourseConfig.class);



        CourseServicePrimary primary = context.getBean(CourseServicePrimary.class);
        CourseServiceQualifier qualifier = context.getBean(CourseServiceQualifier.class);
        CourseServiceByName byName = context.getBean(CourseServiceByName.class);

        System.out.println("=== Primary (Constructor + @Primary) ===");
        primary.getRecommendedCourses().forEach(System.out::println);

        System.out.println("\n=== Qualifier (Setter + @Qualifier) ===");
        qualifier.getRecommendedCourses().forEach(System.out::println);

        //not working due to having multiple beans with the same type (CourseRecommender)
        //if we need to make it work then remove the primary annotation and comment the courseServicePrimary class

//        System.out.println("\n=== By Name (Field + Variable Name) ===");
//        byName.getRecommendedCourses().forEach(System.out::println);
    }
}
