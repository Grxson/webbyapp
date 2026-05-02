package com.scraper.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "selectors")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Selector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scraper_id", nullable = false)
    private ScraperDefinition scraper;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "css_selector", nullable = false)
    private String cssSelector;

    private String attribute;

    private String regexFilter;

    private String parentSelector;

    private String transform;
}