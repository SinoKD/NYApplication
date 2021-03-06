package com.nyapplication.data_models;

/**
 * @author Sino K D
 * @since 8/3/18.
 * <p>
 * Model object to handle media meta data.
 * </p>
 */
public class MediaMetaData {

    private String url;
    private String format;
    private String height;
    private String width;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
