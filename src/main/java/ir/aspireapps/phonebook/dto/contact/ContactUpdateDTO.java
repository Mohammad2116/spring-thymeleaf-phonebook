package ir.aspireapps.phonebook.dto.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ContactUpdateDTO(
		@NotNull
		Long id,
		@NotBlank(message = "First name can't be empty")
		String firstName,		
		String lastName,		
		@Pattern(regexp = "^[0-9]{10,12}$", message = "Phone number is not valid")
		String phone,		
		@Email(message = "Enter a valid email address")
		String email
		) {
}
