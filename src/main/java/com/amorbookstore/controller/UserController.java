package com.amorbookstore.controller;

import com.amorbookstore.dto.RegisterRequest;
import com.amorbookstore.dto.UserDTO;
import com.amorbookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
