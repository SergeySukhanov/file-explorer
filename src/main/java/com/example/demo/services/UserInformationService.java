package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WorkspaceRepository;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    public User getUserByEmail(String userEmail){
        Optional<User> user = userRepository.findByEmail(userEmail);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    public Workspace authorizeUserForWorkspace(String workspaceName, String userName){
        User user = userRepository.findByEmail(userName).get();
        Optional<Workspace> workspace = workspaceRepository.findByUserIdAndName(user, workspaceName);
        if(workspace.isPresent())
            return workspace.get();
        else
            return null;
    }

    public Workspace authorizeUserForWorkspace(Long workspaceId, String userName){
        User user = userRepository.findByEmail(userName).get();
        Optional<Workspace> workspace = workspaceRepository.findById(workspaceId);
        if(workspace.isPresent())
            return  workspace.get();
        else
            return null;
    }

    public Workspace authorizeUserForWorkspace(Workspace workspace, String userName){
        User user = userRepository.findByEmail(userName).get();
        if(workspace==null)
            return null;

        if(workspace.getUserId().equals(user))
            return workspace;
        else
            return null;
    }


}
