package ir.aspireapps.phonebook.form.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseForm {
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
}
