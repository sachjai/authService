package com.myproject.auth;

import org.springframework.web.client.RestTemplate;

public class TokenStore {
	
	public void addToken(String username, String token)
	{
	    final String uri = "http://localhost:8080/addToken";
	 
	    Token objToken = new Token();
	    objToken.setToken(token);
	    objToken.setUsername(username);
	 
	    RestTemplate restTemplate = new RestTemplate();
	    Token result = restTemplate.postForObject( uri, objToken, Token.class);
	 
	    System.out.println(result);
	}

}
