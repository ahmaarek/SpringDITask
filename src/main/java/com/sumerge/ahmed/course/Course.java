package com.sumerge.ahmed.course;

import lombok.Getter;
import lombok.Setter;

public class Course {
    @Setter
    @Getter
    String name;
    @Setter
    @Getter
    String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
