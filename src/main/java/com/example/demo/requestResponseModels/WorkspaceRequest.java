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
public class WorkspaceRequest {
    private Long id;
    private Long userId;
    private String name;
    private Visibility visibility;

    public WorkspaceRequest(Workspace workspace){
        id = workspace.getId();
        userId = workspace.getUserId().getId();
        name = workspace.getName();
        visibility = workspace.getVisibility();
    }
}
