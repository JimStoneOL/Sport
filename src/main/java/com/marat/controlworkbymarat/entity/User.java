package com.marat.controlworkbymarat.entity;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "email")
		})
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	@Column(nullable = false,length = 100)
	private String fio;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	private LocalDateTime lastAuth;
	private boolean authType;
	@OneToMany(mappedBy = "user")
	private List<Order> orderList;

	public User() {
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
