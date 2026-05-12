package ir.aspireapps.phonebook.domain;

import jakarta.validation.constraints.NotBlank;

public record ContactUpdateCommand(
		@NotBlank String firstName,
		String lastName,
		String phone,
		String email		
		) {}
