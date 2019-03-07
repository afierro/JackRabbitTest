package com.github.woonsan.oak.app.simple;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private CMSRepository repository;

    public String getTextContentById(final String nodeName) {
        return repository.getText(nodeName);
    }

    public String publishTextContent(final String text) {
        return repository.publishText(text);
    }

}
