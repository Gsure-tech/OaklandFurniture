package com.decagon.OakLandv1be.repositries;

import com.decagon.OakLandv1be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
