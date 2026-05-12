package ir.aspireapps.phonebook.repo;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.aspireapps.phonebook.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

	Optional<Contact> findByIdAndAppUserEmail(Long id, String loggedInEmail);

	Page<Contact> findByAppUserEmailAndLastNameContainingIgnoreCase(String email, String keyword, Pageable pageable);

	Page<Contact> findByAppUserEmail(String email, Pageable pageable);
}
