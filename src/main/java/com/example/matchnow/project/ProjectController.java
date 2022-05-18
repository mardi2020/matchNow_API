package com.example.matchnow.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value="project")
    public ResponseEntity<?> updateProjectPost(@RequestBody UpdateProjectDTO updateProjectDTO) {
        ResponseEntity<?> responseEntity;

        try {
            projectService.updateProjectPost(updateProjectDTO);
            responseEntity = new ResponseEntity<>("게시글을 성공적으로 수정했습니다.", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>("게시글 수정에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @DeleteMapping(value="project/{id}")
    public ResponseEntity<?> deleteProjectPost(@PathVariable Long id){
        ResponseEntity<?> responseEntity;

        try {
            projectService.deleteProjectPost(id);
            responseEntity = new ResponseEntity<>("게시글을 성공적으로 삭제했습니다.", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>("게시글 삭제에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @GetMapping(value="project/{id}")
    public ResponseEntity<?> detailProjectPost(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;

        try {
            DetailedProjectDTO detailedProjectDTO = projectService.detailProjectPost(id);
            responseEntity = new ResponseEntity<>(detailedProjectDTO, HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>("게시글 가져오기에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}
