package com.gptp.jirawebapp.components.user;

import com.gptp.jirawebapp.components.projectUser.ProjectUserDto;
import com.gptp.jirawebapp.components.projectUser.ProjectUserRepository;
import com.gptp.jirawebapp.utilities.JWT;
import com.gptp.jirawebapp.utilities.JWTContent;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserRepository repository;
    private final ProjectUserRepository projectUserRepository;
    private final JWT jwt;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto user) {
        UserDto storedUser = repository.findByEmail(user.getEmail());

        if (storedUser == null || !BCrypt.checkpw(user.getPassword(), storedUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        Long userId = storedUser.getId();
        JWTContent content = new JWTContent(userId);
        String token = jwt.encode(content);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/testToken")
    public ResponseEntity<?> testToken() {
        return ResponseEntity.ok(jwt.context());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<UserDto> user = repository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> get(@PathVariable String email) {
        UserDto user = repository.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<?> getByFullName(@RequestParam String like) { //TODO: change endpoint
        List<UserDto> users= repository.findByFullName(like);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserDto data) {
        UserDto user = repository.findById(id).orElseThrow();
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        UserDto saved = repository.save(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/projects")
    public ResponseEntity<?> listProjects() {
        Long id = jwt.context().getUserId();

        List<ProjectUserDto> projectUserRoles = projectUserRepository.findByUserId(id);

        List<?> projects = projectUserRoles.stream()
                .map(ProjectUserDto::getProject)
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<?> listUserProjects(@PathVariable Long id) {

        List<ProjectUserDto> projectUserRoles = projectUserRepository.findByUserId(id);

        List<?> projects = projectUserRoles.stream()
                .map(ProjectUserDto::getProject)
                .collect(Collectors.toList());

        return ResponseEntity.ok(projects);
    }
}
