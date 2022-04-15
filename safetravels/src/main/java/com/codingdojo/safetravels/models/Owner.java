package com.codingdojo.safetravels.models;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="owners")
public class Owner {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Size(min = 2, max = 200, message="The First name must be over 2 charaters long")
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 200, message="The Last name must be over 2 charaters long")
	private String lastName;
	
	
	
//  Creating the One to many relationship with expense class
	
	@OneToMany(mappedBy="owner", fetch = FetchType.LAZY)
    private List<Expense> expenses;
	
	
	
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
    
    
    
    public Owner() {
		
	}
	public Owner(
			@NotNull @Size(min = 2, max = 200, message = "The First name must be over 2 charaters long") String firstName,
			@NotNull @Size(min = 2, max = 200, message = "The Last name must be over 2 charaters long") String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Owner(Long id,
			@NotNull @Size(min = 2, max = 200, message = "The First name must be over 2 charaters long") String firstName,
			@NotNull @Size(min = 2, max = 200, message = "The Last name must be over 2 charaters long") String lastName,
			List<Expense> expenses, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.expenses = expenses;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
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
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
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
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	    }
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}
