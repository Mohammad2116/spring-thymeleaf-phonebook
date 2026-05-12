package ir.aspireapps.phonebook.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.aspireapps.phonebook.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
	public Optional<AppUser> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}
