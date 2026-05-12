package ir.aspireapps.phonebook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ir.aspireapps.phonebook.domain.ContactUpdateCommand;
import ir.aspireapps.phonebook.dto.contact.ContactCreateDTO;
import ir.aspireapps.phonebook.dto.contact.ContactResponseDTO;
import ir.aspireapps.phonebook.dto.contact.ContactUpdateDTO;
import ir.aspireapps.phonebook.dto.user.UserRegisterDTO;
import ir.aspireapps.phonebook.dto.user.UserResponseDTO;
import ir.aspireapps.phonebook.model.AppUser;
import ir.aspireapps.phonebook.model.Contact;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "contacts", ignore = true)
	public AppUser toEntity(UserRegisterDTO request);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "appUser", ignore = true)
	public Contact toEntity(ContactCreateDTO request);
	
	public UserResponseDTO toResponse(AppUser entity);
	public ContactResponseDTO toResponse(Contact entity);

	public ContactUpdateCommand toCommand(ContactUpdateDTO request);

}