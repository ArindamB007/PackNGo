package com.png.data.dto.checkinoutdetails;

import java.sql.Timestamp;

public class CheckInOutDetailsDto {
    private String checkInTimestamp;
    private String checkOutTimestamp;
    private Long idProperty;

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

    public Long getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(Long idProperty) {
        this.idProperty = idProperty;
    }
}
