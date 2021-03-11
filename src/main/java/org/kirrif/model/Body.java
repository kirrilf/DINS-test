package org.kirrif.model;

import java.util.Objects;

public class Body {
    private Integer body_length;
    private Integer body_width;
    private Integer body_height;
    private String body_style;

    public Body(){}

    public Body(Integer body_length,
                Integer body_width,
                Integer body_height,
                String body_style) {
        this.body_length = body_length;
        this.body_width = body_width;
        this.body_height = body_height;
        this.body_style = body_style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body body = (Body) o;
        return Objects.equals(body_length, body.body_length) && Objects.equals(body_width, body.body_width) && Objects.equals(body_height, body.body_height) && Objects.equals(body_style, body.body_style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body_length, body_width, body_height, body_style);
    }

    public Integer getBody_length() {
        return body_length;
    }

    public void setBody_length(Integer body_length) {
        this.body_length = body_length;
    }

    public Integer getBody_width() {
        return body_width;
    }

    public void setBody_width(Integer body_width) {
        this.body_width = body_width;
    }

    public Integer getBody_height() {
        return body_height;
    }

    public void setBody_height(Integer body_height) {
        this.body_height = body_height;
    }

    public String getBody_style() {
        return body_style;
    }

    public void setBody_style(String body_style) {
        this.body_style = body_style;
    }
}
