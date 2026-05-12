package ir.aspireapps.phonebook.service;

import ir.aspireapps.phonebook.dto.user.UserRegisterDTO;

public interface UserService {

	public void registerUser(UserRegisterDTO request);

	public String getLoggedInUserEmail();
}
