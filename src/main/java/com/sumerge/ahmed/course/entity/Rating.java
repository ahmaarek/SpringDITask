package com.sumerge.ahmed.course.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
