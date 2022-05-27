package com.example.matchnow.project;


import com.example.matchnow.user.User;
import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    @Transactional
    public String uploadProjectPost(UploadProjectDTO projectDTO, String email) {
        User userInfo = userRepository.findByEmail(email).get(0);

        projectRepository.save(projectDTO.toEntity(userInfo));

        return userInfo.getUsername();
    }

    public List<PostedProjectDTO> findAll() {
        List<Project> projectList = projectRepository.findAll();

        List<PostedProjectDTO> postedProjectDTO = new ArrayList<>();
        for (Project project : projectList) {
            postedProjectDTO.add(new PostedProjectDTO(project));
        }

        return postedProjectDTO;
    }

    @Transactional
    public void updateProjectPost(Long id, UpdateProjectDTO updateProjectDTO) {
        Project entity = projectRepository.findById(id).orElse(null);

        if(entity == null)
            throw new NoSuchElementException("해당 게시글이 존재하지 않습니다.");

        entity.setUpdateProject(updateProjectDTO.getTitle(),
                updateProjectDTO.getMainText(),
                updateProjectDTO.getImage());

        projectRepository.save(entity);

    }

    @Transactional
    public void deleteProjectPost(Long id) {
        projectRepository.deleteProjectPost(id);
    }


    public DetailedProjectDTO detailProjectPost(Long id) {
        List<Project> projectList = projectRepository.detailProjectPost(id);
        if (projectList == null) {
            throw new IllegalStateException("해당 페이지가 존재하지 않습니다.");
        } else {
            Project source = projectList.get(0);
            return new DetailedProjectDTO(source);
        }
    }

    @Transactional
    public void changePostState(Long id, int state) {
        projectRepository.changePostState(id, state);
    }
}
