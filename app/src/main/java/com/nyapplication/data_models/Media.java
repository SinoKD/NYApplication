package com.nyapplication.data_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Sino K D
 * @since 8/3/18.
 * <p>
 * Model object to handle Media data.
 * </p>
 */

public class Media {

    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    private int approved_for_syndication;
    @SerializedName("media-metadata")
    private ArrayList<MediaMetaData> media_metadata;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getApproved_for_syndication() {
        return approved_for_syndication;
    }

    public void setApproved_for_syndication(int approved_for_syndication) {
        this.approved_for_syndication = approved_for_syndication;
    }

    public ArrayList<MediaMetaData> getMedia_metadata() {
        return media_metadata;
    }

    public void setMedia_metadata(ArrayList<MediaMetaData> media_metadata) {
        this.media_metadata = media_metadata;
    }
}
