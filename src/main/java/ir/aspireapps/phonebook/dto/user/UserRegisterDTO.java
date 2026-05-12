package ir.aspireapps.phonebook.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
		@Email(message="Email address is not valied")
		@NotBlank
		String email,
		@NotBlank(message="Password can't be empty")
		@Size(min = 8, message = "Password must be at least 8 characters")
		@Pattern(
		    regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
		    message = "Password must contain at least one letter and one number"
		)		
		String password,
		@NotBlank(message="First name can't be empty")
		String firstName,
		@NotBlank(message="Last name can't be empty")
		String lastName
		) {}
