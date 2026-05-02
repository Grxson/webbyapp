package com.scraper.interfaces.dto.job;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobLogDto {
    private Long id;
    private String level;
    private String message;
    private String details;
    private LocalDateTime loggedAt;
}