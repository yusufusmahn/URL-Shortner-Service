package org.URLShortner.services;

import org.URLShortner.data.models.User;
import org.URLShortner.data.repositories.UserRepository;
import org.URLShortner.dtos.requests.UserRequest;
import org.URLShortner.dtos.responses.UserResponse;
import org.URLShortner.exceptions.UrlShortnerServiceException;
import org.URLShortner.security.JwtUtil;
import org.URLShortner.utils.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


        @Mock
        private UserRepository userRepository;

        @Mock
        private JwtUtil jwtUtil;

        @Mock
        private PasswordEncoder passwordEncoder;

        @InjectMocks
        private UserServiceImpl userService;

        @Test
        void testRegister_Success() {
            UserRequest request = new UserRequest();
            request.setUsername("john");
            request.setPassword("password");

            when(userRepository.findByUsername("john")).thenReturn(null);
            when(passwordEncoder.encode("password")).thenReturn("hashedPassword");

            User userToSave = Mapper.toEntity(request);
            userToSave.setPassword("hashedPassword");

            when(userRepository.save(any(User.class))).thenReturn(userToSave);

            UserResponse response = userService.register(request);

            assertNotNull(response);
            assertEquals("john", response.getUsername());
            assertEquals("User registered successfully", response.getMessage());
        }

    @Test
    void testAuthenticate_Success() {
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setPassword("password");

        User existingUser = new User();
        existingUser.setUsername("john");
        existingUser.setPassword("encodedPass");

        when(userRepository.findByUsername("john")).thenReturn(existingUser);
        when(passwordEncoder.matches("password", "encodedPass")).thenReturn(true);
        when(jwtUtil.generateToken("john")).thenReturn("jwt-token");

        String token = userService.authenticate(request);

        assertEquals("jwt-token", token);
    }

    @Test
    void testAuthenticate_InvalidUsername() {
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setPassword("password");

        when(userRepository.findByUsername("john")).thenReturn(null);

        UrlShortnerServiceException exception = assertThrows(
                UrlShortnerServiceException.class,
                () -> userService.authenticate(request)
        );

        assertEquals("Invalid username or password", exception.getMessage());
    }


    @Test
    void testAuthenticate_WrongPassword() {
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setPassword("password");

        User user = new User();
        user.setUsername("john");
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername("john")).thenReturn(user);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(false);

        UrlShortnerServiceException exception = assertThrows(
                UrlShortnerServiceException.class,
                () -> userService.authenticate(request)
        );

        assertEquals("Invalid username or password", exception.getMessage());
    }


}


