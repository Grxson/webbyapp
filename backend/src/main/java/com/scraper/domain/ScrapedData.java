package com.scraper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "scraped_data")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ScrapedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private ScrapeJob job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scraper_id", nullable = false)
    private ScraperDefinition scraper;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, Object> data;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "scraped_at")
    private LocalDateTime scrapedAt;

    @PrePersist
    protected void onCreate() {
        scrapedAt = LocalDateTime.now();
    }
}