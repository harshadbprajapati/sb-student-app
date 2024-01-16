package org.example.service;

import org.example.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> findAll();
    public UserDto findById(int userId);

    public UserDto save(UserDto userDto);

    public UserDto update(UserDto userDto);

    public void deleteById(int userId);
}


