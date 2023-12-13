package net.codejava;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Category {
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 64)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Category> children = new HashSet<>();
	
	public Category(String name, Category parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public Category(String name) {
		this.name = name;
	}	

	public Category() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}
	
	public void addChild(Category children) {
		this.children.add(children);
	}
}
