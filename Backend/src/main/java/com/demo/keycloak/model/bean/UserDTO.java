package com.demo.keycloak.model.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String username;
    private String name;
    private String surname;
    private String photo;
    private String description;
    private Date creationDate;
    private Date deletionDate;
    private Date lastUpdatedDate;
}