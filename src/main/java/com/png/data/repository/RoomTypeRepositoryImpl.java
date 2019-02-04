package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.Facility;
import com.png.data.entity.Room;
import com.png.exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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
                "  select A.id_room_type,A.type_name,A.base_price,count(B.id_room) as count_available, \n" +
                        "    A.discount,A.description,A.max_adult_occupancy, A.max_child_occupancy,\n" +
                        "    A.max_extra_adult_occupancy, A.max_extra_child_occupancy, A.max_total_occupancy from\n" +
                        " (select * from room_type)  AS A\n" +
                        "  LEFT JOIN\n" +
                        " (select room.id_room, room.room_no, room_type.id_room_type,room_type.type_name\n" +
                        "    from room \n" +
                        "    LEFT JOIN room_type \n" +
                        "    on room.room_type_id_room_type = room_type.id_room_type\n" +
                        "  where id_room not in (\n" +
                        "     select room.id_room from room \n" +
                        "    LEFT JOIN  booking\n" +
                        "      on room.id_room = booking.room_id_room " +
                        "      AND booking.cancelled_timestamp IS NULL where\n" +
                        "           (:checkInTimestamp <= booking.check_in_timestamp and " +
                        "               :checkOutTimestamp >= booking.check_in_timestamp) OR\n" +
                        "           (:checkInTimestamp <= booking.check_out_timestamp and " +
                        "               :checkOutTimestamp >= booking.check_out_timestamp))\n" +
                        "  and room_type.property_id_property = :idProperty ) AS B\n" +
                        "    ON A.id_room_type = B.id_room_type\n" +
                        "    WHERE A.property_id_property = :idProperty\n" +
                        "    GROUP BY A.type_name order by A.id_room_type;");
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
            availableRoomType.setMaxExtraAdultOccupancy((Integer) row[8]);
            availableRoomType.setMaxExtraChildOccupancy((Integer) row[9]);
            availableRoomType.setMaxTotalOccupancy((Integer) row[10]);
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

    @Override
    public List<Room> getRoomsToBeBooked(Long idProperty, String roomTypeName, Integer roomQuantity,
                                                    Timestamp checkInTimestamp, Timestamp checkOutTimestamp){
        Query query = em.createNativeQuery(
                " select room.id_room, room.room_no,room_type.id_room_type,room_type.type_name\n" +
                        "    from room \n" +
                        "    LEFT JOIN room_type \n" +
                        "    ON room.room_type_id_room_type = room_type.id_room_type\n" +
                        "    AND room_type.type_name = :roomTypeName\n"+
                        "  WHERE id_room NOT IN (\n" +
                        "     select room.id_room from room \n" +
                        "    LEFT JOIN  booking\n" +
                        "      on room.id_room = booking.room_id_room " +
                        "      AND booking.cancelled_timestamp IS NULL where \n" +
                        "           (:checkInTimestamp <= booking.check_in_timestamp AND \n" +
                        "           :checkOutTimestamp >= booking.check_in_timestamp) OR \n" +
                        "           (:checkInTimestamp <= booking.check_out_timestamp AND \n" +
                        "           :checkOutTimestamp >= booking.check_out_timestamp)) \n" +
                        " AND room_type.property_id_property = :idProperty\n" +
                        " order by room.room_no*1 LIMIT :roomQuantity");
        query.setParameter("checkInTimestamp",checkInTimestamp);
        query.setParameter("checkOutTimestamp",checkOutTimestamp);
        query.setParameter("idProperty",idProperty);
        query.setParameter("roomTypeName",roomTypeName);
        query.setParameter("roomQuantity",roomQuantity);
        List<Object[]> results = query.getResultList();
        List<Room> roomAllotmentList = new ArrayList<>();
        if ((results.get(0))[0] == null)
            return roomAllotmentList;
        results.forEach(row-> {
            Room room  = new Room();
            room.setIdRoom((Integer)row[0]);
            room.setRoomNo(row[1].toString());
            roomAllotmentList.add(room);
        });
        return roomAllotmentList;
    }

}
