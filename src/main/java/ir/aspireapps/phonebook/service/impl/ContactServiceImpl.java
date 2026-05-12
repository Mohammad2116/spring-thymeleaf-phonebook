package ir.aspireapps.phonebook.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.aspireapps.phonebook.dto.contact.ContactCreateDTO;
import ir.aspireapps.phonebook.dto.contact.ContactResponseDTO;
import ir.aspireapps.phonebook.dto.contact.ContactUpdateDTO;
import ir.aspireapps.phonebook.mapper.UserEntityMapper;
import ir.aspireapps.phonebook.model.AppUser;
import ir.aspireapps.phonebook.model.Contact;
import ir.aspireapps.phonebook.repo.ContactRepository;
import ir.aspireapps.phonebook.repo.UserRepository;
import ir.aspireapps.phonebook.service.ContactService;
import ir.aspireapps.phonebook.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService {
	private final UserRepository userRepository;
	private final UserEntityMapper userMapper;
	private final ContactRepository contactRepository;
	private final UserService userService;
	
	@Override
	public List<ContactResponseDTO> getUserContacts() {
		String loggedInEmail = userService.getLoggedInUserEmail();
		AppUser user = userRepository.findByEmail(loggedInEmail)
				.orElseThrow(() -> new RuntimeException(loggedInEmail));
		
		return userMapper.toResponse(user).contacts();
	}

	@Override
	@Transactional
	public void addContact(ContactCreateDTO request) {
		String loggedInEmail = userService.getLoggedInUserEmail();
		AppUser user = userRepository.findByEmail(loggedInEmail)
				.orElseThrow(() -> new RuntimeException(loggedInEmail));
		
		Contact contact = userMapper.toEntity(request);
		user.addContact(contact);
	}

	@Override
	@Transactional
	public void removeContact(Long id) {
		String loggedInEmail = userService.getLoggedInUserEmail();
		Contact contact = contactRepository.findByIdAndAppUserEmail(id, loggedInEmail)
				.orElseThrow(() -> new RuntimeException("user not found"));
		contact.getAppUser().removeContact(contact);
	}

	@Override
	@Transactional
	public void editContact(ContactUpdateDTO request) {
		String loggedInEmail = userService.getLoggedInUserEmail();
		Contact contact = contactRepository.findByIdAndAppUserEmail(request.id(), loggedInEmail)
				.orElseThrow(() -> new  RuntimeException("user not found"));
		contact.update(userMapper.toCommand(request));
	}
	
}
