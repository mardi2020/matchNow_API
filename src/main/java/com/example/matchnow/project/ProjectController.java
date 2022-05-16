package com.example.matchnow.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            responseEntity = new ResponseEntity<>("모든 게시글을 찾지 못했습니다.", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping(value="project")
    public ResponseEntity<?> uploadProjectPost(@RequestBody UploadProjectDTO uploadProjectDTO) {
        ResponseEntity<?> responseEntity;
        try {
            projectService.uploadProjectPost(uploadProjectDTO);
            responseEntity = new ResponseEntity<>(uploadProjectDTO.getUser().getUsername() + "님의 "
                    + uploadProjectDTO.getTitle() + "글 등록 성공!", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>("게시글을 등록하지 못했습니다.", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
