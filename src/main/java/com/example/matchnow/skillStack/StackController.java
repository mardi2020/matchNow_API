package com.example.matchnow.skillStack;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StackController {

    private final StackService stackService;

    @PostMapping(value="/skill")
    public ResponseEntity<?> addSkillStack(@RequestBody List<AddSkillStackDTO> stackDTOList, Principal principal) {
        ResponseEntity<?> responseEntity;
        try {

            stackService.addMySkill(stackDTOList, principal.getName());
            responseEntity = new ResponseEntity<>("기술 등록 성공", HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<>("기술 등록에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}
