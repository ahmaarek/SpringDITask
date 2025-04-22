package com.sumerge.ahmed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.ahmed.controller.CourseController;
import com.sumerge.ahmed.course.dto.CourseDTO;
import com.sumerge.ahmed.course.services.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAllCourses() throws Exception {
        CourseDTO dto = new CourseDTO();
        dto.setName("Course 1");
        dto.setDescription("Test");
        dto.setAuthorId(1L);

        Mockito.when(courseService.getAllCourses(any()))
                .thenReturn(new PageImpl<>(List.of(dto), PageRequest.of(0, 5), 1));

        mockMvc.perform(get("/api/courses")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Course 1"));
    }

    @Test
    void testAddCourse() throws Exception {
        CourseDTO dto = new CourseDTO();
        dto.setName("New Course");
        dto.setDescription("Desc");
        dto.setAuthorId(1L);

        Mockito.when(courseService.addCourse(any())).thenReturn(dto);

        mockMvc.perform(post("/api/courses")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Course"));
    }

    @Test
    void testUpdateCourse() throws Exception {
        CourseDTO updated = new CourseDTO();
        updated.setName("Updated Course");
        updated.setDescription("Updated");
        updated.setAuthorId(1L);

        Mockito.when(courseService.updateCourseByName(Mockito.eq("Course"), any()))
                .thenReturn(updated);

        mockMvc.perform(put("/api/courses/Course")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Course"));
    }

    @Test
    void testDeleteCourse() throws Exception {
        Mockito.when(courseService.deleteCourseByName("Course")).thenReturn("Deleted Course");

        mockMvc.perform(delete("/api/courses/Course"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Course"));
    }

    @Test
    void testGetCourseByName() throws Exception {
        CourseDTO dto = new CourseDTO();
        dto.setName("Java");
        dto.setAuthorId(1L);

        Mockito.when(courseService.getCourseByName("Java")).thenReturn(dto);

        mockMvc.perform(get("/api/courses/Java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    void testGetCoursesByAuthorId() throws Exception {
        CourseDTO dto = new CourseDTO();
        dto.setName("Math");
        dto.setAuthorId(99L);

        Mockito.when(courseService.getCoursesByAuthorId(99L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/courses/by-author/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].authorId").value(99));
    }
}
