package com.redmars.microservice_template_graphql.controller;

import com.redmars.microservice_template_graphql.model.User;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    
    @QueryMapping
    public String hello() {
        return "Hello, GraphQL";
    }

    @QueryMapping
    public User userById(@Argument String id) {
        return new User(id, "Alice", "RedHat", "hackeralice@redmars.com");
    }
}
