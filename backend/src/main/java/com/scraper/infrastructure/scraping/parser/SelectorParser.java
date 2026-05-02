package com.scraper.infrastructure.scraping.parser;

import com.scraper.domain.Selector;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class SelectorParser {

    public String extractValue(Element element, Selector selector) {
        if (element == null) {
            return null;
        }

        if (selector.getAttribute() != null && !selector.getAttribute().equals("text")) {
            return element.attr(selector.getAttribute());
        }

        return element.text();
    }
}