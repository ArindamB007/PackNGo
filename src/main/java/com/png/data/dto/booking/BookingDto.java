package com.png.data.dto.booking;

import com.png.data.dto.room.RoomDto;
import com.png.data.dto.user.UserContext;

public class BookingDto {
    private Long idBooking;
    private RoomDto room;
    private String cancelledTimestamp;
    private String checkInTimestamp;
    private String checkOutTimestamp;
    private String bookingTypeCode;
    private UserContext bookedByUser;
    private UserContext cancelledByUser;

    public Long getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Long idBooking) {
        this.idBooking = idBooking;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public String getCancelledTimestamp() {
        return cancelledTimestamp;
    }

    public void setCancelledTimestamp(String cancelledTimestamp) {
        this.cancelledTimestamp = cancelledTimestamp;
    }

    public String getCheckInTimestamp() {
        return checkInTimestamp;
    }

    public void setCheckInTimestamp(String checkInTimestamp) {
        this.checkInTimestamp = checkInTimestamp;
    }

    public String getCheckOutTimestamp() {
        return checkOutTimestamp;
    }

    public void setCheckOutTimestamp(String checkOutTimestamp) {
        this.checkOutTimestamp = checkOutTimestamp;
    }

    public String getBookingTypeCode() {
        return bookingTypeCode;
    }

    public void setBookingTypeCode(String bookingTypeCode) {
        this.bookingTypeCode = bookingTypeCode;
    }

    public UserContext getBookedByUser() {
        return bookedByUser;
    }

    public void setBookedByUser(UserContext bookedByUser) {
        this.bookedByUser = bookedByUser;
    }

    public UserContext getCancelledByUser() {
        return cancelledByUser;
    }

    public void setCancelledByUser(UserContext cancelledByUser) {
        this.cancelledByUser = cancelledByUser;
    }
}
