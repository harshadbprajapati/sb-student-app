package org.example.restcontroller;

import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/users/{userId}")
    public UserDto updateUser(@RequestBody UserDto userDto,
                              @PathVariable Long userId) {
        return userService.update(userId, userDto);
    }

    @PatchMapping("/users/{userId}")
    public UserDto partialUpdateUser(@RequestBody UserDto userDto,
                                     @PathVariable Long userId) {
        return userService.partialUpdate(userId, userDto);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUserById(@PathVariable Long userId){
        userService.deleteById(userId);
        return "User with id "+userId+" was deleted successfully.";
    }
}







