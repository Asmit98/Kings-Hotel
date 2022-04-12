package com.example.hotel_application.utils.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RoomResponseData implements Serializable {
    @SerializedName("Room_id")
    @Expose
    private Integer roomId;
    @SerializedName("Room_name")
    @Expose
    private String roomName;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("Room_desc")
    @Expose
    private String roomDesc;
    @SerializedName("Image")
    @Expose
    private String image;

    @SerializedName("Cat_id")
    @Expose
    private List<String> catId = null;

    @SerializedName("Available_No_of_Rooms")
    @Expose
    private Integer availableNoOfRooms;

    public Integer getAvailableNoOfRooms() {
        return availableNoOfRooms;
    }

    public void setAvailableNoOfRooms(Integer availableNoOfRooms) {
        this.availableNoOfRooms = availableNoOfRooms;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getCatId() {
        return catId;
    }

    public void setCatId(List<String> catId) {
        this.catId = catId;
    }


}
