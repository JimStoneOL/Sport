package com.marat.controlworkbymarat.web;



import com.marat.controlworkbymarat.entity.Role;
import com.marat.controlworkbymarat.entity.User;
import com.marat.controlworkbymarat.entity.enums.ERole;
import com.marat.controlworkbymarat.payload.request.LoginRequest;
import com.marat.controlworkbymarat.payload.request.SignupRequest;
import com.marat.controlworkbymarat.payload.response.JwtResponse;
import com.marat.controlworkbymarat.payload.response.MessageResponse;
import com.marat.controlworkbymarat.repository.RoleRepository;
import com.marat.controlworkbymarat.repository.UserRepository;
import com.marat.controlworkbymarat.security.jwt.JwtUtils;
import com.marat.controlworkbymarat.service.UserDetailsImpl;
import com.marat.controlworkbymarat.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRespository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	ResponseErrorValidation responseErrorValidation;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), 
						loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		User user=userRespository.findById(userDetails.getId()).orElse(null);
		user.setAuthType(true);
		user.setLastAuth(LocalDateTime.now());
		userRespository.save(user);

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getEmail(), 
				roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {

		ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
		if (!ObjectUtils.isEmpty(errors)) return errors;

		if (userRespository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is exist"));
		}
		
		User user = new User(
				signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()));
		user.setFio(signupRequest.getLastname()+" "+signupRequest.getFirstname()+" "+signupRequest.getMiddlename());
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository
				.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
		roles.add(userRole);
		user.setRoles(roles);
		userRespository.save(user);
		return ResponseEntity.ok(new MessageResponse("User CREATED"));
	}
}
