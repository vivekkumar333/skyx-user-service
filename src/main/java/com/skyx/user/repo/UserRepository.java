package com.skyx.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyx.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}

