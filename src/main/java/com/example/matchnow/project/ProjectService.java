package com.example.matchnow.project;


import com.example.matchnow.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public void uploadProjectPost(UploadProjectDTO projectDTO) {
        Project projectEntity = new Project();
        User user = new User();
        user.setUsername(projectDTO.getUser().getUsername());
        user.setUserId(projectDTO.getUser().getUserId());

        projectEntity.setMainText(projectDTO.getMainText());
        projectEntity.setTitle(projectDTO.getTitle());
        projectEntity.setWriter(user);
        projectEntity.setInputImage(projectDTO.getImage());
        projectEntity.setWantCnt(projectDTO.getWantCnt());
        projectEntity.setNowPeopleCnt(1);
        projectEntity.setState(State.RECRUITING);

        projectRepository.uploadProjectPost(projectEntity);
    }

    public List<PostedProjectDTO> findAll() {
        List<Project> projectList = projectRepository.findAll();

        List<PostedProjectDTO> postedProjectDTO = new ArrayList<>();
        for (Project project : projectList) {
            postedProjectDTO.add(modelMapper.map(project, PostedProjectDTO.class));
        }

        return postedProjectDTO;
    }

    @Transactional
    public void updateProjectPost(UpdateProjectDTO updateProjectDTO) {
        Project projectEntity = new Project();

        projectEntity.setProjectId(updateProjectDTO.getProjectId());
        projectEntity.setTitle(updateProjectDTO.getTitle());
        projectEntity.setMainText(updateProjectDTO.getMainText());
        projectEntity.setInputImage(updateProjectDTO.getImage());
        projectEntity.setState(updateProjectDTO.getState());

        projectRepository.updateProjectPost(projectEntity);
    }

    @Transactional
    public void deleteProjectPost(Long id) {
        projectRepository.deleteProjectPost(id);
    }
}
