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

    @GetMapping("/file")
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

}
