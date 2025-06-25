package org.dnyanyog;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * ✅ Test: Add a New User
     */
    @Test
    public void verifyAddUser() throws Exception {
        String requestBody = "{" +
                "\"userId\": 4," +
                "\"userName\": \"Alice\"," +
                "\"userEmail\": \"alice@example.com\"," +
                "\"userMobile\": \"9876543210\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(httpBasic("vzodge", "Test@123"));

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated()) // ✅ Expect 201 Created
                .andReturn();

        System.out.println("Add User Response: " + result.getResponse().getContentAsString());
    }

    /**
     * ✅ Test: Find a User
     */
    @Test
    public void verifyFindUser() throws Exception {
        String requestBody = "{" +
                "\"userId\": 19," +
                "\"userName\": \"Alice\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/users/find")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(httpBasic("vzodge", "Test@123"));

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // ✅ Expect 200 OK
                .andReturn();

        System.out.println("Find User Response: " + result.getResponse().getContentAsString());
    }

/**
 * ✅ Test: Update User
 */
@Test
public void verifyUpdateUser() throws Exception {
    String requestBody = "{" +
    		 "\"userName\": \"shruti\"," +
             "\"userEmail\": \"shrutib@example.com\"," +
             "\"userMobile\": \"9876523210\"" +
            "}";

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post("/users/update/23")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .with(httpBasic("vzodge", "Test@123"));

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk()) // ✅ Expect 200 OK
            .andReturn();

    System.out.println("Update User Response: " + result.getResponse().getContentAsString());
}

/**
 * ✅ Test: Delete User
 */
@Test
public void verifyDeleteUser() throws Exception {
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .delete("/users/delete/21")
            .with(httpBasic("vzodge", "Test@123"));

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk()) // ✅ Expect 200 OK
            .andReturn();

    System.out.println("Delete User Response: " + result.getResponse().getContentAsString());
}
}

