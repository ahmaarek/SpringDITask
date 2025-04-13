package com.sumerge.ahmed.config;

import com.sumerge.ahmed.course.CourseRecommender;
import com.sumerge.ahmed.course.HumanitiesRecommender;
import com.sumerge.ahmed.course.SciencesRecommender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.sumerge.ahmed")
public class CourseConfig {

    @Bean
    @Primary
    public CourseRecommender scienceRecommender() {
        return new SciencesRecommender();
    }

    @Bean
    public CourseRecommender  humanitiesRecommender() {
        return new HumanitiesRecommender();
    }
}
