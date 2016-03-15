package org.p0gram3r.playground;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTestMain {

    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();

        // how to rename a field if we can't modify the source code
        builder.setFieldNamingStrategy(new FieldNamingStrategy() {
            @Override
            public String translateName(Field f) {
                if (f.getName().equals("albumId")) {
                    return "album_id";
                } else {
                    return f.getName();
                }
            }
        });

        Gson gson = builder.serializeNulls().setPrettyPrinting().create();

        Albums albums = new Albums();
        albums.title = "Free Music Archive - Albums";
        albums.message = "";
        albums.total = "11259";
        albums.total_pages = 2252;
        albums.page = 1;
        albums.limit = "5";
        // System.out.println(gson.toJson(albums));
        // System.out.println();

        Dataset dataset = new Dataset();
        dataset.album_id = "7596";
        dataset.album_title = "Album 1";
        // System.out.println(gson.toJson(dataset));
        // System.out.println();

        AlbumImages image = new AlbumImages();
        image.image_id = "1";
        // System.out.println(gson.toJson(image));
        // System.out.println();

        dataset.images.add(image);
        albums.dataset.add(dataset);
        System.out.println(gson.toJson(albums));
    }

}
