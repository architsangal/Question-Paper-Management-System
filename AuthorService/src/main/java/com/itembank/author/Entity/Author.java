package com.itembank.author.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique=true, nullable=false)
	private String username;

	@Column(nullable = false)	
	private String password;

	@Column(unique=true, nullable=false)
	private String email;

	public int getId() { return id; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getEmail() { return this.email; }

	public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    
}
