package com.example.matchnow.project;

import com.example.matchnow.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    public void uploadProjectPost(UploadProjectDTO projectDTO) {
        Project projectEntity = modelMapper.map(projectDTO, Project.class);
        projectRepository.uploadProjectPost(projectEntity);
    }

    public List<PostedProjectDTO> findAll() {
        List<Project> projectList = projectRepository.findAll();

        List<PostedProjectDTO> postedProjectDTO = new ArrayList<>();
        for (Project project : projectList) {
            System.out.println("project = " + project);
            postedProjectDTO.add(modelMapper.map(project, PostedProjectDTO.class));
        }

        for (PostedProjectDTO projectDTO : postedProjectDTO) {
            System.out.println("projectDTO = " + projectDTO);
        }
        return postedProjectDTO;
    }
}
