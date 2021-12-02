package com.springcraft.springcraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcraft.springcraft.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
