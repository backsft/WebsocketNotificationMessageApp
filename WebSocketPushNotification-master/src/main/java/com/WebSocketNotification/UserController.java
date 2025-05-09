package com.WebSocketNotification;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// Register
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("Username already exists");
		}
		userRepository.save(user);
		return ResponseEntity.ok("User registered successfully");
	}

	// Login
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		Optional<User> existingUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (existingUser.isPresent()) {
			return ResponseEntity.ok(existingUser.get()); // return user info (for simplicity)
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}
}
