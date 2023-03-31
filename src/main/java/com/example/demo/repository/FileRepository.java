package com.example.demo.repository;

import com.example.demo.models.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, String> {

}
