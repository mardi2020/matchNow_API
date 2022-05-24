package com.example.matchnow.message;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(value = "/message")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageDTO sendMessageDTO, Principal principal) {
        ResponseEntity<?> responseEntity;

        try {
            List<String> sendTo = messageService.sendMessage(sendMessageDTO, principal.getName());
            responseEntity = new ResponseEntity<>(sendTo.get(0) + "님의 메세지가 " + sendTo.get(1) + "님께 전송 완료!", HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>(e + "메세지 전송 실패", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping(value="/message")
    public ResponseEntity<?> findAll(Principal principal) {
        ResponseEntity<?> responseEntity;

        try {
            String email = principal.getName();
            List<ReceivedMessageDTO> messages = messageService.findAll(email);
            responseEntity = new ResponseEntity<>(messages, HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>("오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping(value="/message/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;

        try {
            messageService.deleteMessage(id);
            responseEntity = new ResponseEntity<>("메시지를 삭제했습니다.", HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>("메시지 삭제에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @DeleteMapping(value="/message")
    public ResponseEntity<?> deleteMessageAll(Principal principal) {
        ResponseEntity<?> responseEntity;

        try {
            String email = principal.getName();
            messageService.deleteMessageAll(email);
            responseEntity = new ResponseEntity<>("모든 메시지를 삭제했습니다.", HttpStatus.OK);
        }catch (Exception e) {
            responseEntity = new ResponseEntity<>("메시지 삭제에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}
