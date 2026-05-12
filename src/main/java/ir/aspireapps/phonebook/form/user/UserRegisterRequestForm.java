package ir.aspireapps.phonebook.form.user;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestForm {
	@NotBlank(message = "Email address can't be empty")
	@Email(message = "Enter a valid email address")
	private String email;
	
	@NotBlank(message = "Password can't be empty")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
	
	@NotBlank(message = "Confirm password can't be empty")
	private String confirmPassword;
	
	@NotBlank(message = "First name can't be empty")
	private String firstName;
	
	@NotBlank(message="Last name can't be empty")
	private String lastName;
	
	@AssertTrue(message = "Passwords do not match")
	public boolean isPasswordMatch() {
		return password != null && password.equals(confirmPassword);
	}
	
}
