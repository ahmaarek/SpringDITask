package com.sumerge.ahmed.course.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    String name;

    String description;

    private int credit;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Assessment> assessments;

    public Course(String name, String description, int credit, Author author) {
        this.name = name;
        this.description = description;
        this.credit = credit;
        this.author = author;
    }

    public Course() {

    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
