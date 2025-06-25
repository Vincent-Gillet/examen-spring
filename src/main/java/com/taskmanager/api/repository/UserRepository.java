package com.taskmanager.api.repository;

import com.taskmanager.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface pour gérer les entités User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}