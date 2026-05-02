package com.scraper.interfaces.dto.proxy;

import lombok.Data;

@Data
public class CreateProxyRequest {
    private String host;
    private Integer port;
    private String protocol;
    private String username;
    private String password;
}