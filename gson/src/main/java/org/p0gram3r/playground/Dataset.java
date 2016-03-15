package org.p0gram3r.playground;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Dataset {
    public String album_id;
    public String album_title;

    @SerializedName("album_images")
    List<AlbumImages> images = new ArrayList<AlbumImages>();
}
