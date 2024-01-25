package com.jherrell.msusers.bussines.impl;

import static com.jherrell.msusers.utils.Constants.MESSAGE_EMAIL_FOUND;
import static com.jherrell.msusers.utils.Constants.RGX_PASSWORD_NO_MATCH;
import static com.jherrell.msusers.utils.Utils.generateDateWithFormat;
import static com.jherrell.msusers.utils.Utils.generateToken;
import static com.jherrell.msusers.utils.Utils.matchesRegex;

import com.jherrell.msusers.Exception.RegexMatchException;
import com.jherrell.msusers.Exception.UserEmailExistException;
import com.jherrell.msusers.bussines.UsersService;
import com.jherrell.msusers.config.ApplicationProperties;
import com.jherrell.msusers.mapper.UserMapper;
import com.jherrell.msusers.model.db.UserEntity;
import com.jherrell.msusers.repository.UserRepository;
import com.jherrell.server_resource.model.api.UserRequest;
import com.jherrell.server_resource.model.api.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UsersService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private ApplicationProperties properties;

    @Transactional
    @Override
    public UserResponse registerUser(UserRequest userRequest) {

        if (!matchesRegex(userRequest.getPassword(), properties.getPasswordRgx())) {
            throw new RegexMatchException(RGX_PASSWORD_NO_MATCH);
        }

        if (existUserByEmail(userRequest.getEmail())) {
            throw new UserEmailExistException(MESSAGE_EMAIL_FOUND);
        }

        UserEntity userEntity = userMapper.toEntity(userRequest);
        userEntity.setCreated(generateDateWithFormat());
        userEntity.setModified(generateDateWithFormat());
        userEntity.setLastLogin(generateDateWithFormat());
        userEntity.setToken(generateToken());
        userEntity.setIsActive(true);
        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
