package com.redmars.microservice_template_graphql.controller;

import com.redmars.microservice_template_graphql.model.User;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

/*
 * This class is where you will map all the API calls.
 * These are called querys using GraphQL and tell the service how to behave
 * for a given query.
 */
@Controller
public class UserController {
    
    @QueryMapping
    public String hello() {
        return "Hello, GraphQL";
    }

    /*
     * This is an entry of data that can be requested using a query.
     * This can be dynamically created based on an input file rather 
     * than manually inputted in the code.
     */
    @QueryMapping
    public User userById(@Argument String id) {
        return new User(id, "Alice", "RedHat", "hackeralice@redmars.com");
    }
}
