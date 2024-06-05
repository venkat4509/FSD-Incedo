package com.example.SystemAssistance.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SystemAssistance.Entity.Role;
import com.example.SystemAssistance.Entity.SupportUser;

public interface SupportUserRepository extends JpaRepository<SupportUser, Integer> {

	//public  save(SupportUser supportUser);
	// You can define additional query methods here if needed
	//Optional<Employee> findFirstByIsAvailableTrue();

	//public SupportUser findFirstByIsAvailableTrue();

	//public SupportUser findFirstByAvailabilityTrueandRole(Role role);

	public SupportUser findFirstByAvailabilityTrueAndRole(Role role);

	public Optional<SupportUser> findByUserUserId(int userId);
}

