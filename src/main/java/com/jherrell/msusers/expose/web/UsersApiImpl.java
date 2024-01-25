package com.jherrell.msusers.expose.web;

import com.jherrell.expose.web.DefaultApiDelegate;
import com.jherrell.msusers.bussines.UsersService;
import com.jherrell.server_resource.model.api.UserRequest;
import com.jherrell.server_resource.model.api.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsersApiImpl implements DefaultApiDelegate {

    private UsersService service;

    @Override
    public ResponseEntity<UserResponse> registerUser(UserRequest userRequest) {
        UserResponse userResponse = service.registerUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }
}
