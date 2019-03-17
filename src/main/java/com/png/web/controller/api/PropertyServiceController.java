package com.png.web.controller.api;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.booking.BookingDto;
import com.png.data.dto.checkinoutdetails.CheckInOutSearchParamsDto;
import com.png.data.dto.property.PropertyDto;
import com.png.data.dto.room.RoomDto;
import com.png.data.requests.BlockRoomRequest;
import com.png.data.responses.ApiResponse;
import com.png.util.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/property")
public class PropertyServiceController extends ApiBase {

    @RequestMapping(value = "/search_room_types", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> searchRoom(@RequestBody CheckInOutSearchParamsDto checkInSearchParams) {
        List<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
        try {
            System.out.println("Checkin Date : " + checkInSearchParams.getCheckInTimestamp());
            System.out.println("Checkin Date Converted : " +
                    DateFormatter.getTimestampFromString(checkInSearchParams.getCheckInTimestamp()));
            System.out.println("Checkout Date : " + checkInSearchParams.getCheckOutTimestamp());
            System.out.println("Checkout Date Converted : " +
                    DateFormatter.getTimestampFromString(checkInSearchParams.getCheckOutTimestamp()));
            List<AvailableRoomTypeDto> availableRoomTypeDtos =
                    roomTypeService.getAvailableRoomTypes(
                            DateFormatter.getTimestampFromString(checkInSearchParams.getCheckInTimestamp()),
                            DateFormatter.getTimestampFromString(checkInSearchParams.getCheckOutTimestamp()),
                            checkInSearchParams.getIdProperty());
            System.out.println(availableRoomTypeDtos);
            return new ResponseEntity<>(availableRoomTypeDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get_available_rooms", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> getAvailableRooms(@RequestBody CheckInOutSearchParamsDto checkInSearchParams) {
        String checkInDate = checkInSearchParams.getCheckInTimestamp();
        String checkOutDate = checkInSearchParams.getCheckOutTimestamp();
        Long idProperty = checkInSearchParams.getIdProperty();
        try {
            List<RoomDto> availableRooms = roomMaintenanceService.getAvailableRoomsForProperty(
                    checkInDate, checkOutDate, idProperty);
            return new ResponseEntity<>(availableRooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/save_blocked_rooms_booking", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> saveBlockedRooms(@RequestBody BlockRoomRequest blockRoomRequest) {
        try {
            roomMaintenanceService.saveBlockedRooms(
                    blockRoomRequest.getCheckInTimestamp(),
                    blockRoomRequest.getCheckOutTimestamp(),
                    blockRoomRequest.getRoomsList(),
                    blockRoomRequest.getBookByUser());
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Blocked rooms successfully saved!");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(populateErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update_blocked_rooms_booking", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> updateBlockedRooms(@RequestBody BookingDto booking) {
        try {
            roomMaintenanceService.updateBlockedRooms(booking);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Blocked rooms successfully updated!");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(populateErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getAllEnabledProperties", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAllEnabledProperties() {
        ArrayList<HashMap<String, String>> errorList = new ArrayList<>();
        try {
            ArrayList<PropertyDto> properties = propertyService.getAllProperties();
            System.out.println(properties);
            return new ResponseEntity<>(properties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(populateErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }
}
