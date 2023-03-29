package com.example.demo.repository;

import com.example.demo.datatypes.Folder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends CrudRepository<Folder, String> {
}
