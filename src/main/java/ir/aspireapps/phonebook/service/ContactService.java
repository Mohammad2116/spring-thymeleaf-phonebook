package ir.aspireapps.phonebook.service;

import java.util.List;

import ir.aspireapps.phonebook.dto.contact.ContactCreateDTO;
import ir.aspireapps.phonebook.dto.contact.ContactResponseDTO;
import ir.aspireapps.phonebook.dto.contact.ContactUpdateDTO;

public interface ContactService {
	public List<ContactResponseDTO> getUserContacts();
	public void addContact(ContactCreateDTO request);
	public void removeContact(Long id);	
	public void editContact(ContactUpdateDTO request);
}
