package com.jherrell.msusers.expose.web;

import static com.jherrell.msusers.utils.TestUtils.generateObject;

import com.jherrell.msusers.bussines.UsersService;
import com.jherrell.server_resource.model.api.UserRequest;
import com.jherrell.server_resource.model.api.UserResponse;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class UserApiImplTest {

    @InjectMocks
    UsersApiImpl usersApi;

    @Mock
    UsersService usersService;

    UserRequest userRequest;
    UserResponse userResponse;

    @BeforeEach
    void setUp() throws IOException {
        userRequest = generateObject("data/api/userRequest.json", UserRequest.class);
        userResponse = generateObject("data/api/userResponse.json", UserResponse.class);
    }

    @Test
    void registerUserWhenUserRequestIsValid() {
        Mockito.when(usersService.registerUser(userRequest))
                .thenReturn(userResponse);

        var response = usersApi.registerUser(userRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
}
