package com.example.demo.Controller;

import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.models.InformationFile;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FolderRepository folderRepository;

    @PostMapping("/file")
    public void createFile(@RequestBody InformationFile informationFile){
        if(informationFile.isFolder()){
            folderRepository.save(new Folder(informationFile));
        }else{
            fileRepository.save(new File(informationFile));
        }
    }

    @GetMapping("/file")
    public ArrayList<InformationFile> getAllFiles(){
        ArrayList<InformationFile> list = new ArrayList<>();
        for(File file:fileRepository.findAll()){
            list.add(new InformationFile(file));
        }
        for(Folder folder : folderRepository.findAll()){
            list.add(new InformationFile(folder));
        }
        return list;
    }

}
