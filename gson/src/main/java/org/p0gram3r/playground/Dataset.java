package org.p0gram3r.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Dataset {
    public String album_id;
    public String album_title;

    @SerializedName("album_images")
    List<AlbumImages> images = new ArrayList<AlbumImages>();

    private Map<String, Object> otherProperties = new HashMap<String, Object>();

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public Object get(String name) {
        return otherProperties.get(name);
    }
}
