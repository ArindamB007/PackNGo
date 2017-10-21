package com.png.data.domain;

import com.png.data.entity.RoomType;

import java.util.Collection;

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
