package com.techease.speedracerz.dataModels.myTicketsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTicketsData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("noOfTickets")
    @Expose
    private String noOfTickets;
    @SerializedName("ticketBookedAt")
    @Expose
    private String ticketBookedAt;
    @SerializedName("eventId")
    @Expose
    private Integer eventId;
    @SerializedName("eventTitle")
    @Expose
    private String eventTitle;
    @SerializedName("eventCategory")
    @Expose
    private String eventCategory;
    @SerializedName("eventimage")
    @Expose
    private String eventimage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(String noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String getTicketBookedAt() {
        return ticketBookedAt;
    }

    public void setTicketBookedAt(String ticketBookedAt) {
        this.ticketBookedAt = ticketBookedAt;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventimage() {
        return eventimage;
    }

    public void setEventimage(String eventimage) {
        this.eventimage = eventimage;
    }

}
