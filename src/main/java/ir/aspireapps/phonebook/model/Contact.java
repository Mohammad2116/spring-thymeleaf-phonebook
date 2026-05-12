package ir.aspireapps.phonebook.model;

import ir.aspireapps.phonebook.domain.ContactUpdateCommand;
import ir.aspireapps.phonebook.dto.contact.ContactUpdateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contacts")
@Getter @Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	@ToString.Include
	private String firstName;
	
	@ToString.Include
	private String lastName;
	
	@ToString.Include
	private String phone;
	
	@ToString.Include
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser appUser;

	public void update(ContactUpdateCommand command) {
		this.firstName = command.firstName();
		this.lastName = command.lastName();
		this.phone = command.phone();
		this.email = command.email();
	}
}
