package com.png.services;

import com.png.data.dto.booking.BookingDto;
import com.png.data.dto.room.RoomDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.Booking;
import com.png.data.entity.Room;
import com.png.data.mapper.RoomMapper;
import com.png.data.repository.BookingRepository;
import com.png.data.repository.RoomRepository;
import com.png.exception.ApiBusinessException;
import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomMaintenanceService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    BookingRepository bookingRepository;

    public List<RoomDto> getAvailableRoomsForProperty(String checkInDate, String checkOutDate, Long idProperty) {
        List<Room> availableRooms;
        try {
            availableRooms = roomRepository.getAvailableRoomsForProperty(
                    DateFormatter.getTimestampFromString(checkInDate),
                    DateFormatter.getTimestampFromString(checkOutDate),
                    idProperty, Sort.Direction.ASC);
        } catch (Exception e) {
            e.printStackTrace();
            //todo add logging
            throw new ApiBusinessException("1000", "An unexpected situation is creating hindrance to further progress. " +
                    "Support team needs to intervene!");
        }
        return RoomMapper.INSTANCE.RoomsToRoomDtos(availableRooms);
    }

    @Transactional
    public void saveBlockedRooms(String checkInDate, String checkOutDate, List<RoomDto> rooms, UserContext blockedByUser) {
        try {
            rooms.forEach(room -> {
                Room roomEntity = roomRepository.findOne(room.getIdRoom());
                Booking booking = new Booking(
                        Booking.BookingTypeCodes.BLOCK,
                        DateFormatter.getTimestampFromString(checkInDate),
                        DateFormatter.getTimestampFromString(checkOutDate),
                        customUserDetailsService.getUserByUserContext(blockedByUser),
                        roomEntity, null);
                bookingRepository.save(booking);
            });
        } catch (Exception e) {
            e.printStackTrace();
            //todo log error
            throw new ApiBusinessException("1000", "An unexpected situation is creating hindrance to further progress. " +
                    "Support team needs to intervene!");

        }
    }

    @Transactional
    public void updateBlockedRooms(BookingDto bookingDto) {
        try {
            Booking booking = bookingRepository.getOne(bookingDto.getIdBooking());
            if (bookingDto.getCancelledByUser() == null) {
                Room room = roomRepository.getOne(bookingDto.getRoom().getIdRoom());
                booking.setBookedByUser(customUserDetailsService.getUserByUserContext(bookingDto.getBookedByUser()));
                booking.setRoom(room);
                booking.setCheckInTimestamp(DateFormatter.getTimestampFromString(bookingDto.getCheckInTimestamp()));
                booking.setCheckOutTimestamp(DateFormatter.getTimestampFromString(bookingDto.getCheckOutTimestamp()));
                booking.setUpdatedTimestamp(DateFormatter.getCurrentTime());
            } else {
                booking.setCancelledByUser(customUserDetailsService.getUserByUserContext(
                        bookingDto.getCancelledByUser()));
                booking.setCancelledTimestamp(DateFormatter.getCurrentTime());
            }
            bookingRepository.save(booking);
        } catch (Exception e) {
            e.printStackTrace();
            //todo log error
            throw new ApiBusinessException("1000", "An unexpected situation is creating hindrance to further progress. " +
                    "Support team needs to intervene!");

        }
    }
}
