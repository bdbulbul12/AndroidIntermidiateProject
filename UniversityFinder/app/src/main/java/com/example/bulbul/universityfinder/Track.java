package com.example.bulbul.universityfinder;

/**
 * Created by bulbul on 2/1/2018.
 */

public class Track {
    private String trackId;
    private String trackName;
    private double trackRating;

    public Track(){

    }


    public Track(String trackId, String trackName, double trackRating) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackRating = trackRating;
    }


    public String getTrackId() {
        return trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public double getTrackRating() {
        return trackRating;
    }
}
