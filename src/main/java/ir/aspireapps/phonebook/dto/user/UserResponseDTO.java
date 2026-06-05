package ir.aspireapps.phonebook.dto.user;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.aspireapps.phonebook.dto.contact.ContactResponseDTO;

@Schema(
		name = "UserResponseDTO",
		description = "User details information."
)
public record UserResponseDTO(
		@Schema(
				description = "ID of the user.",
				examples = "5"
		)
		Long id,

		@Schema(
				description = "Email of the user.",
				example = "Joe.doe@example.com"
		)
		String email,

		@Schema(
				description = "First name of the user.",
				example = "Joe"
		)
		String firstName,

		@Schema(
				description = "Last name of the user.",
				example = "Doe"
		)
		String lastName,

		@Schema(
				description = "List of user contacts."
		)
		List<ContactResponseDTO> contacts
		) {
}