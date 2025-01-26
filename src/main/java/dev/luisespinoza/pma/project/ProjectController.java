package dev.luisespinoza.pma.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService service;

    @PostMapping
    public ResponseEntity<Long> create(
            @Validated @RequestBody ProjectRequest request
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }


}
