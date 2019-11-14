package com.example.homework3;

import java.util.List;

public class Cat_image {
    private String url;
    private String id;

    private List<breeds> breeds;

    public List<breeds> getBreeds() {
        return breeds;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
