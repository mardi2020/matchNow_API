package com.example.matchnow.skillStack;


import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StackService {

    private final StackRepository stackRepository;

    private final UserRepository userRepository;

    @Transactional
    public void addMySkill(List<AddSkillStackDTO> stackList, String email){
        Long user = userRepository.findByEmail(email).get(0).getUserId();
        for (AddSkillStackDTO skillStack : stackList) {
            stackRepository.addMySkill(skillStack.getStackName(), user);
        }
    }
}

