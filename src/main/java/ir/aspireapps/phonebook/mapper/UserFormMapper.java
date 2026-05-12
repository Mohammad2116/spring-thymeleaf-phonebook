package ir.aspireapps.phonebook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ir.aspireapps.phonebook.dto.contact.ContactCreateDTO;
import ir.aspireapps.phonebook.dto.contact.ContactResponseDTO;
import ir.aspireapps.phonebook.dto.contact.ContactUpdateDTO;
import ir.aspireapps.phonebook.dto.user.UserRegisterDTO;
import ir.aspireapps.phonebook.dto.user.UserResponseDTO;
import ir.aspireapps.phonebook.form.contact.ContactCreateForm;
import ir.aspireapps.phonebook.form.contact.ContactResponseForm;
import ir.aspireapps.phonebook.form.contact.ContactUpdateForm;
import ir.aspireapps.phonebook.form.user.UserRegisterRequestForm;
import ir.aspireapps.phonebook.form.user.UserResponseForm;
import jakarta.validation.Valid;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserFormMapper {
	
	public UserRegisterDTO toDTO(UserRegisterRequestForm request);
	public ContactCreateDTO toDTO(ContactCreateForm request);
	
	public UserResponseForm toForm(UserResponseDTO dto);
	public ContactResponseForm toForm(ContactResponseDTO dto);
	public ContactUpdateDTO toDTO(@Valid ContactUpdateForm contact);
}