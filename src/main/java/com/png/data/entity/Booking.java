package com.png.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Arindam on 12/6/2017.
 */
@Entity
@Table(name="booking")
public class Booking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_booking")
    private Integer idBooking;

    @ManyToMany
    @JoinTable(name = "bookings_rooms", joinColumns = @JoinColumn(name = "id_booking"), inverseJoinColumns = @JoinColumn(name = "id_room"))
    private Set<Room> rooms;

    @Column (name = "cancelled_timestamp")
    private Timestamp cancelledTimestamp;

    @Column (name = "check_in_timestamp")
    private Timestamp checkInTimestamp;

    @Column (name = "check_out_timestamp")
    private Timestamp checkOutTimestamp;

}
