package com.example.demo.repository;

import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.models.Workspace;
import org.hibernate.jdbc.Work;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findFileByNameAndWorkspaceIdAndParentFolderId(String name, Workspace workspaceId, Folder parentFolderId);

    Optional<File> findByName(String name);

    Iterable<File> findAllByParentFolderId(Folder parentFolderId);
    Iterable<File> findAllByWorkspaceId(Workspace workspaceId);

}
