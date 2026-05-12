package ir.aspireapps.phonebook.dto.user;

import java.util.List;

import ir.aspireapps.phonebook.dto.contact.ContactResponseDTO;

public record UserResponseDTO(
		Long id,
		String email,
		String firstName,
		String lastName,
		List<ContactResponseDTO> contacts
		) {
}
