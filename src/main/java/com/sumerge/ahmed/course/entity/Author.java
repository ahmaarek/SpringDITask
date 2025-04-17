package com.sumerge.ahmed.course.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private LocalDate birthdate;

    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL)
    private List<Course> courses;

    public Author(String name, String email, LocalDate birthdate) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }
    public Author(){

    }
}
