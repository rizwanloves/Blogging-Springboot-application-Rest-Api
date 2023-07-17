package com.codewithrizwan.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrizwan.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
