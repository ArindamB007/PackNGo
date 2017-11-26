package com.png.data.dto.room;

import com.png.data.entity.RoomType;

public class AvailableRoomType extends RoomType{
    private Integer countAvailable;
    public Integer getCountAvailable() {
        return countAvailable;
    }

    public void setCountAvailable(Integer countAvailable) {
        this.countAvailable = countAvailable;
    }
}

/*public class AvailableRoomTypes{
    private Collection<AvailableRoomType> availableRoomTypes;

}*/
