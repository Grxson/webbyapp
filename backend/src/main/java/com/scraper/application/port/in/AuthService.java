package com.scraper.application.port.in;

import com.scraper.interfaces.dto.auth.*;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    UserDto getCurrentUser();
}