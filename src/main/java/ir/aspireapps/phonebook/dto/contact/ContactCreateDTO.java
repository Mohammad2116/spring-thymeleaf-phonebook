package ir.aspireapps.phonebook.dto.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ContactCreateDTO(
		
		@NotBlank(message = "First name can't be empty")
		String firstName,
		
		String lastName,
		
		@Pattern(regexp = "^[0-9]{10,12}$", message = "Phone number is not valid")
		String phone,
		
		@Email(message = "Enter a valied email address")
		String email
		) {
}
