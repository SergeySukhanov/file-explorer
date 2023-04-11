package com.example.demo.Controller;

import com.example.demo.requestResponseModels.WorkspaceRequest;
import com.example.demo.services.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;

    @PostMapping("/workspace")
    public ResponseEntity createWorkspace(@RequestBody WorkspaceRequest request, Authentication authentication){
        return workspaceService.createWorkspace(request, authentication.getName());
    }

    @GetMapping("/workspace")
    public ResponseEntity getAllWorkspaces(Authentication authentication){
        return workspaceService.getAllWorkspaces(authentication.getName());
    }

    @GetMapping("/workspace/{workspaceName}")
    public ResponseEntity getWorkspaceByName(@PathVariable String workspaceName, Authentication authentication){
        return workspaceService.getWorkspaceByName(workspaceName, authentication.getName());
    }
}
