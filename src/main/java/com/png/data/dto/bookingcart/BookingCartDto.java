package com.png.data.dto.bookingcart;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.checkinoutdetails.InvoiceCheckInOutDetailsDto;
import com.png.data.dto.property.PropertyDto;
import com.png.data.dto.user.UserContext;

import java.util.List;

public class BookingCartDto {
    private InvoiceCheckInOutDetailsDto checkInOutDetails;
    private UserContext userContext;
    private PropertyDto selectedProperty;
    private List<AvailableRoomTypeDto> selectedRoomTypes;

    public InvoiceCheckInOutDetailsDto getCheckInOutDetails() {
        return checkInOutDetails;
    }

    public void setCheckInOutDetails(InvoiceCheckInOutDetailsDto checkInOutDetails) {
        this.checkInOutDetails = checkInOutDetails;
    }

    public UserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }

    public PropertyDto getSelectedProperty() {
        return selectedProperty;
    }

    public void setSelectedProperty(PropertyDto selectedProperty) {
        this.selectedProperty = selectedProperty;
    }

    public List<AvailableRoomTypeDto> getSelectedRoomTypes() {
        return selectedRoomTypes;
    }

    public void setSelectedRoomTypes(List<AvailableRoomTypeDto> selectedRoomTypes) {
        this.selectedRoomTypes = selectedRoomTypes;
    }
}
