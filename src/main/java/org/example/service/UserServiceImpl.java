package org.example.service;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.IncompleteRequestBodyException;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        UserDto userDto = null;
        if(foundUser.isPresent()){
            userDto = modelMapper.map(foundUser.get(), UserDto.class);
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
        return userDto;
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
    public UserDto update(Long userId, UserDto updateUserDto) {
        UserDto matchingUserDto = findById(userId);
        if(matchingUserDto==null){
            throw new UserNotFoundException("User with id " +
                    updateUserDto.getUserId() + " not found");
        }

        if(updateUserDto.getUserName()==null || updateUserDto.getUserEmail()==null) {
            throw new IncompleteRequestBodyException(
                    "Request body must contains the following fields: userName and userEmail");
        }
        updateUserDto.setUserId(userId);
        User updatedUser = userRepository.save(modelMapper.map(updateUserDto, User.class));
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto partialUpdate(Long userId, UserDto updateUserDto) {
        UserDto matchingUserDto = findById(userId);
        if(matchingUserDto!=null) {
            if(updateUserDto.getUserName()!=null){
                matchingUserDto.setUserName(updateUserDto.getUserName());
            }
            if(updateUserDto.getUserEmail()!=null){
                matchingUserDto.setUserEmail(updateUserDto.getUserEmail());
            }
        }
        if(matchingUserDto==null){
            throw new UserNotFoundException("User with id " +
                    updateUserDto.getUserId() + " not found");
        }
        User updatedUser = userRepository.save(modelMapper.map(matchingUserDto, User.class));
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteById(Long userId) {
        UserDto userDto = findById(userId);
        userRepository.deleteById(userId);
    }
}
















