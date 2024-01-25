package com.jherrell.msusers.bussines;

import com.jherrell.server_resource.model.api.UserRequest;
import com.jherrell.server_resource.model.api.UserResponse;

public interface UsersService {

    UserResponse registerUser(UserRequest userRequest);

    boolean existUserByEmail(String email);
}
