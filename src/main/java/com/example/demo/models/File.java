package com.example.demo.models;

import jakarta.persistence.*;

@Entity(name="File")
@Table(name = "TextFiles")
public class File {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    String id;

    private String parentFolderId;

    private String name;

    private String content;

    //constructor

    public File() {
        this.parentFolderId = "Home";
    }

    public File(String name) {
        this.name = name;
        this.parentFolderId = "Home";
    }

    public File(String name, String content){
        this.name = name;
        this.content = content;
        this.parentFolderId = "Home";
    }

    public File(String parentFolderId, String name, String content) {
        this.parentFolderId = parentFolderId;
        this.name = name;
        this.content = content;
    }

    public File(InformationFile infoFile){
        id = infoFile.getId();
        parentFolderId = infoFile.getFolderId();
        name = infoFile.getName();
        content = infoFile.getText();
    }

    //getter-setter


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
