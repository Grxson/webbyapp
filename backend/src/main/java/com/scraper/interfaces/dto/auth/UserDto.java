package com.scraper.interfaces.dto.auth;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
}