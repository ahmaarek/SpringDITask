package com.sumerge.ahmed.course.repository;

import com.sumerge.ahmed.course.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
