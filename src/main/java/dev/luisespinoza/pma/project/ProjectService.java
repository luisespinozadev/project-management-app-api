package dev.luisespinoza.pma.project;

import dev.luisespinoza.pma.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public ProjectResponse findById(Long id) throws Exception {
        return null;
        /*
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID " + id));
         */
    }

    public Long create(ProjectRequest request) throws Exception {
        return null;
    }

    public List<ProjectResponse> findAll() throws Exception {
        return null;
    }

    public ProjectResponse update(Long id, ProjectRequest request) throws Exception {
        return null;
    }
}
