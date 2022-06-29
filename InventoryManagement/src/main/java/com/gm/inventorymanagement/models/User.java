package com.gm.inventorymanagement.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

	@Entity
	@Table(name="users")
public class User {
	
//	@Size cannot validate a number and min and max cannot validate a string	
		
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotEmpty(message="First Name is required!")
	    @Size(min=2, max=30)
	    private String firstName;
	    
	    @NotEmpty(message="Last Name is required!")
	    @Size(min=2, max=30)
	    private String lastName;
	    
	    @NotEmpty(message="Email is required!")
	    @Email(message="Please enter a valid email!")
	    private String email;
	    
	    @NotEmpty(message="Password is required!")
	    @Size(min=8, max= 128, message="Password must be between 8 and 128 characters")
	    private String password;
	    
//	    Transient doesnt get persisted and stored back to db. it is just to confirm in the frontend
	    @Transient
	    @NotEmpty(message="Confirm Password is required!")
	    @Size(min=8, max=128)
	    private String confirm;
	    
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
	    
//========================================
//	    One to Many Relationship with Item
//=========================================
	    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	    private List<Item> items;
	    
//=============================
//	    Constructors
//=============================
	    public User() {}
	    
public User(@NotEmpty(message = "First Name is required!") @Size(min = 2, max = 30) String firstName,
				@NotEmpty(message = "Last Name is required!") @Size(min = 2, max = 30) String lastName,
				@NotEmpty(message = "Email is required!") @Email(message = "Please enter a valid email!") String email,
				@NotEmpty(message = "Password is required!") @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters") String password,
				@NotEmpty(message = "Confirm Password is required!") @Size(min = 8, max = 30) String confirm,
				List<Item> items) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.confirm = confirm;
			this.items = items;
		}

//=============================
//	    Getters and Setters
//=============================
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirm() {
	return confirm;
}
public void setConfirm(String confirm) {
	this.confirm = confirm;
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
public List<Item> getItems() {
	return items;
}
public void setItems(List<Item> items) {
	this.items = items;
}
		

}
