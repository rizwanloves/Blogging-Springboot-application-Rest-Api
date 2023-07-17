package com.codewithrizwan.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrizwan.blog.entities.Category;

import com.codewithrizwan.blog.entities.Post;
import com.codewithrizwan.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
//	void save(Comment comment);
}
