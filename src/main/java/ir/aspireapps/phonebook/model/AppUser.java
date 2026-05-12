package ir.aspireapps.phonebook.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import ir.aspireapps.phonebook.model.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "app_users")
@Getter @Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Include
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false, unique = true)
	@ToString.Include
	@EqualsAndHashCode.Include
	private String email;
	
	@Column(nullable = false, length = 60)
	private String password;
	
	@Column(nullable = false)
	@ToString.Include
	private String firstName;
	
	@Column(nullable = false)
	@ToString.Include
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@ToString.Include
	private Role role = Role.USER;
	
	@CreationTimestamp
	@Column(updatable = false, nullable = false)
	@Setter(value = AccessLevel.NONE)
	private LocalDateTime createdAt;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "appUser", fetch = FetchType.LAZY)
	@Setter(value = AccessLevel.NONE)
	private List<Contact> contacts = new ArrayList<>();
	
	public void addContact(Contact contact) {
		contacts.add(contact);
		contact.setAppUser(this);
	}
	public void removeContact(Contact contact) {
		contacts.remove(contact);
		contact.setAppUser(null);
	}
}
