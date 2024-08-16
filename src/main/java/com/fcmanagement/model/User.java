package com.fcmanagement.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
    private int id;
	
    private String username;
    
    private String companyName;
    
    private String email;
    
    private String mobile;
    
    private String password;
    
    private String status;
    
    @OneToMany(mappedBy = "user")
	private List<Product> products = new ArrayList<Product>();
    
    @OneToMany(mappedBy ="user")
	private List<Category> categories = new ArrayList<Category>();
    
    @OneToMany(mappedBy ="user")
	private List<Brand> brand = new ArrayList<Brand>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "', mobile='" + mobile + "'}";
    }
}
