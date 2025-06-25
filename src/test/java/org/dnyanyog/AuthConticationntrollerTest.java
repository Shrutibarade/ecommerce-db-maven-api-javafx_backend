package org.dnyanyog;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = EcommerceMain.class)
@AutoConfigureMockMvc
public class AuthConticationntrollerTest { // ✅ Corrected class name

    @Autowired
    private MockMvc mockMvc;

    /**
     * ✅ Test: Validate Login Credentials
     */
    @Test
    public void verifyPostLoginEndPoint() throws Exception {
        String requestBody = "{"
                + "\"loginName\": \"vzodge\","
                + "\"password\": \"Test2123\""
                + "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/auth/validate") // ✅ Use relative path
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(httpBasic("vzodge", "Test@123")); // ✅ Basic Authentication

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // ✅ Expect 200 OK
                .andReturn();

        System.out.println("Login Response: " + result.getResponse().getContentAsString());
    }

    /**
     * ✅ Test: Add New Credentials (User Registration)
     */
    @Test
    public void verifyAddCredentialsEndPoint() throws Exception {
        String requestBody = "{"
                + "\"loginName\": \"newuser\","
                + "\"password\": \"NewPass@123\""
                + "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/auth/addCredentials") // ✅ Added new endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(httpBasic("vzodge", "Test@123")); // ✅ Basic Authentication

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // ✅ Expect 200 OK
                .andReturn();

        System.out.println("Add Credentials Response: " + result.getResponse().getContentAsString());
    }
}
