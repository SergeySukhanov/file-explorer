package com.example.demo.services;

import com.example.demo.enums.Visibility;
import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WorkspaceRepository;
import com.example.demo.requestResponseModels.WorkspaceRequestResponse;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FileService fileService;


    public ResponseEntity createWorkspace(WorkspaceRequestResponse request, String userName) {
        Optional<User> user = userRepository.findByEmail(userName);
        //Does a user with this id exist?
        if(user.isEmpty()){
            return ResponseEntity.internalServerError().body("There is no user corresponding to the Username in the JWT!");
        }

        //Does a workspace with this exact name and owner exist?
        if (workspaceRepository.findByUserIdAndName(user.get(), request.getName()).isPresent()){
            return ResponseEntity.badRequest().body("For this user there already exists a workspace with the specified name");
        }

        //save the Workspace
        workspaceRepository.save(Workspace.builder()
                .name(request.getName())
                .userId(user.get())
                .visibility(Visibility.PRIVATE)
                .build());

        //Return the data of the saved workspace
        Workspace workspace = workspaceRepository.findByUserIdAndName(user.get(),request.getName()).get();

        fileService.createFolder(workspace, null, "Home");

        return ResponseEntity.ok(new WorkspaceRequestResponse(workspace));
    }


    public List<WorkspaceRequestResponse> getAllWorkspaces(String userName) {
        Optional<User> user = userRepository.findByEmail(userName);

        List<WorkspaceRequestResponse> list = new ArrayList();
        for(Workspace workspace: workspaceRepository.findAllByUserId(user.get())){
            list.add(new WorkspaceRequestResponse(workspace));
        }
        return list;
    }

    //TODO return the workspace or a null pointer, but not a ResponseEntity
    public ResponseEntity getWorkspaceByName(String workspaceName, String userName) {
        User user = userRepository.findByEmail(userName).get();
        Optional<Workspace> workspace = workspaceRepository.findByUserIdAndName(user,workspaceName);
        if(workspace.isPresent())
            return ResponseEntity.ok(new WorkspaceRequestResponse(workspace.get()));
        else
            return ResponseEntity.badRequest().body("For this user there exists no workspace with this name!");
    }


}
