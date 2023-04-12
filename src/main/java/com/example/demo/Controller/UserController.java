package com.example.demo.Controller;

import com.example.demo.repository.UserRepository;
import com.example.demo.requestResponseModels.UserInformationResponse;
import com.example.demo.services.UserInformationService;
import com.example.demo.services.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserInformationService userService;

    @Autowired
    WorkspaceService workspaceService;

    @GetMapping("/user")
    public ResponseEntity getUserInformation(Authentication authentication){
        return ResponseEntity.ok(
                new UserInformationResponse(
                        userService.getUserByEmail(
                                authentication.getName()),
                                workspaceService.getAllWorkspaces(authentication.getName())
                ));
    }

}
