package com.redmars.microservice_template_graphql.model;

import lombok.Getter;
import lombok.Setter;

/*
 * This class defines the structure of User, but could be any class of
 * object. Note that @Getter and @Setter annotations is part of the 
 * Lombok dependency and automatically creates all Getters and Setters
 * at compile time, removing the boilerplate code that usually is needed.
 */
@Getter
@Setter
public class User {
    private String id;
    private String fname;
    private String lname;
    private String email;

    public User(String id, String fname, String lname, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
}
