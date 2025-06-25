package org.dnyanyog.service;

import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.entity.User;
import org.dnyanyog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

 // ✅ Add a new user
    public String addUser(UserRequest userRequest) {
        try {
            User user = new User();
            user.setUserName(userRequest.getUserName());
            user.setUserEmail(userRequest.getUserEmail());
            user.setUserMobile(userRequest.getUserMobile());

            userRepository.save(user);
            return "User added successfully!";
        } catch (Exception e) {
            return "User adding failed! Error: " + e.getMessage();
        }
    }
    

    // ✅ Find User by ID & Name and Return Full Details
    public String getUserByIdAndName(Integer id, String userName) {
        Optional<User> userOptional = userRepository.findByUserIdAndUserNameIgnoreCase(id, userName);

        if (userOptional.isEmpty()) {
            return "User Not Found"; 
        }

        User user = userOptional.get();
        return "User Found: ID=" + user.getUserId() +
               ", Name=" + user.getUserName() +
               ", Email=" + user.getUserEmail() +
               ", Mobile=" + user.getUserMobile();
    }

    // ✅ Update User by ID
    public String updateUser(Integer userId, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isEmpty()) {
            return "User Not Found";
        }

        User user = optionalUser.get();
        user.setUserName(userRequest.getUserName());
        user.setUserEmail(userRequest.getUserEmail());
        user.setUserMobile(userRequest.getUserMobile());

        userRepository.save(user);
        return "User updated successfully!";
    }
 // ✅ Delete User by ID
    public String deleteUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isEmpty()) {
            return "User Not Found";
        }

        userRepository.deleteById(userId);
        return "User deleted successfully!";
    }

}
