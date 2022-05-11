package com.example.matchnow.skillStack;

import java.util.List;
import java.util.Optional;

public interface StackRepository {

    Optional<SkillStack> findById(long id);

    List<SkillStack> findAll();

    Optional<SkillStack> findByName(String name);
}
