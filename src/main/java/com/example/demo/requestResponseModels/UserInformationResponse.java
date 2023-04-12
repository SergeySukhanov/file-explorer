package com.example.demo.requestResponseModels;

import com.example.demo.enums.Role;
import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private List<WorkspaceRequestResponse> workspaces;


    public UserInformationResponse(User user, List<WorkspaceRequestResponse> workspaces){
        id = user.getId();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        role = user.getRole();
        this.workspaces = workspaces;
    }

}
