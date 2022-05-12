package com.example.matchnow.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(value="/project")
    public ResponseEntity<?> findAllProjectPost() {
        ResponseEntity<?> responseEntity;

        try {
            List<PostedProjectDTO> projects = projectService.findAll();

            responseEntity = new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
