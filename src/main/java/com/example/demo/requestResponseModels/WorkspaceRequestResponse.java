package com.example.demo.requestResponseModels;


import com.example.demo.enums.Visibility;
import com.example.demo.models.Workspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceRequestResponse {
    private Long workspaceId;
    private Long userId;
    private String name;
    private Visibility visibility;

    public WorkspaceRequestResponse(Workspace workspace){
        workspaceId = workspace.getId();
        userId = workspace.getUserId().getId();
        name = workspace.getName();
        visibility = workspace.getVisibility();
    }
}
