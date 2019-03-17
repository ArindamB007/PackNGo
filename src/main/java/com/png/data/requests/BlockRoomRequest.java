package com.png.data.requests;

import com.png.data.dto.room.RoomDto;
import com.png.data.dto.user.UserContext;

import java.util.List;

public class BlockRoomRequest {
    private String checkInTimestamp;
    private String checkOutTimestamp;
    private List<RoomDto> roomsList;
    private UserContext bookByUser;

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

    public List<RoomDto> getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(List<RoomDto> roomsList) {
        this.roomsList = roomsList;
    }

    public UserContext getBookByUser() {
        return bookByUser;
    }

    public void setBookByUser(UserContext bookByUser) {
        this.bookByUser = bookByUser;
    }
}
