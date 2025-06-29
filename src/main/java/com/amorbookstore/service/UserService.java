package com.amorbookstore.service;

import com.amorbookstore.dto.UserDTO;
import com.amorbookstore.dto.RegisterRequest;

public interface UserService {
    UserDTO registerUser(RegisterRequest registerRequest);
    UserDTO getUserById(Long id);
    UserDTO getUserByUsername(String username);
}
