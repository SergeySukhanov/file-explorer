package com.example.demo.Controller;

import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.requestResponseModels.FileRequestResponse;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.FolderRepository;
import com.example.demo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class FileRestController {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FolderRepository folderRepository;

    @Autowired
    FileService fileService;

    @PostMapping("/file")
    public ResponseEntity createFile(@RequestBody FileRequestResponse fileRequest, Authentication authentication){
        return fileService.createFileOrFolder(fileRequest, authentication.getName());
//
    }


    //TODO this endpoint should be changed, so that it only returns the Files and Folders connected to the current user
    @GetMapping("/files")
    public ResponseEntity getAllFiles(Authentication authentication){
        ArrayList<FileRequestResponse> list = new ArrayList<>();
        for(File file:fileRepository.findAll()){
            list.add(new FileRequestResponse(file));
        }
        for(Folder folder : folderRepository.findAll()){
            list.add(new FileRequestResponse(folder));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/files/{workspaceName}")
    public ResponseEntity getAllFilesInWorkspace(Authentication authentication, @PathVariable String workspaceName){
        List<FileRequestResponse> list = fileService.getAllFilesInWorkspace(workspaceName, authentication.getName());
        if(list == null)
            return ResponseEntity.badRequest().body("There is either no Workspace with this name or you do not have permission to access it!");
        else
            return ResponseEntity.ok(list);
    }

    @GetMapping("/folder/{folderId}")
    public ResponseEntity getChildrenOfFolder(Authentication authentication, @PathVariable Long folderId){
        List<FileRequestResponse> list = fileService.getChildrenOfFolder(folderId, authentication.getName());
        if(list == null)
            return ResponseEntity.badRequest().body(
                    "There is either no folder with this name or you do not have permission to access it!"
            );
        else
            return ResponseEntity.ok(list);
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity getFileInformation(Authentication authentication, @PathVariable Long fileId){
        FileRequestResponse file = fileService.getFileInformation(fileId, authentication.getName());
        if(file == null)
            return ResponseEntity.badRequest().body(
                    "There is either no file with this name or you do not have permission to access it!"
            );
        else
            return ResponseEntity.ok(file);
    }

}
