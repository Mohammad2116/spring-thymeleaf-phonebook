package ir.aspireapps.phonebook.form.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactCreateForm {
	@NotBlank(message = "First name can't be empty")
	private String firstName;
	
	private String lastName;
	
	@Pattern(regexp = "^$|^[0-9]{10,12}$", message = "Phone number is not valid")
	private String phone;
	
	@Email(message = "Enter a valied email address")
	private String email;
}

