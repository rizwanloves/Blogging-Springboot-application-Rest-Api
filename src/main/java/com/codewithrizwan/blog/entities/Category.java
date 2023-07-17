package com.codewithrizwan.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Categories")
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name = "Title",length = 100,nullable = false)
	private String categoryTitle;
	
	@Column(name = "Description")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
}
