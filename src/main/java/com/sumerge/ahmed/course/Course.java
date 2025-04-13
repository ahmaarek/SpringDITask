package com.sumerge.ahmed.course;

public class Course {
    String name;
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
