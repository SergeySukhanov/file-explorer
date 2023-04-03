package com.example.demo.models;

import jakarta.persistence.*;

//TODO refactor code using lombok

@Entity(name="Folder")
@Table(name  = "folders")
public class Folder {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //TODO Setup ForeignKey with OneToMany Relationship:
    //-Do Research beforehand
    private String parentFolderId;

    private String name;

    // constructors
    public Folder(){
        this.parentFolderId = "Home";
    }

    public Folder(String name) {
        this.name = name;
        this.parentFolderId = "Home";
    }

    public Folder(String parentFolderId, String name) {
        this.parentFolderId = parentFolderId;
        this.name = name;
    }

    public Folder(InformationFile infoFile){
        id = infoFile.getId();
        parentFolderId = infoFile.getFolderId();
        name = infoFile.getName();

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

    public String getName() {
        return name;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    //methods
}
