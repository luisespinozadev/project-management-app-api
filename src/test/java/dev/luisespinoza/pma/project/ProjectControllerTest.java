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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        ResultActions response = mockMvc.perform(get("/api/projects/" + expectedProject.getId()));

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
        ResultActions response = mockMvc.perform(get("/api/projects/" + id));

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

}
