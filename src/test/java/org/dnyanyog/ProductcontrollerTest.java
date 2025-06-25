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
public class ProductcontrollerTest { // ✅ Corrected class name

    @Autowired
    private MockMvc mockMvc;

    /**
     * ✅ Test: Create a New Product
     */
    @Test
    public void verifyCreateProduct() throws Exception {
        String requestBody = "{" +
                "\"productName\": \"Laptop\"," +
                "\"productPrice\": 75000," +
                "\"productQuantity\": 10" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(httpBasic("vzodge", "Test@123")); // ✅ Basic Authentication

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // ✅ Expect 200 OK
                .andExpect(jsonPath("$.productName").value("Laptop")) // ✅ Check response field
                .andReturn();

        System.out.println("Create Product Response: " + result.getResponse().getContentAsString());
    }

    /**
     * ✅ Test: Update Product (Using POST Instead of PUT)
     */
    @Test
    public void verifyUpdateProduct() throws Exception {
        String requestBody = "{" +
                "\"productName\": \"Gaming Laptop\"," +
                "\"productPrice\": 85000," +
                "\"productQuantity\": 8" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/products/21")  // ✅ Using POST instead of PUT
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(httpBasic("vzodge", "Test@123")); // ✅ Basic Authentication

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // ✅ Expect 200 OK
                .andExpect(jsonPath("$.productName").value("Gaming Laptop")) // ✅ Check updated field
                .andReturn();

        System.out.println("Update Product Response: " + result.getResponse().getContentAsString());
    }

    /**
     * ✅ Test: Delete a Product
     */
    @Test
    public void verifyDeleteProduct() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/api/products/22") // ✅ Delete request
                .with(httpBasic("vzodge", "Test@123")); // ✅ Basic Authentication

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // ✅ Expect 200 OK
                .andReturn();

        System.out.println("Delete Product Response: " + result.getResponse().getContentAsString());
    }

    /**
     * ✅ Test: Search Product by ID and Name
     */
    @Test
    public void verifySearchProduct() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/products/search/23/Laptop") // ✅ Search request
                .with(httpBasic("vzodge", "Test@123")); // ✅ Basic Authentication

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()) // ✅ Expect 200 OK
                .andReturn();

        System.out.println("Search Product Response: " + result.getResponse().getContentAsString());
    }
}
