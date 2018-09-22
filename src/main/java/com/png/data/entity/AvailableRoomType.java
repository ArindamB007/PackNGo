package com.png.data.entity;

import java.util.ArrayList;
import java.util.List;

public class AvailableRoomType extends RoomType{
    private Integer availableCount;

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }
    
    public List<Integer> getExtraOccupancyList(Integer occupancy){
    	List<Integer> extraOccupancy = new ArrayList<Integer>(); 
    	if (occupancy == null) return extraOccupancy;
    	for (int i=0; i<=occupancy;i++) {
    		extraOccupancy.add(i);
    	}
    	return extraOccupancy;
    }
}
