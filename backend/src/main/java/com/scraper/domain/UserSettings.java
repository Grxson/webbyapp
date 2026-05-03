package com.scraper.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_settings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String theme;

    private String timezone;

    @Column(name = "email_notifications")
    private boolean emailNotifications;

    @Column(name = "dashboard_layout", columnDefinition = "jsonb")
    private String dashboardLayout;
}