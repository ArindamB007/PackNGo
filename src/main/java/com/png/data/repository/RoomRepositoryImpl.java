package com.png.data.repository;

import com.png.data.entity.Room;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RoomRepositoryImpl implements RoomRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> getAvailableRoomsForProperty(Timestamp checkInTimestamp, Timestamp checkOutTimestamp, Long idProperty,
                                                   Sort.Direction sortOrder) {
        List<Room> roomsAvailable = (em.createQuery("SELECT OBJECT(room) FROM Room room " +
                "LEFT JOIN FETCH room.booking " +
                "LEFT JOIN FETCH room.roomType " +
                "WHERE room.idRoom NOT IN " +
                "(SELECT rm.idRoom FROM Room rm INNER JOIN rm.booking AS booking WHERE " +
                " booking.cancelledTimestamp is NULL AND " +
                " (:checkInTimestamp <= booking.checkInTimestamp AND " +
                "  :checkOutTimestamp >= booking.checkInTimestamp) OR " +
                " (:checkInTimestamp <= booking.checkOutTimestamp AND " +
                "  :checkOutTimestamp >= booking.checkOutTimestamp) OR " +
                " (:checkInTimestamp >= booking.checkInTimestamp AND " +
                "  :checkOutTimestamp <= booking.checkOutTimestamp)) " +
                "AND room.property.idProperty = :idProperty", Room.class)
                .setParameter("checkInTimestamp", checkInTimestamp)
                .setParameter("checkOutTimestamp", checkOutTimestamp)
                .setParameter("idProperty", idProperty)
                .getResultList()).stream().sorted((o1, o2) ->
                (Integer.valueOf(o1.getRoomNo()) - Integer.valueOf(o2.getRoomNo()))).collect(Collectors.toList());
        if (sortOrder.isAscending())
            return roomsAvailable;
        else {
            Collections.reverse(roomsAvailable);
            return roomsAvailable;
        }

    }
}
