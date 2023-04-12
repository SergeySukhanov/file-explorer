package com.example.demo.services;

import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.models.Workspace;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.FolderRepository;
import com.example.demo.repository.WorkspaceRepository;
import com.example.demo.requestResponseModels.FileRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<FileRequestResponse> getAllFilesInWorkspace(String workspaceName, String userName) {
        //authorize the user and get the WorkspaceId at the same time
        Workspace workspace = userInformationService.authorizeUserForWorkspace(workspaceName, userName);
        if(workspace==null)
            return null;

        List<FileRequestResponse> allFiles = new ArrayList<FileRequestResponse>();

        for(Folder folder: folderRepository.findAllByWorkspaceId(workspace)){
            allFiles.add(new FileRequestResponse(folder));
        }
        for(File file: fileRepository.findAllByWorkspaceId(workspace)){
            allFiles.add(new FileRequestResponse(file));
        }
        return allFiles;
    }

    public List<FileRequestResponse> getChildrenOfFolder(Long folderId, String userName) {
        Optional<Folder> parentFolder = folderRepository.findById(folderId);

        //Is there a folder with this id?
        if(parentFolder.isEmpty())
            return null;

        //Does the user have access to the workspace this folder belongs to?
        Workspace workspace = userInformationService.authorizeUserForWorkspace(parentFolder.get().getWorkspaceId(), userName);
        if(workspace==null)
            return null;

        List<FileRequestResponse> childrenOfFolder = new ArrayList<FileRequestResponse>();
        for(Folder folder: folderRepository.findAllByParentFolderId(parentFolder.get())){
            childrenOfFolder.add(new FileRequestResponse(folder));
        }
        for(File file : fileRepository.findAllByParentFolderId(parentFolder.get())){
            childrenOfFolder.add(new FileRequestResponse(file));
        }
        return childrenOfFolder;

    }

    public FileRequestResponse getFileInformation(Long fileId, String userName) {
        Optional<File> file = fileRepository.findById(fileId);
        //Is there a file with this id?
        if(file.isEmpty())
            return null;

        //Does the user have access to the workspace this file belongs to?
        Workspace workspace = userInformationService.authorizeUserForWorkspace(file.get().getWorkspaceId(),userName);
        if(workspace == null)
            return null;

        return new FileRequestResponse(file.get());
    }
}
