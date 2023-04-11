package com.example.demo.repository;

import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.models.Workspace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findFileByNameAndWorkspaceIdAndParentFolderId(String name, Workspace workspaceId, Folder parentFolderId);

    Optional<File> findByName(String name);

}
