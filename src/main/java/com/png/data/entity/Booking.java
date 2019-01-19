package com.png.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
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
    private Long idBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @OneToOne(fetch = FetchType.LAZY)
    private Room room;


   /* @ManyToMany
    @JoinTable(name = "bookings_rooms", joinColumns = @JoinColumn(name = "id_booking"),
            inverseJoinColumns = @JoinColumn(name = "id_room"))
    private List<Room> rooms;*/

    @Column (name = "cancelled_timestamp")
    private Timestamp cancelledTimestamp;

    @Column(name = "check_in_timestamp", nullable = false)
    private Timestamp checkInTimestamp;

    @Column(name = "check_out_timestamp", nullable = false)
    private Timestamp checkOutTimestamp;

    public Long getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Long idBooking) {
        this.idBooking = idBooking;
    }

    public Timestamp getCancelledTimestamp() {
        return cancelledTimestamp;
    }

    public void setCancelledTimestamp(Timestamp cancelledTimestamp) {
        this.cancelledTimestamp = cancelledTimestamp;
    }

    public Timestamp getCheckInTimestamp() {
        return checkInTimestamp;
    }

    public void setCheckInTimestamp(Timestamp checkInTimestamp) {
        this.checkInTimestamp = checkInTimestamp;
    }

    public Timestamp getCheckOutTimestamp() {
        return checkOutTimestamp;
    }

    public void setCheckOutTimestamp(Timestamp checkOutTimestamp) {
        this.checkOutTimestamp = checkOutTimestamp;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
