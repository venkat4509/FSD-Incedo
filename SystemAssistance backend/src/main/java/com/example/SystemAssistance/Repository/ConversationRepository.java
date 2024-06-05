package com.example.SystemAssistance.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.SystemAssistance.Entity.Conversation;
import com.example.SystemAssistance.Entity.User;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

	//void save(Conversation conversationTable);
	/*
	@Query(value = "SELECT * FROM Conversation WHERE support_user_user_name = :supportUsername AND normal_user_user_name = :normalUsername", nativeQuery = true)
	//@Query("SELECT c FROM Conversation c WHERE c.supportUserUserName = :supportUsername AND c.normalUserUserName = :normalUsername")
	Conversation findBySupportUserUserNameAndNormalUserUserName(String otherUsername, String username);
`*/
	
//	@Query(value = "SELECT * FROM Conversation WHERE support_user_user_name = :supportUsername AND normal_user_user_name = :normalUsername", nativeQuery = true)
//	Optional<Conversation> findBySupportUserUserNameAndNormalUserUserName(@Param("supportUsername") String supportUsername, @Param("normalUsername") String normalUsername);
	
	@Query(value = "SELECT * FROM Conversation WHERE support_user_user_name = :supportUsername AND normal_user_user_name = :normalUsername  AND availability = :availability", nativeQuery = true)
	Optional<Conversation> findBySupportUserUserNameAndNormalUserUserNameAndAvailability(@Param("supportUsername") String supportUsername, @Param("normalUsername") String normalUsername, @Param("availability") boolean availability);

	Optional<Conversation> findById(Integer id);

	Conversation findBySupportUserUserName(String userName);

	//Conversation findByAvailability(String supportUserUsername);

	
	@Query("SELECT c FROM Conversation c WHERE c.supportUserUserName = :supportUserUsername AND c.availability = false")
    Conversation findBySupportUserUserNameAndAvailability(@Param("supportUserUsername") String supportUserUsername);

	@Query("SELECT c FROM Conversation c WHERE c.supportUserUserName = :supportUserUsername AND c.availability = :availability")
    Conversation findBySupportUserUserNameAndAvailability(@Param("supportUserUsername") String supportUserUserName, @Param("availability") boolean availability);
//	@Query("SELECT c FROM Conversation c WHERE c.supportUserUserName = :supportUserUsername")
//	Conversation findBySupportUserUserName(@Param("supportUserUsername") String supportUserUsername);





}

