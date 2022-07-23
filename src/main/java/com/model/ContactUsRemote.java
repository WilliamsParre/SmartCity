package com.model;

import javax.ejb.Remote;

@Remote
public interface ContactUsRemote {
	String register(String first_name, String last_name, String email, String message);
}
