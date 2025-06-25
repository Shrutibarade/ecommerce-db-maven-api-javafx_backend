package org.dnyanyog.dto;



public class LoginResponse {
    private String responseCode; // 0000 -> Success, 911 -> Fail
    private String responseMessage;

    // Constructor (Optional)
    public LoginResponse() {}

    public LoginResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    // Getters & Setters
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
