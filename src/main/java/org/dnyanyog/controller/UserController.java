package org.dnyanyog.controller;

import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ API to add a new user (Always returns JSON)
    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        String responseMessage = userService.addUser(userRequest);
        
        UserResponse response = new UserResponse();
        response.setStatus(responseMessage.contains("successfully") ? "success" : "error");
        response.setMessage(responseMessage);

        return ResponseEntity.status(responseMessage.contains("successfully") ? 201 : 400).body(response);
    }

    // ✅ API to delete user (Returns JSON)
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer userId) {
        String responseMessage = userService.deleteUser(userId);
        
        UserResponse response = new UserResponse();
        response.setStatus(responseMessage.equals("User Not Found") ? "error" : "success");
        response.setMessage(responseMessage);

        return ResponseEntity.status(responseMessage.equals("User Not Found") ? 404 : 200).body(response);
    }
    // ✅ API to update user by ID
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody UserRequest userRequest) {
        String responseMessage = userService.updateUser(userId, userRequest);

        UserResponse response = new UserResponse();
        response.setStatus(responseMessage.equals("User updated successfully!") ? "success" : "error");
        response.setMessage(responseMessage);

        return ResponseEntity.status(responseMessage.equals("User updated successfully!") ? 200 : 404).body(response);
    }
 // ✅ API to search user by ID and Name
    @GetMapping("/search")
    public ResponseEntity<UserResponse> getUserByIdAndName(@RequestParam Integer userId, @RequestParam String userName) {
        String responseMessage = userService.getUserByIdAndName(userId, userName);

        UserResponse response = new UserResponse();
        response.setStatus(responseMessage.startsWith("User Found") ? "success" : "error");
        response.setMessage(responseMessage);

        return ResponseEntity.status(responseMessage.startsWith("User Found") ? 200 : 404).body(response);
    }

}
