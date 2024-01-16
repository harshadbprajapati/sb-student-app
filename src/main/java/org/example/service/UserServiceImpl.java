package org.example.service;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        // Create a type map configuration for User to UserDto
        TypeMap<User, UserDto> userToUserDtoTypeMap = modelMapper.createTypeMap(User.class, UserDto.class);
        userToUserDtoTypeMap.addMapping(User::getId, UserDto::setUserId);
        userToUserDtoTypeMap.addMapping(src -> src.getName(), UserDto::setUserName);
        userToUserDtoTypeMap.addMapping(User::getEmail, UserDto::setUserEmail);

        // Create a type map configuration for UserDto to User
        TypeMap<UserDto, User> userDtoToUserTypeMap = modelMapper.createTypeMap(UserDto.class, User.class);
        userDtoToUserTypeMap.addMapping(src -> src.getUserId(), User::setId);
        userDtoToUserTypeMap.addMapping(UserDto::getUserName, User::setName);
        userDtoToUserTypeMap.addMapping(src -> src.getUserEmail(), User::setEmail);
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto findById(int userId) {
        return null;
    }

    @Override
    public UserDto save(UserDto userDto) {
        // Convert UserDto into User JPA Entity
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        // Convert User JPA Entity into UserDto
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto update(UserDto user) {
        return null;
    }

    @Override
    public void deleteById(int userId) {

    }
}


