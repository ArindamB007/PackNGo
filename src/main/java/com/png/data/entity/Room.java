package com.png.data.entity;


import javax.persistence.*;

/**
 * Created by Arindam on 12/5/2017.
 */
@Entity
@Table(name="room")
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_room")
    private Integer idRoom;

    @Column (name = "room_no", nullable = false, unique = true)
    private String roomNo;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Booking booking;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private RoomType roomType;

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
