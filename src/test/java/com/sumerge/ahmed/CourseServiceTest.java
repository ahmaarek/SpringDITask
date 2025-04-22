package com.sumerge.ahmed;


import com.sumerge.ahmed.course.dto.CourseDTO;
import com.sumerge.ahmed.course.entity.Course;
import com.sumerge.ahmed.course.mapper.CourseMapper;
import com.sumerge.ahmed.course.recommender.CourseRecommender;
import com.sumerge.ahmed.course.repository.CourseRepository;
import com.sumerge.ahmed.course.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private CourseRecommender courseRecommender;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 5);
        Course course1 = new Course();
        Course course2 = new Course();
        List<Course> courses = List.of(course1, course2);
        Page<Course> coursePage = new PageImpl<>(courses);

        when(courseRepository.findAll(pageable)).thenReturn(coursePage);
        when(courseMapper.toDTO(any(Course.class))).thenReturn(new CourseDTO());

        // Act
        Page<CourseDTO> result = courseService.getAllCourses(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(courseRepository, times(1)).findAll(pageable);
        verify(courseMapper, times(2)).toDTO(any(Course.class));
    }
}
