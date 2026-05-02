package com.scraper.infrastructure.scraping.engine;

import com.scraper.domain.ScraperDefinition;
import com.scraper.infrastructure.scraping.parser.DataExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class StaticHtmlEngine implements ScraperEngine {

    private final DataExtractor dataExtractor;

    public StaticHtmlEngine(DataExtractor dataExtractor) {
        this.dataExtractor = dataExtractor;
    }

    @Override
    public String fetch(ScraperDefinition scraper, String proxyHost, int proxyPort) throws Exception {
        Document doc = Jsoup.connect(scraper.getTargetUrl())
            .userAgent("Mozilla/5.0")
            .timeout(scraper.getRateLimitMs() != null ? scraper.getRateLimitMs() : 30000)
            .proxy(proxyHost, proxyPort)
            .get();
        return doc.html();
    }

    @Override
    public Map<String, Object> extractData(String html, ScraperDefinition scraper) throws Exception {
        Document doc = Jsoup.parse(html);
        return dataExtractor.extract(doc, scraper);
    }
}