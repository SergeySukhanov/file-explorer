package com.example.demo.repository;

import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {
    Optional<Workspace> findByUserId(User userId);
    Optional<Workspace> findByUserIdAndName(User userId, String name);

    Optional<Workspace> findByIdAndUserId(Long id, User userId);

    Iterable<Workspace> findAllByUserId(User userId);
}
