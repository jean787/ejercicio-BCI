package com.jherrell.msusers.mapper;

import com.jherrell.msusers.model.db.PhoneEntity;
import com.jherrell.msusers.model.db.UserEntity;
import com.jherrell.server_resource.model.api.Phone;
import com.jherrell.server_resource.model.api.UserRequest;
import com.jherrell.server_resource.model.api.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserRequest userRequest);

    UserResponse toResponse(UserEntity userEntity);

    PhoneEntity toPhoneEntity(Phone phone);
}
