package ir.aspireapps.phonebook.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.media.EmailSchema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(
		name = "ContactUpdateDTO",
		description = "Request to update contact details."
)
public record ContactUpdateDTO(
		@Schema(
				description = "ID of the target contact, (This field is not changeable).",
				example = "1"
		)
		@NotNull
		Long id,

		@Schema(
				description = "New First name of contact.",
				example = "Joe"
		)
		@NotBlank(message = "First name can't be empty")
		String firstName,

		@Schema(
				description = "New last name of contact.",
				example = "Doe"
		)
		String lastName,

		@Schema(
				description = "New Phone number of contact.",
				example = "09xxxxxxxxx"
		)
		@Pattern(regexp = "^[0-9]{10,12}$", message = "Phone number is not valid")
		String phone,

		@Schema(
				description = "New Email of the contact.",
				example = "Joe.doe@example.com"
		)
		@Email(message = "Enter a valid email address")
		String email
		) {
}
