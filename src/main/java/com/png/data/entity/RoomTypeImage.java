package com.png.data.entity;

import javax.persistence.*;

@Entity
@Table(name="room_type_image")
public class RoomTypeImage extends Image{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_room_type_image")
    private Long idRoomTypeImage;

    @Column (name="room_type_id")
    private Long roomTypeId;

    public Long getIdRoomTypeImage() {
        return idRoomTypeImage;
    }

    public void setIdRoomTypeImage(Long idRoomTypeImage) {
        this.idRoomTypeImage = idRoomTypeImage;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}
