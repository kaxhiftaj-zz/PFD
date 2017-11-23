package com.techease.pfd.Controller;

/**
 * Created by Adam Noor on 23-Nov-17.
 */

public class GraphModel {

    private String Story;
    private String id;
    private String updated_time;


    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }


}
