package com.model;

import javax.ejb.Remote;

@Remote
public interface SignUpRemote {
	
	String register(String first_name, String last_name, String username, String email,String password);
	
}
