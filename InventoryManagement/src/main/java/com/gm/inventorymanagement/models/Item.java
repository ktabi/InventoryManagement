package com.gm.inventorymanagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="items")
public class Item {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(unique = true)
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotNull
	@Min(0)
	private Integer price;
	
	@NotNull
	@Min(0)
	private Integer quantity;
	
	@Column(nullable = true, length = 64)
	private String image;
	
	
	
	 // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    // other getters and setters removed for brevity
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    
    
//=============================
//    Many To One
//=============================
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    
    
//=============================
//    Constructors
//=============================
    public Item() {}
    
    
    
 public Item(@NotEmpty String name, @NotEmpty String description, @NotNull @Min(0) Integer price,
		@NotNull @Min(0) Integer quantity, String image, User user) {
	super();
	this.name = name;
	this.description = description;
	this.price = price;
	this.quantity = quantity;
	this.image = image;
	this.user = user;
}
//=============================
//  Getters and Setters
//=============================
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getPrice() {
	return price;
}
public void setPrice(Integer price) {
	this.price = price;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public Date getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}
public Date getUpdatedAt() {
	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
 

	
    
    
    
    
    
    
	
}
