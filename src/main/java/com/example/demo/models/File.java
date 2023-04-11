package com.example.demo.models;

import com.example.demo.requestResponseModels.FileRequestResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "file")
@Table(name = "text_files")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "workspace_id",
            referencedColumnName = "id"
    )
    private Workspace workspaceId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "parent_folder_id",
            referencedColumnName = "id"
    )
    private Folder parentFolderId;


    @Column(
            name = "file_name",
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "file_content",
            columnDefinition = "TEXT"
    )
    private String content;


    //constructor

//    public File() {
//        this.parentFolderId = "Home";
//    }
//
//    public File(String name) {
//        this.name = name;
//        this.parentFolderId = "Home";
//    }
//
//    public File(String name, String content){
//        this.name = name;
//        this.content = content;
//        this.parentFolderId = "Home";
//    }
//
//    public File(String parentFolderId, String name, String content) {
//        this.parentFolderId = parentFolderId;
//        this.name = name;
//        this.content = content;
//    }
//
    @Deprecated
    public File(FileRequestResponse infoFile){
        id = 0L;
        //parentFolderId = infoFile.getFolderId();
        name = infoFile.getName();
        content = infoFile.getText();
//        workspaceId = infoFile.getWorkspaceId();
    }

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
//    public void setParentFolderId(String parentFolderId) {
//        this.parentFolderId = parentFolderId;
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
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
