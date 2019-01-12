package com.png.data.dto.user;

import com.png.data.entity.Traveller;

public class TravellerDto extends Traveller {
    private Long idTraveller;
    private Long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobile;

    @Override
    public Long getIdTraveller() {
        return idTraveller;
    }

    @Override
    public void setIdTraveller(Long idTraveller) {
        this.idTraveller = idTraveller;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
