package com.example.demo.repository;

import com.example.demo.models.Workspace;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {
    Optional<Workspace> findByUserId(Long userId);
}
