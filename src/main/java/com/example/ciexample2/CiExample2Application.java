package com.example.ciexample2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class CiExample2Application {

	public static void main(String[] args) {
		SpringApplication.run(CiExample2Application.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}


@RestController
@RequestMapping("/api/v1")
class RandController  {


	@Autowired
	private UserService userService;
	@GetMapping()
	public String sayHello() {
		return "hello welcome";
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return this.userService.getAll();
	}


	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		return this.userService.getById(id);
	}
}

@Service
class UserService {

	@Autowired
	private UserRepo repo;


	public List<User> getAll() {
		return this.repo.findAll();
	}

	public User getById(int id) {
		return this.repo.findById(id);
	}
}


@Repository
class UserRepo {
	private final List<User> users;

	public UserRepo() {
		this.users  = IntStream.rangeClosed(1,5).mapToObj(i -> new User(i, "name" + i)).collect(Collectors.toList());
	}

	public List<User> findAll() {
		return this.users;
	}

	public User findById(int id) {
		return this.users.stream().filter( u -> u.getId() == id).findFirst().orElse(null);
	}

}


class User {
	private int id;
	private String name;
	public User() {}
	public User(int id, String name)  {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

