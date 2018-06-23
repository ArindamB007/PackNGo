package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.Facility;
import com.png.data.entity.RoomTypeImage;
import com.png.exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RoomTypeRepositoryImpl implements RoomTypeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoomTypeImageRepository roomTypeImageRepository;
    
    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Override
    public List<AvailableRoomType> getAvailableRoomTypeWithCount(Timestamp checkInTimestamp,Timestamp checkOutTimestamp,
                                                                 Long idProperty){
        Query query = em.createNativeQuery(
                "SELECT room_type.id_room_type,room_type.type_name,room_type.base_price,count(rt.type_name) AS count_available,\n" +
                        "    room_type.discount,room_type.description,\n" +
                        "    room_type.max_adult_occupancy, room_type.max_child_occupancy\n" +
                        "    FROM property \n" +
                        "    LEFT JOIN room_type\n" +
                        "        ON room_type.property_id_property = property.id_property" +
                        "        AND room_type.enabled_flag =1\n" +
                        "    LEFT JOIN room\n" +
                        "        ON room.room_type_id_room_type = room_type.id_room_type\n" +
                        "        AND room.enabled_flag = 1 \n" +
                        "    LEFT JOIN bookings_rooms\n" +
                        "        ON room.id_room = bookings_rooms.id_room\n" +
                        "    LEFT JOIN booking\n" +
                        "        ON booking.id_booking = bookings_rooms.id_booking \n" +
                        "        AND booking.enabled_flag = 1 \n" +
                        "        AND booking.cancelled_timestamp IS NULL\n" +
                        "    LEFT JOIN room_type rt\n" +
                        "        ON rt.id_room_type = room_type.id_room_type\n" +
                        "        AND ((booking.check_in_timestamp IS NULL AND  booking.check_out_timestamp IS NULL)\n" +
                        "        OR  (:checkInTimestamp < booking.check_in_timestamp AND :checkOutTimestamp < booking.check_in_timestamp)\n" +
                        "        OR  (:checkInTimestamp > booking.check_out_timestamp AND :checkOutTimestamp > booking.check_out_timestamp))\n" +
                        "    where id_property = :idProperty" +
                        "    GROUP BY room_type.type_name ORDER BY room_type.id_room_type");
        query.setParameter("checkInTimestamp",checkInTimestamp);
        query.setParameter("checkOutTimestamp",checkOutTimestamp);
        query.setParameter("idProperty",idProperty);
        List<Object[]> results = query.getResultList();



       /* List<Object[]> results = em.createNativeQuery(
                "SELECT room_type.id_room_type,room_type.type_name,room_type.base_price,count(*) AS count_available,\n" +
                "    room_type.discount,room_type.description\n" +
                "    FROM room \n" +
                "    LEFT JOIN room_type\n" +
                "        ON room.room_type_id_room_type = room_type.id_room_type \n" +
                "        AND room.enabled_flag = 1 \n" +
                "        AND room_type.enabled_flag =1\n" +
                "    LEFT JOIN bookings_rooms\n" +
                "        ON room.id_room = bookings_rooms.id_room\n" +
                "    LEFT JOIN booking\n" +
                "        ON booking.id_booking = bookings_rooms.id_booking \n" +
                "        AND booking.enabled_flag = 1 \n" +
                "        AND booking.cancelled_timestamp IS NOT NULL\n" +
                "    INNER JOIN room_type rt\n" +
                "        ON rt.id_room_type = room_type.id_room_type\n" +
                "        AND ((booking.check_in_timestamp IS NULL AND  booking.check_out_timestamp IS NULL)\n" +
                "        OR  ('2017-12-17 00:00:01' < booking.check_in_timestamp AND '2017-12-18 00:00:00' < booking.check_in_timestamp)\n" +
                "        OR  ('2017-12-17 00:00:01' > booking.check_out_timestamp AND '2017-12-18 00:00:00' > booking.check_out_timestamp))\n" +
                "\t\tGROUP BY type_name ORDER BY room_type.id_room_type").getResultList();*/
       if ((results.get(0))[0] == null) {
           throw new NoDataException("000", "No data for Available Room Types");
       }
        List<AvailableRoomType> availableRoomTypeList= new ArrayList<>(results.size());
        results.forEach(row->{
            AvailableRoomType availableRoomType = new AvailableRoomType();
            availableRoomType.setIdRoomType(((BigInteger)row[0]).longValue());
            availableRoomType.setTypeName((String)row[1]);
            availableRoomType.setBasePrice((BigDecimal) row[2]);
            availableRoomType.setAvailableCount(((BigInteger) row[3]).intValue());
            availableRoomType.setDiscount((Integer) row[4]);
            availableRoomType.setDescription((String) row[5]);
            availableRoomType.setMaxAdultOccupancy((Integer) row[6]);
            availableRoomType.setMaxChildOccupancy((Integer) row[7]);
            availableRoomType.setRoomTypeImages(
                    new HashSet<>(roomTypeImageRepository.findByRoomTypeId(availableRoomType.getIdRoomType())));
            availableRoomType.setFacilities(
                    new HashSet<>(getRoomTypeFacilitiesByIdRoomType(availableRoomType.getIdRoomType())));
            availableRoomType.setMealPlans(mealPlanRepository.findByRoomTypeId(availableRoomType.getIdRoomType()));
            availableRoomTypeList.add(availableRoomType);
        });
        return availableRoomTypeList;
    }

    @Override
    public List<Facility>getRoomTypeFacilitiesByIdRoomType(Long idRoomType){
        List <Object[]> results = em.createNativeQuery("SELECT facility.id_facility,facility.name, facility.enabled_flag FROM facility \n" +
                "LEFT JOIN room_types_facilities\n" +
                "ON room_types_facilities.id_facility = facility.id_facility AND\n" +
                "room_types_facilities.id_room_type = " + idRoomType.toString()).getResultList();
        List<Facility> facilities = new ArrayList<>(results.size());
        results.forEach(row->{
            Facility facility = new Facility();
            facility.setIdFacility(((BigInteger)row[0]).longValue());
            facility.setName((String)row[1]);
            facility.setEnabledFlag((Boolean)row[2]);
            facilities.add(facility);
        });
        return facilities;
    }

}
