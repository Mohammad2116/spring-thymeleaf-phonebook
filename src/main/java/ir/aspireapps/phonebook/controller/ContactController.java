package ir.aspireapps.phonebook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ir.aspireapps.phonebook.form.contact.ContactCreateForm;
import ir.aspireapps.phonebook.form.contact.ContactUpdateForm;
import ir.aspireapps.phonebook.mapper.UserFormMapper;
import ir.aspireapps.phonebook.model.Contact;
import ir.aspireapps.phonebook.repo.ContactRepository;
import ir.aspireapps.phonebook.service.ContactService;
import ir.aspireapps.phonebook.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContactController {
	private final ContactRepository contactRepository;
	private final UserService userService;
	private final ContactService contactService;
	private final UserFormMapper userFormMapper;

	@Operation(
			summary = "List of User contacts",
			description = "Renders a paged list of contacts for the user."
	)
	@GetMapping("/contacts")
	public String contacts(
			@Parameter(
					description = "Page number.",
					example = "0"
			)
			@RequestParam(defaultValue = "0") int page,
			@Parameter(
					description = "Size of the page.",
					example = "10"
			)
			@RequestParam(defaultValue = "10") int size,
			@Parameter(
					description = "Name of the field to sort contacts based on",
					example = "firstName"
			)
			@RequestParam(defaultValue = "lastName") String sortBy,
			@Parameter(
					description = "Method of sorting (Ascending/Descending).",
					example = "asc"
			)
			@RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(required = false) String keyword, Model model) {

		prepareContactsPage(page, size, sortBy, sortDir, keyword, model);
		model.addAttribute("contactUpdateForm", new ContactUpdateForm());
		model.addAttribute("contactCreateForm", new ContactCreateForm());	

		return "contacts";
	}

	private void prepareContactsPage(int page, int size, String sortBy, String sortDir, String keyword, Model model) {
		String email = userService.getLoggedInUserEmail();

		Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Contact> contacts;

		if (keyword != null && !keyword.isEmpty()) {
			contacts = contactRepository.findByAppUserEmailAndLastNameContainingIgnoreCase(email, keyword, pageable);
			model.addAttribute("keyword", keyword);
		} else {
			contacts = contactRepository.findByAppUserEmail(email, pageable);
		}

		model.addAttribute("page", contacts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", contacts.getTotalPages());
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");	
	}

	@Operation(
			summary = "Add Contact",
			 description = "Add new contact to user contacts list."
	)
	@PostMapping("/contacts/save")
	public String save(
			@Parameter(
					description = "New contact details in a Form."
			)
			@Valid @ModelAttribute("contactCreateForm") ContactCreateForm form, BindingResult result,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "lastName") String sortBy, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(required = false) String keyword, Model model) {

		if (result.hasErrors()) {
			prepareContactsPage(page, size, sortBy, sortDir, keyword, model);
			model.addAttribute("newContactErrors", true);
			model.addAttribute("contactUpdateForm", new ContactUpdateForm());			
			return "contacts";
		}
		contactService.addContact(userFormMapper.toDTO(form));
		return "redirect:/contacts";
	}

	@Operation(
			summary = "Update Contact",
			description = "Updates contact details."
	)
	@PostMapping("/contacts/edit")
	public String edit(
			@Parameter(
					description = "New details of contact to edit."
			)
			@Valid @ModelAttribute("contactUpdateForm") ContactUpdateForm form, BindingResult result,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "lastName") String sortBy, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(required = false) String keyword, Model model) {

		if (result.hasErrors()) {
			prepareContactsPage(page, size, sortBy, sortDir, keyword, model);
			model.addAttribute("editContactErrors", true);
			model.addAttribute("contactCreateForm", new ContactCreateForm());				
			return "contacts";
		}

		contactService.editContact(userFormMapper.toDTO(form));
		return "redirect:/contacts";
	}

	@Operation(
			summary = "Delete contact",
			description = "Delete contact from user contacts."
	)
	@PostMapping("/contacts/delete/{id}")
	public String delete(
			@Parameter(
					description = "ID of the target contact to delete."
			)
			@PathVariable Long id) {
		contactService.removeContact(id);
		return "redirect:/contacts";
	}
}
