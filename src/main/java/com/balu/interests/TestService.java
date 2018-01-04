package com.balu.interests;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balu.interests.security.AuthenticatedUser;



@RestController
@RequestMapping("/api")
public class TestService {

	@GetMapping(value="/test")
	public String getName() {
		
		return  ((AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
}
