package com.fcmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
