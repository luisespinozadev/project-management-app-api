package dev.luisespinoza.pma.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class ProjectIntegrationTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ProjectRepository projectRepository;


    @Test
    public void testGetProjectById () throws Exception {
        // Arrange
        Project project = new Project();
        project.setName("New project");
        project.setDescription("Building software");
        project.setCreatedDate(LocalDateTime.now());

        Project savedProject = projectRepository.save(project);
        Long projectId = savedProject.getId();

        // Act
        ResponseEntity<Project> foundProject = testRestTemplate.getForEntity("/api/projects/" + projectId, Project.class);

        // Assert
        assertEquals(HttpStatus.OK, foundProject.getStatusCode());
    }



}
