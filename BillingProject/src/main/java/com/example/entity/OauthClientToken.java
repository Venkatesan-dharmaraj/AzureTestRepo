package com.example.entity;

// Generated 13 Sep, 2017 5:11:31 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OauthClientToken generated by hbm2java
 */
@Entity
@Table(name = "oauth_client_token")
public class OauthClientToken implements java.io.Serializable {

	private String authenticationId;
	private String tokenId;
	private byte[] token;
	private String userName;
	private String clientId;

	public OauthClientToken() {
	}

	public OauthClientToken(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public OauthClientToken(String authenticationId, String tokenId,
			byte[] token, String userName, String clientId) {
		this.authenticationId = authenticationId;
		this.tokenId = tokenId;
		this.token = token;
		this.userName = userName;
		this.clientId = clientId;
	}

	@Id
	@Column(name = "authentication_id", unique = true, nullable = false)
	public String getAuthenticationId() {
		return this.authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	@Column(name = "token_id")
	public String getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@Column(name = "token")
	public byte[] getToken() {
		return this.token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "client_id")
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
