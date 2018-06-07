package com.example.bulbul.onlinefileuploader;

/**
 * Created by bulbul on 3/2/2018.
 */

public class Upload {
    public String name;
    public String url;
    public String id;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }



    public Upload(String name, String url, String id) {
        this.name = name;
        this.url = url;
        this.id=id;

    }




public String getId(){
    return  id;
}
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
