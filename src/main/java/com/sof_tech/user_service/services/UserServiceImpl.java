package com.sof_tech.user_service.services;

import com.sof_tech.user_service.exception.ResourceNotFoundException;
import com.sof_tech.user_service.model.User;
import com.sof_tech.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        String string = UUID.randomUUID().toString();
        user.setUserId(string);
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id not found on server:" + userId));
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUserById(User user, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
