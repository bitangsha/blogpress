package com.blogpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogpress.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
