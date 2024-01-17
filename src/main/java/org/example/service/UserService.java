package org.example.service;

import org.example.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> findAll();
    public UserDto findById(Long userId);

    public UserDto save(UserDto userDto);

    public UserDto update(Long id, UserDto userDto);

    public UserDto partialUpdate(Long userId, UserDto userDto);

    public void deleteById(Long userId);
}


