package dev.luisespinoza.pma.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @MockitoBean
    ProjectService projectService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetProjectById() throws Exception {
        // Arrange
        Project project = new Project();
        project.setId(1L);
        project.setName("New project");
        project.setDescription("Building software");
        project.setCreatedDate(LocalDateTime.now());

        when(projectService.findById(1L)).thenReturn(project);

        // Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/projects/" + project.getId()));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(project.getName()));
    }

}
