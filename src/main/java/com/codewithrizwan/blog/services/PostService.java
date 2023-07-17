package com.codewithrizwan.blog.services;

import java.util.List;

import com.codewithrizwan.blog.payloads.PostDto;
import com.codewithrizwan.blog.payloads.PostResponse;

public interface PostService {
	
	//CREATE
	PostDto createPost(PostDto postDto , Integer userId,Integer categoryId);
	
	//UPDATE
	PostDto updatePost(PostDto postDto,Integer postId);
	
	
	//DELETE
	void deletePost(Integer postId);
	
	
	//GET
	PostDto getPostById(Integer postId);
	
	//GETALL
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
	
	//GETALL POST BY CATEGORY
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//GETALL POST BY USER
	List<PostDto> getPostsByUser(Integer UserId);
	
	//SEARCH POST
	List<PostDto> searchPosts(String keyword);

}
