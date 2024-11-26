package com.sof_tech.user_service.services;

import com.sof_tech.user_service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User saveUser(User user);

    User getUserById(String userId);

    List<User> getAllUser();

    User updateUserById(User user, String userId);

    void deleteUser(String userId);
}
