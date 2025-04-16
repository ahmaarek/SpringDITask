package com.sumerge.ahmed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {
    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext(CourseConfig.class);

        SpringApplication.run(CourseApplication.class, args);

//        CourseServicePrimary primary = context.getBean(CourseServicePrimary.class);
//        CourseServiceQualifier qualifier = context.getBean(CourseServiceQualifier.class);
//        CourseServiceByName byName = context.getBean(CourseServiceByName.class);
//
//        System.out.println("=== Primary (Constructor + @Primary) ===");
//        primary.getRecommendedCourses().forEach(System.out::println);
//
//        System.out.println("\n=== Qualifier (Setter + @Qualifier) ===");
//        qualifier.getRecommendedCourses().forEach(System.out::println);
//
//        //not working due to having multiple beans with the same type (CourseRecommender)
//        //if we need to make it work then remove the primary annotation and comment the courseServicePrimary class
//
////        System.out.println("\n=== By Name (Field + Variable Name) ===");
////        byName.getRecommendedCourses().forEach(System.out::println);
    }
}
