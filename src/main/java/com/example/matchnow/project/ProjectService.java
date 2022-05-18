package com.example.matchnow.project;


import com.example.matchnow.user.User;
import com.example.matchnow.user.UserRepository;
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

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public String uploadProjectPost(UploadProjectDTO projectDTO, String email) {
        Project projectEntity = new Project();
        User userInfo = userRepository.findByEmail(email).get(0);

        projectEntity.setMainText(projectDTO.getMainText());
        projectEntity.setTitle(projectDTO.getTitle());
        projectEntity.setWriter(userInfo);
        projectEntity.setInputImage(projectDTO.getImage());
        projectEntity.setWantCnt(projectDTO.getWantCnt());
        projectEntity.setNowPeopleCnt(1);
        projectEntity.setState(State.RECRUITING);

        projectRepository.uploadProjectPost(projectEntity);

        return userInfo.getUsername();
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
    public void updateProjectPost(Long id, UpdateProjectDTO updateProjectDTO) {
        Project projectEntity = new Project();
        projectEntity.setProjectId(id);
        projectEntity.setTitle(updateProjectDTO.getTitle());
        projectEntity.setMainText(updateProjectDTO.getMainText());
        projectEntity.setInputImage(updateProjectDTO.getImage());
        projectRepository.updateProjectPost(projectEntity);
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
            DetailedProjectDTO detailedProjectDTO = new DetailedProjectDTO();
            detailedProjectDTO.setProjectId(source.getProjectId());
            detailedProjectDTO.setTitle(source.getTitle());
            detailedProjectDTO.setMainText(source.getMainText());
            detailedProjectDTO.setUser(source.getUser());
            detailedProjectDTO.setInputImage(source.getInputImage());
            detailedProjectDTO.setNowPeopleCnt(source.getNowPeopleCnt());
            detailedProjectDTO.setWantCnt(source.getWantCnt());
            detailedProjectDTO.setState(source.getState());
            return detailedProjectDTO;
        }
    }

    @Transactional
    public void changePostState(Long id, int state) {
        projectRepository.changePostState(id, state);
    }
}
