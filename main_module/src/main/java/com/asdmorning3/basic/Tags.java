package com.asdmorning3.basic;

import java.awt.*;

public class Tags {
    private String description_;
    private Color color_;

    public Tags(String description, Color color)
    {
        description_ = description;
        color_ = color;
    }

    public String getDescription() {
        return description_;
    }
}
