package com.example.SystemAssistance.DTO;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
//import java.util.Base64;
//import java.util.List;
//import java.util.Map;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;

public class adminPageDisplayDTO {

	private String normalUserUserName;
	private String supportUserUserName;
	private LocalDateTime timestamp;
	private String content;
	// private byte[] blobFile;

	// Getters and Setters
	public String getNormalUserUserName() {
		return normalUserUserName;
	}

	public void setNormalUserUserName(String normalUserUserName) {
		this.normalUserUserName = normalUserUserName;
	}

	public String getSupportUserUserName() {
		return supportUserUserName;
	}

	public void setSupportUserUserName(String supportUserUserName) {
		this.supportUserUserName = supportUserUserName;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

//	public byte[] getBlobFile() {
//		return blobFile;
//	}
//
//	public void setBlobFile(byte[] blobFile) {
//		this.blobFile = blobFile;
//	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setBlobFile(byte[] blobFile) {
		if (blobFile != null) {
			// Convert byte array to string
			this.content = new String(blobFile, StandardCharsets.UTF_8);
		} else {
			this.content = null; // or some default value, e.g., ""
		}
	}
}
