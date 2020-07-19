package com.Nil.JWTDemo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Nil.JWTDemo.Model.AuthencationResponse;
import com.Nil.JWTDemo.Model.AuthenticationRequest;
import com.Nil.JWTDemo.Service.MyUserDetailsService;
import com.Nil.JWTDemo.Util.JwtUtils;

@RestController
public class HelloWorldResource {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired	
	private JwtUtils jwtUtils;

	@GetMapping(path = "/hello")
	public String helloController() {
		return "Hello World";
	}

	@PostMapping(path = "/authentication")
	public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		
		final String jwt = jwtUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthencationResponse(jwt));
	}

}
