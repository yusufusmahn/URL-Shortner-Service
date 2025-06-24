package org.URLShortner.services;

import org.URLShortner.data.models.User;
import org.URLShortner.data.repositories.UserRepository;
import org.URLShortner.dtos.requests.UserRequest;
import org.URLShortner.dtos.responses.UserResponse;
import org.URLShortner.security.JwtUtil;
import org.URLShortner.exceptions.UrlShortnerServiceException;
import org.URLShortner.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new UrlShortnerServiceException("Username already exists");
        }

        User user = Mapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return Mapper.toResponse(user, "User registered successfully");
    }


    @Override
    public String authenticate(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null || !passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new UrlShortnerServiceException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
