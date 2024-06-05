package com.example.SystemAssistance.Entity;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Conversation {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int id;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private int conversationId;

    @ManyToOne
    @JoinColumn(name = "normalUser_userid")
    private User normalUser;

    @ManyToOne
    @JoinColumn(name = "supportUser_userid")
    private SupportUser supportUser;
    
    private String supportUserUserName;
    
    private String normalUserUserName;
    
    private boolean availability;
    
    

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Lob
    @Column(name = "blob_file", columnDefinition = "LONGBLOB")
    private byte[] blobFile;
    
    public Conversation() {
    	
    }
	public Conversation(int conversationId, User normalUser, SupportUser supportUser, LocalDateTime timestamp,
			byte[] blobFile) {
		super();
		this.conversationId = conversationId;
		this.normalUser = normalUser;
		this.supportUser = supportUser;
		//this.timestamp = timestamp;
		this.blobFile = blobFile;
	}

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public User getNormalUser() {
		return normalUser;
	}

	public void setNormalUser(User normalUser) {
		this.normalUser = normalUser;
	}

	public SupportUser getSupportUser() {
		return supportUser;
	}

	public void setSupportUser(SupportUser supportUser) {
		this.supportUser = supportUser;
	}
	
	public String getSupportUserUserName() {
		return supportUserUserName;
	}

	public void setSupportUserUserName(String supportUserUserName) {
		this.supportUserUserName = supportUserUserName;
	}

	public String getNormalUserUserName() {
		return normalUserUserName;
	}

	public void setNormalUserUserName(String normalUserUserName) {
		this.normalUserUserName = normalUserUserName;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public byte[] getBlobFile() {
		return blobFile;
	}

	public void setBlobFile(byte[] blobFile) {
		this.blobFile = blobFile;
	}
	
    
}
