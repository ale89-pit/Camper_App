package com.camper_app_server.security.service;

import com.camper_app_server.security.payload.LoginDto;
import com.camper_app_server.security.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
