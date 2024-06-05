package com.example.SystemAssistance.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SystemAssistance.Entity.SupportUser;
import com.example.SystemAssistance.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
//	boolean findByEmail(boolean b);

	void save(SupportUser supportUser);

	//Optional<User> findById(Integer userId);

}
