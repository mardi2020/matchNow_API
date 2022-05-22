package com.example.matchnow.skillStack;

import java.util.List;
import java.util.Optional;

public interface StackRepository {

    List<SkillStack> findById(long id);

    List<SkillStack> findAll(String email);

    List<SkillStack> findByName(String name);

    void addMySkill(String stackName, Long userId);
}
