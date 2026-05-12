package ir.aspireapps.phonebook.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.aspireapps.phonebook.dto.user.UserRegisterDTO;
import ir.aspireapps.phonebook.error.EmailAlreadyExistsException;
import ir.aspireapps.phonebook.mapper.UserEntityMapper;
import ir.aspireapps.phonebook.model.AppUser;
import ir.aspireapps.phonebook.repo.UserRepository;
import ir.aspireapps.phonebook.security.CustomUserDetails;
import ir.aspireapps.phonebook.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final UserEntityMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void registerUser(UserRegisterDTO request) {
		if(userRepository.existsByEmail(request.email()))
			throw new EmailAlreadyExistsException();
		AppUser user = userMapper.toEntity(request);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);		
	}
	
	@Override
	public String getLoggedInUserEmail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || !(auth.getPrincipal() instanceof CustomUserDetails userDetails)) {
			throw new IllegalStateException("Unauthenticated access");
		}
		
		return userDetails.getUser().getEmail();
	}	

}
