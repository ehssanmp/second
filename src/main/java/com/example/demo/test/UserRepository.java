package com.example.demo.test;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
import com.example.demo.test.User;
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}