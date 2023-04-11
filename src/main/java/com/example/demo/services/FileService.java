package com.example.demo.services;

import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.models.Workspace;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.FolderRepository;
import com.example.demo.repository.WorkspaceRepository;
import com.example.demo.requestResponseModels.FileRequestResponse;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    @Autowired
    FolderRepository folderRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    UserInformationService userInformationService;


    public ResponseEntity createFileOrFolder(FileRequestResponse fileRequest, String userName) {
        //TODO validate the RequestBody
        if(fileRequest == null)
            return ResponseEntity.badRequest().body("The passed information is not valid");

        //checks if this user has a workspace with the specified name
        Optional<Workspace> workspace = workspaceRepository.findByIdAndUserId(fileRequest.getWorkspaceId(), userInformationService.getUserByEmail(userName));
        if(workspace.isEmpty())
            return ResponseEntity.badRequest().body("The workspace does not exist for this user!");


        //checks if the parentFolder exists in the specified workspace
        Optional<Folder> parentFolder = folderRepository.findFolderByIdAndWorkspaceId(fileRequest.getFolderId(), workspace.get());
        if(parentFolder.isEmpty())
            return ResponseEntity.badRequest().body("The parent Folder does not exist for this user!");


        //checks if a file or a folder is being created
        if(fileRequest.isFolder())
            return createFolder(workspace.get(), parentFolder.get(), fileRequest.getName());
        else if(!fileRequest.isFolder())
            return createFile(workspace.get(), parentFolder.get(), userName, fileRequest.getText());

        //TODO Exception handling, What errors could occur?
        return null;
    }

    //TODO finish createFolder
    public ResponseEntity createFolder(Workspace workspace, Folder parentFolder, String name) {
        if(folderRepository.findFolderByNameAndWorkspaceIdAndAndParentFolderId(name,workspace,parentFolder).isPresent())
            return ResponseEntity.badRequest().body("There already exists a folder with this name in the parent folder!");
        folderRepository.save(Folder.builder()
                        .id(0L)
                        .parentFolderId(parentFolder)
                        .workspaceId(workspace)
                        .name(name)
                .build());
        return ResponseEntity.ok(new FileRequestResponse(folderRepository.findFolderByNameAndWorkspaceIdAndAndParentFolderId(name,workspace,parentFolder).get()));
    }

    //TODO finish createFile
    public ResponseEntity createFile(Workspace workspace, Folder parentFolder, String name, String content) {
        if(fileRepository.findFileByNameAndWorkspaceIdAndParentFolderId(name, workspace, parentFolder).isPresent())
            return ResponseEntity.badRequest().body("There already exists a file with this name in the parent folder!");

        fileRepository.save(File.builder()
                        .content(content)
                        .id(0L)
                        .name(name)
                        .parentFolderId(parentFolder)
                        .workspaceId(workspace)
                .build());

        return ResponseEntity.ok(new FileRequestResponse(fileRepository.findFileByNameAndWorkspaceIdAndParentFolderId(name,workspace, parentFolder).get()));
    }
}
