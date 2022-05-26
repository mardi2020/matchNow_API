package com.example.matchnow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Transactional
    public void join(UserJoinDTO userJoinDTO) {
        validateDuplicateUser(userJoinDTO);
        if(!checkValidEmail(userJoinDTO.getEmail()))
            throw new IllegalStateException("올바르지 않는 email입니다.");

//        User user = new User();
//        user.setEmail(userJoinDTO.getEmail());
//        user.setPassword(passwordEncoder.encode(userJoinDTO.getPassword()));
//        user.setUsername(userJoinDTO.getUsername());

        userRepository.join(userJoinDTO.toEntity());
    }

    private void validateDuplicateUser(UserJoinDTO userJoinDTO) {
        List<User> users = userRepository.findByEmail(userJoinDTO.getEmail());
        if(!users.isEmpty())
            throw new IllegalStateException("이미 가입되어 있는 email입니다.");
    }

    private boolean checkValidEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
}
