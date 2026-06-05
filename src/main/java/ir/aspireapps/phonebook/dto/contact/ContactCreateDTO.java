package ir.aspireapps.phonebook.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(
		name = "ContactCreateDTO",
		description = "Request to register a new contact."
)
public record ContactCreateDTO(
		@Schema(
				description = "First name of contact.",
				example = "Joe"
		)
		@NotBlank(message = "First name can't be empty")
		String firstName,

		@Schema(
				description = "Last name of contact.",
				example = "Doe"
		)
		String lastName,

		@Schema(
				description = "Phone number of contact (Only numbers with the length of 10 to 12).",
				example = "09xxxxxxxxx"
		)
		@Pattern(regexp = "^[0-9]{10,12}$", message = "Phone number is not valid")
		String phone,

		@Schema(
				description = "Email address of contact (Valid email address).",
				example = "Joe.doe@example.com"
		)
		@Email(message = "Enter a valid email address")
		String email
		) {
}
