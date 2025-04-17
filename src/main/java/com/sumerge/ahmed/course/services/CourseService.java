package com.sumerge.ahmed.course.services;

import com.sumerge.ahmed.course.entity.Author;
import com.sumerge.ahmed.course.entity.Course;
import com.sumerge.ahmed.course.recommender.CourseRecommender;
import com.sumerge.ahmed.course.dto.CourseDTO;
import com.sumerge.ahmed.course.mapper.CourseMapper;
import com.sumerge.ahmed.course.repository.AuthorRepository;
import com.sumerge.ahmed.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class CourseService {

    private final CourseRecommender courseRecommender;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         CourseMapper courseMapper,
                         CourseRecommender courseRecommender,
                         AuthorRepository authorRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseRecommender = courseRecommender;
        this.authorRepository = authorRepository;
    }


    public Page<CourseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(courseMapper::toDTO);
    }


    public CourseDTO getCourseByName(String name) {
        Course course = courseRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.toDTO(course);
    }


    public CourseDTO addCourse(CourseDTO dto) {
        Course course = courseMapper.toEntity(dto);
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        course.setAuthor(author);
        return courseMapper.toDTO(courseRepository.save(course));
    }


    public CourseDTO updateCourseByName(String name, CourseDTO updatedDTO) {
        Course existing = courseRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existing.setName(updatedDTO.getName());
        existing.setDescription(updatedDTO.getDescription());
        existing.setCredit(updatedDTO.getCredit());

        if (updatedDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(updatedDTO.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            existing.setAuthor(author);
        }

        return courseMapper.toDTO(courseRepository.save(existing));
    }


    public String deleteCourseByName(String name) {
        Course existing = courseRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courseRepository.delete(existing);
        return "Deleted course: " + name;
    }


    public List<Course> getRecommendedCourses() {
        return courseRecommender.recommendedCourses();
    }


    public List<CourseDTO> getCoursesByAuthorId(Long authorId) {
        return courseRepository.findByAuthorId(authorId)
                .stream()
                .map(courseMapper::toDTO)
                .toList();
    }
}
