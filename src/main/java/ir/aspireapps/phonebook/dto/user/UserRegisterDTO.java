package ir.aspireapps.phonebook.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
		name = "UserRegisterDTO",
		description = "New user registration details."
)
public record UserRegisterDTO(
		@Schema(
				description = "Email of the new user, used as a username.",
				example = "Joe.doe@example.com"
		)
		@Email(message="Email address is not valid")
		@NotBlank
		String email,

		@Schema(
				description = "Password of new user(At least one character with a special character).",
				example = "P@ssword"
		)
		@NotBlank(message="Password can't be empty")
		@Size(min = 8, message = "Password must be at least 8 characters")
		@Pattern(
		    regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
		    message = "Password must contain at least one letter and one number"
		)		
		String password,

		@Schema(
				description = "First name of new user.",
				example = "Joe"
		)
		@NotBlank(message="First name can't be empty")
		String firstName,

		@Schema(
				description = "Last name of new user.",
				example = "Doe"
		)
		@NotBlank(message="Last name can't be empty")
		String lastName
		) {}
