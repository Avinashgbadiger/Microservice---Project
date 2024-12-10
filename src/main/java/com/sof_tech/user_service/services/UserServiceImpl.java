package com.sof_tech.user_service.services;

import com.sof_tech.user_service.exception.ResourceNotFoundException;
import com.sof_tech.user_service.model.Hotel;
import com.sof_tech.user_service.model.Rating;
import com.sof_tech.user_service.model.User;
import com.sof_tech.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Rating[] userRatings = restTemplate
                .getForObject("http://RATINGSERVICE/ratings/all-ratings/user/" + user.getUserId(), Rating[].class);

//
        List<Rating> list = Arrays.stream(userRatings).toList();

        list.stream().map(rating -> {
            System.out.println(rating.getHotelId());
            Hotel forObject = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/hotel/" + rating.getHotelId(), Hotel.class);

            rating.setHotel(forObject);
            return new Rating();
        }).collect(Collectors.toList());

        loggerFactory.info("" + userRatings);
        user.setRatings(list);

        return user;
    }

    @Override
    public List<User> getAllUser() {

        List<User> all = this.userRepository.findAll();
        for (User u : all) {

            Rating[] forObject = restTemplate.getForObject("http://RATINGSERVICE/ratings/all-ratings/user/" + u.getUserId(), Rating[].class);


            List<Rating> ratings1 = Arrays.stream(forObject).toList();
            ratings1.stream().map(rating -> {
                Hotel forObject1 = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/hotel/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(forObject1);
                return rating;
            }).collect(Collectors.toList());

            u.setRatings(ratings1);

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
