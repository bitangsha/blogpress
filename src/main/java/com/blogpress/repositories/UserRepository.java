package com.blogpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogpress.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
