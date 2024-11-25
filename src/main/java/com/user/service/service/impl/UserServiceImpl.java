package com.user.service.service.impl;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.external.service.FeignClientHotelService;
import com.user.service.repository.UserRepository;
import com.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignClientHotelService feignClientHotelService;

    @Override
    public User addUser(User user) {
        try {
            if (user != null) {
                // Generate a random UUID
                UUID randomUserId = UUID.randomUUID();

                // Extract the least significant bits and use them as a Long
                long longUserId = randomUserId.getLeastSignificantBits();

                // Set the userId (converted to Long)
                user.setUserId(longUserId);
                return userRepository.save(user); // Return the saved user
            } else {
                logger.warn("User object is null");
                return null; // Return null if user is null
            }
        } catch (Exception exception) {
            logger.error("Error occurred while adding user: ", exception);
            throw new RuntimeException("Unable to add user at this time.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll(); // Assuming you want to fetch all users from DB
        } catch (Exception exception) {
            logger.error("Error occurred while fetching all users: ", exception);
            throw new RuntimeException("Unable to fetch users at this time.");
        }
    }

    @Override
    public User getUserByUserId(Long userId) {
        try {
            // Fetch the user from the repository
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                throw new RuntimeException("User not found for ID: " + userId);
            }

            // Fetch the rating list from the external service
            String ratingsUrl = "http://RATINGSERVICE/ratings/user/" + userId;

            try {
                List<Rating> ratingList = restTemplate.exchange(ratingsUrl, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Rating>>() {
                }).getBody();

                if (ratingList != null) {
                    for (Rating rating : ratingList) {
                        Long hotelId = rating.getHotelId();
                        String hotelByIdUrl = "http://HOTELSERVICE/hotels/" + hotelId;

                        try {
                            //Hotel hotel = restTemplate.getForObject(hotelByIdUrl, Hotel.class);
                            Hotel hotel = feignClientHotelService.getHotel(hotelId);
                            rating.setHotel(hotel);
                        } catch (Exception hotelFetchException) {
                            logger.error("Error occurred while fetching hotel for ID: " + hotelId, hotelFetchException);
                            throw new RuntimeException("Unable to fetch hotel with ID: " + hotelId);
                        }
                    }
                    user.setRatingList(ratingList);
                }
            } catch (Exception ratingsFetchException) {
                logger.error("Error occurred while fetching ratings for user ID: " + userId, ratingsFetchException);
                throw new RuntimeException("Unable to fetch ratings for the user with ID: " + userId);
            }

            return user;

        } catch (Exception exception) {
            logger.error("Error occurred while fetching user by ID: " + userId, exception);
            throw new RuntimeException("Unable to fetch user with the provided ID.");
        }
    }

    @Override
    public User deleteUserByUserId(Long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                userRepository.delete(userOptional.get()); // Delete the user if found
                return userOptional.get(); // Return the deleted user
            } else {
                logger.warn("User with ID " + userId + " not found.");
                return null; // Return null if user not found
            }
        } catch (Exception exception) {
            logger.error("Error occurred while deleting user: ", exception);
            throw new RuntimeException("Unable to delete user at this time.");
        }
    }
}
