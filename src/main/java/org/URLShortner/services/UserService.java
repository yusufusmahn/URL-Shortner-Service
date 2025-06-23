package org.URLShortner.services;

import org.URLShortner.dtos.requests.UserRequest;
import org.URLShortner.dtos.responses.UserResponse;

public interface UserService {
    UserResponse register(UserRequest userRequest);
    String authenticate(UserRequest userRequest);
}
