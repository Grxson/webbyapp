package com.scraper.infrastructure.persistence;

import com.scraper.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findByUserIdAndIsReadFalse(Long userId, Pageable pageable);
    long countByUserIdAndIsReadFalse(Long userId);
}