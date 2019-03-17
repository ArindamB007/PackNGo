package com.png.data.entity;

import com.png.util.DateFormatter;

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
    public enum BookingTypeCodes {
        ONLINE, BLOCK, OFFLINE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_booking")
    private Long idBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Room room;

    @Column (name = "cancelled_timestamp")
    private Timestamp cancelledTimestamp;

    @Column(name = "check_in_timestamp", nullable = false)
    private Timestamp checkInTimestamp;

    @Column(name = "check_out_timestamp", nullable = false)
    private Timestamp checkOutTimestamp;

    @Column(name = "booking_type_code", nullable = false)
    private String bookingTypeCode;

    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User bookedByUser;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private User cancelledByUser;

    public Booking() {
        super();
    }

    public Booking(BookingTypeCodes bookingTypeCode, Timestamp checkInTimestamp, Timestamp checkOutTimestamp,
                   User bookedByUser, Room room, Invoice invoice) {
        super();
        this.bookingTypeCode = bookingTypeCode.name();
        this.invoice = invoice;
        this.checkInTimestamp = checkInTimestamp;
        this.checkOutTimestamp = checkOutTimestamp;
        this.cancelledByUser = null;
        this.room = room;
        this.bookedByUser = bookedByUser;
    }

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

    public User getBookedByUser() {
        return bookedByUser;
    }

    public void setBookedByUser(User bookedByUser) {
        this.bookedByUser = bookedByUser;
    }

    public User getCancelledByUser() {
        return cancelledByUser;
    }

    public void setCancelledByUser(User cancelledByUser) {
        this.cancelledByUser = cancelledByUser;
    }

    public String getBookingTypeCode() {
        return bookingTypeCode;
    }

    public void setBookingTypeCode(String bookingTypeCode) {
        this.bookingTypeCode = bookingTypeCode;
    }
}
