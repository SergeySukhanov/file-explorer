package com.example.demo.models;




public class InformationFile {
    private String id;
    private String name;
    private String folderId;

    private boolean isFolder;
    private String text;

    public InformationFile(){
    }

    public InformationFile(File file){
        id = file.getId();
        name = file.getName();
        folderId = file.getParentFolderId();
        isFolder = false;
        text = file.getContent();
    }

    public InformationFile(Folder folder){
        id = folder.getId();
        name = folder.getName();
        folderId = folder.getParentFolderId();
        isFolder = true;
        text = null;
    }

    //getter-setter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
