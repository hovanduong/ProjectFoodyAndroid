package com.example.foody2.Model;

import java.util.List;

public class DealModel {
    private List<String> image;

    public DealModel(List<String> image) {
        this.image = image;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
}
