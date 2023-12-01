package com.gptp.jirawebapp.components.project;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/project")
@AllArgsConstructor
public class ProjectController {
    private final ProjectRepository repository;
    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.getProjectById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getByNameSimilarity(@RequestParam String like) {
        List<ProjectDto> projects = repository.findByNameSimilarity(like);
        return ResponseEntity.ok(projects);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectDto project) {
        ProjectDto saved = projectService.createProject(project);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProjectDto data) {
        ProjectDto saved = projectService.updateProject(id, data);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<?> getUsers(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectUsers(id));
    }

    @PostMapping("/{id}/user")
    public ResponseEntity<?> addUser(@PathVariable Long id, @RequestBody ProjectAddUserData data) {
        projectService.addUserToProject(id, data);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{projectId}/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long projectId, @PathVariable Long userId) {
        return ResponseEntity.ok(projectService.removeUserFromProject(projectId, userId));
    }
}
