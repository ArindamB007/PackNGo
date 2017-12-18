package com.png.data.entity;

import java.math.BigDecimal;
import java.util.Set;

public class AvailableRoomType extends RoomType{
    private Integer availableCount;

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }
}
