package com.example.matchnow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUserPOST(@RequestBody User user) {
        ResponseEntity<String> responseEntity;

        try {
            userService.join(user);
            String email = user.getEmail();

            responseEntity = new ResponseEntity<>(email + "님 회원 가입 성공!", HttpStatus.OK);

        } catch(Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
