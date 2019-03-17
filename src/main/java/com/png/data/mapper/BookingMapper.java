package com.png.data.mapper;

import com.png.data.dto.booking.BookingDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.Booking;
import com.png.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    List<BookingDto> BookingsToBookingDtos(List<Booking> bookings);

    default BookingDto BookingToBookingDto(Booking booking) {
        if (booking == null)
            return null;
        BookingDto bookingDto = new BookingDto();

        bookingDto.setIdBooking(booking.getIdBooking());
        bookingDto.setBookingTypeCode(booking.getBookingTypeCode());
        UserContext userContext = new UserContext();
        userContext.setUserDetails(booking.getBookedByUser());
        bookingDto.setBookedByUser(userContext);

        UserContext cancelledByUser = new UserContext();
        cancelledByUser.setUserDetails(booking.getCancelledByUser());
        bookingDto.setCancelledByUser(cancelledByUser);

        bookingDto.setCancelledTimestamp(DateFormatter.getDateStringFromTimestamp(booking.getCancelledTimestamp()));
        bookingDto.setCheckInTimestamp(DateFormatter.getDateStringFromTimestamp(booking.getCheckInTimestamp()));
        bookingDto.setCheckOutTimestamp(DateFormatter.getDateStringFromTimestamp(booking.getCheckOutTimestamp()));
        return bookingDto;
    }
}
