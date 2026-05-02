package com.scraper.domain;

import com.scraper.domain.enums.ProxyStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "proxy_pool")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProxyConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private Integer port;

    private String protocol;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProxyStatus status;

    @Column(name = "success_count")
    private Integer successCount = 0;

    @Column(name = "fail_count")
    private Integer failCount = 0;

    @Column(name = "last_checked")
    private LocalDateTime lastChecked;

    @Column(name = "response_time_ms")
    private Integer responseTimeMs;
}