package com.sof_tech.user_service.services;

import com.sof_tech.user_service.exception.ResourceNotFoundException;
import com.sof_tech.user_service.model.Rating;
import com.sof_tech.user_service.model.User;
import com.sof_tech.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    Logger loggerFactory = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {

        String string = UUID.randomUUID().toString();
        user.setUserId(string);
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id not found on server:" + userId));
//        //localhost:8083/ratings/all-ratings/user/df78360b-deac-4d88-8b85-6bd1e9101c38
//

        ArrayList userRatings = restTemplate
                .getForObject("http://localhost:8083/ratings/all-ratings/user/" + user.getUserId(), ArrayList.class);

//

        loggerFactory.info("" + userRatings);
        user.setRatings(userRatings);

        return user;
    }

    @Override
    public List<User> getAllUser() {

        List<User> all = this.userRepository.findAll();
        for (User u : all) {

            ArrayList forObject = restTemplate.getForObject("http://localhost:8083/ratings/all-ratings/user/" + u.getUserId(), ArrayList.class);
            u.setRatings(forObject);

        }

        return all;
    }

    @Override
    public User updateUserById(User user, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
