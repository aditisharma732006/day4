package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repo.MyRepo;

@RestController
public class UserController {

	@Autowired
	private MyRepo repository;
	
	@GetMapping("/getAllUser")
	public List<User> getAllUser() {
		return repository.findAll();
	}

	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable int id) {
		Optional<User> user=repository.findById(id);
		return user.get();
	}
	@PostMapping("/addUser")
	public String createUser(@RequestBody User user) {
		repository.save(user);
		return "user created ";
	}

	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable int id,@RequestBody User updatedUser) {
		Optional<User> optional = repository.findById(id);
		if(optional.isPresent()) {
			User user=optional.get();
			user.setName(updatedUser.getName());
			repository.save(user);
			return "user updated";
			
		}
		return "user not found";
	}
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		if (repository.existsById(id)) {
            repository.deleteById(id);
            return "user deleted successfully";
        }
        return "user not found";
	}
	
	@DeleteMapping("/deleteAllUser")
	public String deleteAllUser() {
		repository.deleteAll();
        return "All user deleted";
	}
	
}
