package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.entity.Author;
import com.sumerge.ahmed.course.entity.Course;
import com.sumerge.ahmed.course.recommender.CourseRecommender;
import com.sumerge.ahmed.course.dto.CourseDTO;
import com.sumerge.ahmed.course.mapper.CourseMapper;
import com.sumerge.ahmed.course.repository.AuthorRepository;
import com.sumerge.ahmed.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock private CourseRepository courseRepository;
    @Mock private CourseMapper courseMapper;
    @Mock private CourseRecommender courseRecommender;
    @Mock private AuthorRepository authorRepository;

    @InjectMocks private CourseService courseService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        Course course = new Course();
        CourseDTO dto = new CourseDTO();
        Pageable pageable = PageRequest.of(0, 5);

        when(courseRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(course)));
        when(courseMapper.toDTO(course)).thenReturn(dto);

        Page<CourseDTO> result = courseService.getAllCourses(pageable);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetCourseByName_found() {
        Course course = new Course();
        course.setName("Math");
        CourseDTO dto = new CourseDTO();

        when(courseRepository.findAll()).thenReturn(List.of(course));
        when(courseMapper.toDTO(course)).thenReturn(dto);

        CourseDTO result = courseService.getCourseByName("Math");
        assertNotNull(result);
    }

    @Test
    void testAddCourse() {
        CourseDTO dto = new CourseDTO();
        dto.setAuthorId(1L);

        Course course = new Course();
        Author author = new Author();

        when(courseMapper.toEntity(dto)).thenReturn(course);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(dto);

        CourseDTO result = courseService.addCourse(dto);
        assertEquals(dto, result);
    }

    @Test
    void testUpdateCourseByName() {
        Course existing = new Course();
        existing.setName("History");

        CourseDTO updated = new CourseDTO();
        updated.setName("Modern History");
        updated.setDescription("Updated");
        updated.setCredit(4);

        Author author = new Author();
        updated.setAuthorId(1L);

        when(courseRepository.findAll()).thenReturn(List.of(existing));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(courseRepository.save(existing)).thenReturn(existing);
        when(courseMapper.toDTO(existing)).thenReturn(updated);

        CourseDTO result = courseService.updateCourseByName("History", updated);
        assertEquals("Modern History", result.getName());
    }

    @Test
    void testDeleteCourseByName() {
        Course course = new Course();
        course.setName("Physics");

        when(courseRepository.findAll()).thenReturn(List.of(course));

        String result = courseService.deleteCourseByName("Physics");

        assertTrue(result.contains("Deleted course"));
        verify(courseRepository).delete(course);
    }

    @Test
    void testGetRecommendedCourses() {
        Course c1 = new Course();
        when(courseRecommender.recommendedCourses()).thenReturn(List.of(c1));
        List<Course> result = courseService.getRecommendedCourses();
        assertEquals(1, result.size());
    }

    @Test
    void testGetCoursesByAuthorId() {
        Course c = new Course();
        CourseDTO dto = new CourseDTO();

        when(courseRepository.findByAuthorId(5L)).thenReturn(List.of(c));
        when(courseMapper.toDTO(c)).thenReturn(dto);

        List<CourseDTO> result = courseService.getCoursesByAuthorId(5L);
        assertEquals(1, result.size());
    }

    @Test
    void testGetCourseByName_notFound() {

        when(courseRepository.findAll()).thenReturn(List.of());

        assertThrows(RuntimeException.class, () -> courseService.getCourseByName("Nonexistent Course"));
    }

    @Test
    void updateCourseByName_shouldWorkWithoutAuthorChange() {

        CourseDTO updatedDTO = new CourseDTO();
        updatedDTO.setName("Updated Course");
        updatedDTO.setDescription("Updated Description");
        updatedDTO.setCredit(4);
        updatedDTO.setAuthorId(null);

        Author existingAuthor = new Author("John", "john@example.com", LocalDate.of(1990, 1, 1));
        existingAuthor.setId(1L);

        Course existingCourse = new Course("Old Course", "Old Desc", 3, existingAuthor);
        existingCourse.setId(99L);

        when(courseRepository.findAll()).thenReturn(List.of(existingCourse));
        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(courseMapper.toDTO(any(Course.class))).thenAnswer(invocation -> {
            Course course = invocation.getArgument(0);
            CourseDTO dto = new CourseDTO();
            dto.setId(course.getId());
            dto.setName(course.getName());
            dto.setDescription(course.getDescription());
            dto.setCredit(course.getCredit());
            dto.setAuthorId(course.getAuthor() != null ? course.getAuthor().getId() : null);
            return dto;
        });


        CourseDTO result = courseService.updateCourseByName("Old Course", updatedDTO);


        assertEquals("Updated Course", result.getName());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(4, result.getCredit());
        assertEquals(1L, result.getAuthorId());
    }


}
