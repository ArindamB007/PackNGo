package com.png.data.dto.checkinoutdetails;


public class CheckInOutDetailsDto {
    private String checkInTimestamp;
    private String checkOutTimestamp;

    public String getCheckInTimestamp() {
        return checkInTimestamp;
    }

    public void setCheckInTimestamp(String checkInTimestamp) {
        this.checkInTimestamp = checkInTimestamp + " 11:00:00";
    }

    public String getCheckOutTimestamp() {
        return checkOutTimestamp;
    }

    public void setCheckOutTimestamp(String checkOutTimestamp) {
        this.checkOutTimestamp = checkOutTimestamp + " 10:00:00";
    }
}
