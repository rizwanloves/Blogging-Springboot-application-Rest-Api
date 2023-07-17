package com.codewithrizwan.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrizwan.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
