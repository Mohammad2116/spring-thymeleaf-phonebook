package ir.aspireapps.phonebook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ir.aspireapps.phonebook.error.EmailAlreadyExistsException;
import ir.aspireapps.phonebook.form.user.UserRegisterRequestForm;
import ir.aspireapps.phonebook.mapper.UserFormMapper;
import ir.aspireapps.phonebook.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(
		name = "AuthController",
		description = "User authentication and Remember me Token management"
)
@Controller
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	private final UserFormMapper userFormMapper;
	private final AuthenticationManager authenticationManager;
	private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	@ModelAttribute
	public void commonAttributes(Model model) {
		model.addAttribute("title", "Phone Book");
		model.addAttribute("loggedin", isAuthenticated());
	}
	
	private boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return  auth != null &&
				auth.isAuthenticated() &&
				!(auth instanceof AnonymousAuthenticationToken);
	}
	
	private void autoLogin(String email, String rawPassword,
			HttpServletRequest request,
			HttpServletResponse response) {

	    UsernamePasswordAuthenticationToken authToken =
	            new UsernamePasswordAuthenticationToken(email, rawPassword);

	    Authentication authentication = authenticationManager.authenticate(authToken);
	    SecurityContext context = SecurityContextHolder.createEmptyContext();
	    context.setAuthentication(authentication);

	    SecurityContextHolder.setContext(context);
	    securityContextRepository.saveContext(context, request, response);
	}

	@Operation(
			summary = "Register",
			description = "Render registration page"
	)
	@GetMapping("/register")
	public String register(Model model) {
			model.addAttribute("loginBtnActive", true);
			model.addAttribute("registerBtnActive", false);
			model.addAttribute("registerForm", new UserRegisterRequestForm());
			
		return "register";
	}

	@Operation(
			summary = "Process Registration",
			description = "Register new user and redirects to contacts page and returns login information"
	)
	@PostMapping("/register/process")
	public String register_process(
			@Parameter(
					description = "new user details"
			)
			@Valid @ModelAttribute("registerForm") UserRegisterRequestForm registerForm,
			BindingResult result, 
			Model model,
	        HttpServletRequest servletRequest, 
	        HttpServletResponse servletResponse) {
		
		model.addAttribute("loginBtnActive", true);
		model.addAttribute("registerBtnActive", false);	
		
		if( !(registerForm.getPassword().equals(registerForm.getConfirmPassword())) ) {
			result.rejectValue("confirmPassword", "mismatch", "Passwords are not equal");
			model.addAttribute("globalError", "Registration failed");
			return "register";
		}
		
		if(result.hasErrors()) {
			model.addAttribute("globalError", "Registration failed");
			return "register";
		}
	
		String rawPassword = registerForm.getPassword();
		try {
			userService.registerUser(userFormMapper.toDTO(registerForm));
			autoLogin(registerForm.getEmail(), rawPassword, servletRequest, servletResponse);
			return "redirect:/contacts";
		} catch (EmailAlreadyExistsException ex) {
			result.rejectValue("email", "duplicated", "Email address already registered");
			model.addAttribute("globalError", "Registration failed");
			return "register";
		} catch (Exception ex) {
			model.addAttribute("globalError", "Server Error, try again later");
			return "register";
		}
	}	


	@Operation(
			summary = "Login page",
			description = "Renders the login page, processes user credentials, " +
					"and redirects to the contacts page."
			)
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginBtnActive", false);
		model.addAttribute("registerBtnActive", true);
		return "login";
	}

	@Operation(
			summary = "Home page",
			description = "Render main page of application, with links to register or login pages."
	)
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("loginBtnActive", true);
		model.addAttribute("registerBtnActive", true);
		return "home";
	}
}
