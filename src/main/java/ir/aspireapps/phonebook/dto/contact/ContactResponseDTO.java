package ir.aspireapps.phonebook.dto.contact;

public record ContactResponseDTO(
		Long id,
		String firstName,
		String lastName,
		String phone,
		String email
		) {

}
