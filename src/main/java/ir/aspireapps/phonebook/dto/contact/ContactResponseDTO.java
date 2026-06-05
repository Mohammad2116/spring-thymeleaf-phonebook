package ir.aspireapps.phonebook.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
		name = "ContactResponseDTO",
		description = "Returns contact details."
)
public record ContactResponseDTO(
		@Schema(
				name = "Contact ID",
				example = "5"
		)
		Long id,
		@Schema(
				description = "First name of contact.",
				example = "Joe"
		)
		String firstName,
		@Schema(
				description = "Last name of contact.",
				example = "Doe"
		)
		String lastName,
		@Schema(
				description = "Phone number of contact.",
				example = "09xxxxxxxxx"
		)
		String phone,
		@Schema(
				description = "Email address of contact.",
				example = "Joe.doe@example.com"
		)
		String email
		) {

}
