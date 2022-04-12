package com.example.hotel_application.utils.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomResponse {
    @SerializedName("Rooms")
    @Expose
    private List<RoomResponseData> rooms = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public List<RoomResponseData> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomResponseData> rooms) {
        this.rooms = rooms;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
