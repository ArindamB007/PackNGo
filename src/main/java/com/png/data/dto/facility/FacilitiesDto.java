package com.png.data.dto.facility;

import java.util.HashMap;

public class FacilitiesDto {
	HashMap<String,Boolean> facilitiesMap = new HashMap<>();

	public HashMap<String, Boolean> getFacilitiesMap() {
		return facilitiesMap;
	}

	public void setFacilitiesMap(HashMap<String, Boolean> facilitiesMap) {
		this.facilitiesMap = facilitiesMap;
	}
}
