package com.example.demo.requestResponseModels;


import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.models.Workspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileRequestResponse {
    private Long id;
    private String name;
    private Long workspaceId;
    private Long folderId;
    private boolean isFolder;
    private String text;


    //TODO rename FileRequestResponse to something like FileResponse and maybe change package
//    public FileRequestResponse(){
//    }

    public FileRequestResponse(File file){
        id = file.getId();
        name = file.getName();
        folderId = file.getParentFolderId().getId();
        workspaceId = file.getWorkspaceId().getId();
        isFolder = false;
        text = file.getContent();
    }

    public FileRequestResponse(Folder folder){
        id = folder.getId();
        name = folder.getName();
        folderId = (folder.getParentFolderId()!=null)?folder.getParentFolderId().getId():null;
        workspaceId = folder.getWorkspaceId().getId();
        isFolder = true;
        text = null;
    }

    //getter-setter

//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getFolderId() {
//        return folderId;
//    }
//
//    public void setFolderId(String folderId) {
//        this.folderId = folderId;
//    }
//
//    public boolean isFolder() {
//        return isFolder;
//    }
//
//    public void setFolder(boolean folder) {
//        isFolder = folder;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
}
