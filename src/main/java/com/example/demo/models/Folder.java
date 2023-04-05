package com.example.demo.models;

import com.example.demo.requestResponseModels.FileRequestResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="Folder")
@Table(name  = "folders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "workspace_id"
    )
    private Long workspaceId;

    //TODO Setup ForeignKey with OneToMany Relationship:
    //-Do Research beforehand
    @Column(
            name = "parent_folder_id"
    )
    private Long parentFolderId;

    @Column(
            name = "name",
            columnDefinition = "TEXT"
    )
    private String name;

    // constructors
//    public Folder(){
//        this.parentFolderId = "Home";
//    }
//
//    public Folder(String name) {
//        this.name = name;
//        this.parentFolderId = "Home";
//    }
//
//    public Folder(String parentFolderId, String name) {
//        this.parentFolderId = parentFolderId;
//        this.name = name;
//    }
//
    @Deprecated
    public Folder(FileRequestResponse infoFile){
        id = 0L;
        parentFolderId = infoFile.getFolderId();
        name = infoFile.getName();
        workspaceId = infoFile.getWorkspaceId();
    }

//    public Folder(FileRequest request){
//        name =
//    }

    //getter-setter


//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getParentFolderId() {
//        return parentFolderId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setParentFolderId(String parentFolderId) {
//        this.parentFolderId = parentFolderId;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    //methods
}
