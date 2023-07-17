 package com.codewithrizwan.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithrizwan.blog.entities.Category;
import com.codewithrizwan.blog.entities.Post;
import com.codewithrizwan.blog.entities.User;
import com.codewithrizwan.blog.exceptions.ResourceNotFoundException;
import com.codewithrizwan.blog.payloads.PostDto;
import com.codewithrizwan.blog.payloads.PostResponse;
import com.codewithrizwan.blog.repositories.CategoryRepo;
import com.codewithrizwan.blog.repositories.PostRepo;
import com.codewithrizwan.blog.repositories.UserRepo;
import com.codewithrizwan.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user Id",userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}
	
	
	

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	
	
	
	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
	}
	
	
	

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	
	
	
	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> allPost = pagePost.getContent();
		List<PostDto> postDtos = allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLast(pagePost.isLast());
		
		return postResponse;
	}

	
	
	
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	
	
	@Override
	public List<PostDto> getPostsByUser(Integer UserId) {
		User user = this.userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", UserId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postDtos;
	}

	
	
	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post) ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
