package org.dnyanyog.dto;

public class UserRequest {
    private Integer userId; // ✅ Use 'userId' to match JSON
    private String userName;
    private String userEmail;
    private String userMobile;

    // ✅ Getters and Setters
    public Integer getUserId() {  // ✅ Correct getter name
        return userId;
    }

    public void setUserId(Integer userId) {  // ✅ Correct setter name
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
}
