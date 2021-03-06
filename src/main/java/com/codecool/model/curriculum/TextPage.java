package com.codecool.model.curriculum;

import java.io.Serializable;

public class TextPage extends Page implements Serializable {

    private String content;

    public TextPage(String id, String title, String content) {
        super(id, title);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
