package com.matching.kb40.backend.controller;

import com.matching.kb40.backend.dto.MydataDto;
import com.matching.kb40.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.matching.kb40.backend.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://matching.kbfg.kubepia.com")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	public UserController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/{userId}")
    public UserDto find(@PathVariable String userId) throws Exception {
		UserDto user = userService.find(userId);

    	return user;
    }

	@GetMapping("/findAll")
	public List<UserDto> findAll() throws Exception {
		List<UserDto> userList = userService.findAll();

		return userList;
	}

	@GetMapping("/findRandom/{gender}")
	public List<UserDto> findRandom(@PathVariable String gender) throws Exception {
		List<UserDto> userList = userService.findRandom(gender);

		return userList;
	}

	@PutMapping("")
	public void modify(@RequestBody UserDto user) throws Exception {
		userService.modify(user);
	}

	@DeleteMapping("/{userId}")
	public void remove(@PathVariable String userId) throws Exception {
		userService.remove(userId);
	}

	// ------------------- MYDATA 관련
	@GetMapping("/mydata/{userId}")
	public MydataDto findMydata(@PathVariable String userId) throws Exception {
		MydataDto mydata = userService.findMydata(userId);

		return mydata;
	}
}
