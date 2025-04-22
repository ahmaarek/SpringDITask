package com.sumerge.ahmed.course.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
