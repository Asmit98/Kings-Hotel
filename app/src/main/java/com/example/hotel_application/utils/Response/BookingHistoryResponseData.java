package com.example.hotel_application.utils.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingHistoryResponseData implements Serializable {
    @SerializedName("Booking_id")
    @Expose
    private Integer bookingId;
    @SerializedName("Customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("Booking_Date")
    @Expose
    private String bookingDate;
    @SerializedName("Payment_Method")
    @Expose
    private String paymentMethod;
    @SerializedName("Payment_Status")
    @Expose
    private Integer paymentStatus;
    @SerializedName("NumberOf_Rooms")
    @Expose
    private Integer numberOfRooms;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("Room_name")
    @Expose
    private String roomName;
    @SerializedName("Room_image")
    @Expose
    private String roomImage;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }
}
