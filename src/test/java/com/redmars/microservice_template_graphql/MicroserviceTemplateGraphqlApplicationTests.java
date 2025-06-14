package com.redmars.microservice_template_graphql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = true) // ensure filters like security are included
@WithMockUser(username = "admin", roles = {"USER"}) // optional shortcut for some cases
class MicroserviceTemplateGraphqlApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
        // Just ensures app starts correctly
    }

    @Test
	@WithAnonymousUser // forces Spring Security to treat this as unauthenticated
    void testHelloQuery_Unauthenticated_ShouldRedirectToLogin() throws Exception {
        String query = "{ \"query\": \"{ hello }\" }";

        mockMvc.perform(post("/graphql")
                .contentType(MediaType.APPLICATION_JSON)
                .content(query))
                .andExpect(status().is3xxRedirection()) // redirects to /login
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @Test
    void testHelloQuery_WithBasicAuth_ShouldReturnHello() throws Exception {
        String query = "{ \"query\": \"{ hello }\" }";

        mockMvc.perform(post("/graphql")
                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic("admin", "superSecretPassword"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.hello").value("Hello, GraphQL"));
    }

    @Test
    void testUserByIdQuery_ShouldReturnUserfName() throws Exception {
        String query = "{ \"query\": \"{ userById(id: \\\"1\\\") { fname email } }\" }";

        mockMvc.perform(post("/graphql")
                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic("admin", "superSecretPassword"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userById.fname").value("Alice"))
                .andExpect(jsonPath("$.data.userById.email").value("hackeralice@redmars.com"));
    }

	@Test
    void testUserByIdQuery_ShouldReturnUserlName() throws Exception {
        String query = "{ \"query\": \"{ userById(id: \\\"1\\\") { lname email } }\" }";

        mockMvc.perform(post("/graphql")
                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic("admin", "superSecretPassword"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userById.lname").value("RedHat"))
                .andExpect(jsonPath("$.data.userById.email").value("hackeralice@redmars.com"));
    }
}
