package com.scraper.infrastructure.scraping.parser;

import com.scraper.domain.ScraperDefinition;
import com.scraper.domain.Selector;
import com.scraper.infrastructure.persistence.SelectorRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class DataExtractor {

    private final SelectorRepository selectorRepository;
    private final SelectorParser selectorParser;

    public DataExtractor(SelectorRepository selectorRepository, SelectorParser selectorParser) {
        this.selectorRepository = selectorRepository;
        this.selectorParser = selectorParser;
    }

    public Map<String, Object> extract(Document doc, ScraperDefinition scraper) {
        Map<String, Object> result = new HashMap<>();
        List<Selector> selectors = selectorRepository.findByScraperId(scraper.getId());

        for (Selector selector : selectors) {
            Elements elements = doc.select(selector.getCssSelector());
            if (selector.getParentSelector() != null) {
                Element parent = doc.selectFirst(selector.getParentSelector());
                if (parent != null) {
                    elements = parent.select(selector.getCssSelector());
                }
            }

            if (!elements.isEmpty()) {
                String value = selectorParser.extractValue(elements.first(), selector);
                if (selector.getRegexFilter() != null) {
                    value = applyRegexFilter(value, selector.getRegexFilter());
                }
                result.put(selector.getFieldName(), value);
            }
        }

        return result;
    }

    private String applyRegexFilter(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return value;
    }
}