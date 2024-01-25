package com.jherrell.msusers.business;

import static com.jherrell.msusers.utils.TestUtils.generateObject;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.jherrell.msusers.Exception.RegexMatchException;
import com.jherrell.msusers.Exception.UserEmailExistException;
import com.jherrell.msusers.bussines.impl.UserServiceImpl;
import com.jherrell.msusers.config.ApplicationProperties;
import com.jherrell.msusers.mapper.UserMapper;
import com.jherrell.msusers.model.db.UserEntity;
import com.jherrell.msusers.repository.UserRepository;
import com.jherrell.server_resource.model.api.UserRequest;
import com.jherrell.server_resource.model.api.UserResponse;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    ApplicationProperties properties;

    UserRequest userRequest;
    UserResponse userResponse;
    UserEntity userEntity;
    UserEntity userEntitySaved;

    @BeforeEach
    void setUp() throws IOException {
        userRequest = generateObject("data/api/userRequest.json", UserRequest.class);
        userEntity = generateObject("data/api/userRequest.json", UserEntity.class);
        userEntitySaved = generateObject("data/api/userEntitySaved.json", UserEntity.class);
        userResponse = generateObject("data/api/userResponse.json", UserResponse.class);
    }

    @Test
    void registerUserWhenRequestIsValid() {
        when(properties.getPasswordRgx())
                .thenReturn("^[\\d]+$");
        when(userMapper.toEntity(any()))
                .thenReturn(userEntity);
        when(userRepository.existsByEmail(anyString()))
                .thenReturn(false);
        when(userRepository.save(userEntity))
                .thenReturn(userEntitySaved);
        when(userMapper.toResponse(userEntitySaved))
                .thenReturn(userResponse);

        assertNotNull(userService.registerUser(userRequest));
        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void throwExceptionWhenPasswordRegexNoMatch() {
        userRequest.setPassword("aaaa");
        when(properties.getPasswordRgx())
                .thenReturn("^[\\d]+$");

        assertThrows(RegexMatchException.class,
                () -> userService.registerUser(userRequest));
    }

    @Test
    void throwExceptionWhenUserEmailExist() {

        when(properties.getPasswordRgx())
                .thenReturn("^[\\d]+$");
        when(userRepository.existsByEmail(anyString()))
                .thenReturn(true);

        assertThrows(UserEmailExistException.class,
                () -> userService.registerUser(userRequest));
    }
}
