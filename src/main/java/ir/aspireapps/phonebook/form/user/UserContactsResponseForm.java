package ir.aspireapps.phonebook.form.user;

import java.util.ArrayList;
import java.util.List;

import ir.aspireapps.phonebook.form.contact.ContactResponseForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContactsResponseForm {
	private List<ContactResponseForm> contacts = new ArrayList<>();
}
