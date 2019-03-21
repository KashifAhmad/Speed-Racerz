package com.techease.speedracerz.dataModels.eventDetailModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventTicketsModel {


    @SerializedName("users")
    @Expose
    private List<Object> users = null;
    @SerializedName("racers")
    @Expose
    private List<EventRacerModel> racers = null;

    public List<Object> getUsers() {
        return users;
    }

    public void setUsers(List<Object> users) {
        this.users = users;
    }

    public List<EventRacerModel> getRacers() {
        return racers;
    }

    public void setRacers(List<EventRacerModel> racers) {
        this.racers = racers;
    }

}
