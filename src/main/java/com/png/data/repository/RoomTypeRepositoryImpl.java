package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeRepositoryImpl implements RoomTypeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<AvailableRoomType> getAvaiableRoomTypeWithCount(){
        List<Object[]> results = em.createNativeQuery("SELECT room_type.id_room_type,room_type.type_name,room_type.base_price,count(*) AS count_available,\n" +
                "    room_type.discount,room_type.description\n" +
                "    FROM room \n" +
                "\t\tLEFT JOIN room_type\n" +
                "\t\t\tON room.room_type_id_room_type = room_type.id_room_type\n" +
                "\t\tLEFT JOIN bookings_rooms\n" +
                "\t\t\tON room.id_room = bookings_rooms.id_room\n" +
                "\t\tLEFT JOIN booking\n" +
                "\t\t\tON booking.id_booking = bookings_rooms.id_booking\n" +
                "\t\tINNER JOIN room_type AS rt\n" +
                "\t\t\tON rt.id_room_type = room_type.id_room_type AND\n" +
                "\t\t\t((booking.check_in_timestamp IS NULL AND  booking.check_out_timestamp IS NULL) OR\n" +
                "            ('2017-12-17 00:00:01' < booking.check_in_timestamp AND '2017-12-18 00:00:00' < booking.check_in_timestamp) OR\n" +
                "\t\t\t('2017-12-17 00:00:01' > booking.check_out_timestamp AND '2017-12-18 00:00:00' > booking.check_out_timestamp))\n" +
                "\t\tGROUP BY type_name ORDER BY room_type.id_room_type").getResultList();
        List<AvailableRoomType> availableRoomTypeList= new ArrayList<>(results.size());
        results.forEach(row->{
            AvailableRoomType availableRoomType = new AvailableRoomType();
            availableRoomType.setIdRoomType((Integer)row[0]);
            availableRoomType.setTypeName((String)row[1]);
            availableRoomType.setBasePrice((BigDecimal) row[2]);
            availableRoomType.setAvailableCount(((BigInteger) row[3]).intValue());
            availableRoomType.setDiscount((Integer) row[4]);
            availableRoomType.setDescription((String) row[5]);
            availableRoomTypeList.add(availableRoomType);
        });
        return availableRoomTypeList;
    }
}
