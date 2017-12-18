package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.RoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomTypeRepository extends CrudRepository<RoomType,Integer>,RoomTypeRepositoryCustom {

}

   /* select room_type.id_room_type,room_type.type_name,room_type.base_price,count(*) as count_available,
        room_type.discount,room_type.description
        from room
        LEFT JOIN room_type
        on room.room_type_id_room_type = room_type.id_room_type
        LEFT JOIN bookings_rooms
        on room.id_room = bookings_rooms.id_room
        LEFT JOIN booking
        on booking.id_booking = bookings_rooms.id_booking
        INNER JOIN room_type as rt
        on rt.id_room_type = room_type.id_room_type AND
        ((booking.check_in_timestamp IS NULL AND  booking.check_out_timestamp IS NULL) OR
        ('2017-12-17 00:00:01' < booking.check_in_timestamp and '2017-12-18 00:00:00' < booking.check_in_timestamp) OR
        ('2017-12-17 00:00:01' > booking.check_out_timestamp and '2017-12-18 00:00:00' > booking.check_out_timestamp))
        group by type_name order by room_type.id_room_type;*/