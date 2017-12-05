package com.png.data.dto.room;

import com.png.data.entity.RoomTypeObject;

public class AvailableRoomTypeObject extends RoomTypeObject {
    private Integer countAvailable;
    public Integer getCountAvailable() {
        return countAvailable;
    }

    public void setCountAvailable(Integer countAvailable) {
        this.countAvailable = countAvailable;
    }
}

/*public class AvailableRoomTypes{
    private Collection<AvailableRoomTypeObject> availableRoomTypes;

}*/
