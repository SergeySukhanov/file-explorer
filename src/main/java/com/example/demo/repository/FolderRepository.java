package com.example.demo.repository;

import com.example.demo.models.Folder;
import com.example.demo.models.Workspace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends CrudRepository<Folder, Long> {

    Optional<Folder> findFolderByNameAndWorkspaceIdAndAndParentFolderId(String name, Workspace workspaceId,Folder parentFolderId);

    Optional<Folder> findFolderByIdAndWorkspaceId(Long id, Workspace workspaceId);




    Optional<Folder> findByName(String name);
}
