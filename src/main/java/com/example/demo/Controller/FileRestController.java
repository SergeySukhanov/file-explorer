package com.example.demo.Controller;

import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.requestResponseModels.FileRequestResponse;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class FileRestController {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FolderRepository folderRepository;

    @PostMapping("/file")
    public void createFile(@RequestBody FileRequestResponse fileRequest){
        if(fileRequest.isFolder()){
            folderRepository.save(new Folder(fileRequest));
        }else{
            fileRepository.save(new File(fileRequest));
        }
    }

    @GetMapping("/file")
    public ArrayList<FileRequestResponse> getAllFiles(){
        ArrayList<FileRequestResponse> list = new ArrayList<>();
        for(File file:fileRepository.findAll()){
            list.add(new FileRequestResponse(file));
        }
        for(Folder folder : folderRepository.findAll()){
            list.add(new FileRequestResponse(folder));
        }
        return list;
    }

}
