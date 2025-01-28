package dev.luisespinoza.pma.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.luisespinoza.pma.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @MockitoBean
    ProjectService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void givenExistingProjectId_whenGetProjectById_thenProjectIsReturned() throws Exception {
        // Given
        ProjectResponse expectedProject = new ProjectResponse(2L, "New project", "Building software");

        when(service.findById(expectedProject.getId())).thenReturn(expectedProject);

        // When
        ResultActions response = mockMvc.perform(get("/api/projects/{id}", expectedProject.getId()));

        // Then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedProject.getName()));
    }

    @Test
    public void givenNonExistingProjectId_whenGetProjectById_thenResourceNotFoundExceptionIsThrown() throws Exception {
        // Given
        Long id = 100L;
        given(service.findById(id)).willThrow(new ResourceNotFoundException("Project not found with ID " + id));

        // When
        ResultActions response = mockMvc.perform(get("/api/projects/{id}", id));

        // Then
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Project not found with ID " + id));
    }

    @Test
    public void givenValidProjectRequest_whenCreateProject_thenCreatedProjectIsReturned() throws Exception {
        //Given
        ProjectRequest projectRequest = new ProjectRequest(null, "Awesome project", "Awesome project description");
        Long expectedProjectId = 1L;
        given(service.create(any(ProjectRequest.class))).willReturn(expectedProjectId);

        //When
        ResultActions response = mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(projectRequest)));

        //Then
        response.andExpect(status().isCreated())
                .andExpect(content().string(expectedProjectId.toString()));
    }

    @Test
    public void givenInvalidProjectRequest_whenCreateProject_thenBadRequestExceptionIsReturned() throws Exception {
        //Given
        ProjectRequest projectRequest = new ProjectRequest(null, null, "Awesome project description");

        //Then
        ResultActions response = mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(projectRequest)));
        //When
        response.andExpect(status().isBadRequest());
    }

    @Test
    public void givenExistingProjects_whenFindAllProjects_thenAllProjectsAreReturned() throws Exception {
        // Given
        ProjectResponse projectResponse1 = new ProjectResponse(1L, "Project 1", "Project 1 description");
        ProjectResponse projectResponse2 = new ProjectResponse(2L, "Project 2", "Project 2 description");
        List<ProjectResponse> projectResponseList = Arrays.asList(projectResponse1, projectResponse2);

        given(service.findAll()).willReturn(projectResponseList);

        // Then
        ResultActions response = mockMvc.perform(get("/api/projects"));

        // When
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(projectResponseList.size()));
    }

    @Test
    public void givenNonExistingProjects_whenFindAllProjects_thenNoContentIsReturned() throws Exception {
        // Given
        List<ProjectResponse> projectResponseList = Collections.emptyList();
        given(service.findAll()).willReturn(projectResponseList);

        // Then
        ResultActions response = mockMvc.perform(get("/api/projects"));

        // When
        response.andExpect(status().isNoContent());
    }

    @Test
    public void givenValidProjectRequest_whenUpdateProject_thenUpdatedProjectIsReturned() throws Exception {
        // Given
        ProjectRequest projectRequest = new ProjectRequest(1L, "Project name updated", "Project description updated");
        ProjectResponse projectResponse = new ProjectResponse(1L, "Project name updated", "Project description updated" );
        given(service.update(any(Long.class), any(ProjectRequest.class))).willReturn(projectResponse);
        // When
        ResultActions response = mockMvc.perform(put("/api/projects/{id}", projectRequest.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(projectRequest)));

        // Then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(projectResponse.getId().toString()))
                .andExpect(jsonPath("$.name").value(projectResponse.getName().toString()))
                .andExpect(jsonPath("$.description").value(projectResponse.getDescription().toString()));
    }



}
