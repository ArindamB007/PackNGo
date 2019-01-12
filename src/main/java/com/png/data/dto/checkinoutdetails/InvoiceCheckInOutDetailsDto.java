package com.png.data.dto.checkinoutdetails;

public class InvoiceCheckInOutDetailsDto extends CheckInOutDetailsDto{
    private Integer nights;
    private Integer maxAdults;
    private Integer maxChilds;
    private Integer maxExtraAdults;
    private Integer maxExtraChilds;

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public Integer getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(Integer maxAdults) {
        this.maxAdults = maxAdults;
    }

    public Integer getMaxChilds() {
        return maxChilds;
    }

    public void setMaxChilds(Integer maxChilds) {
        this.maxChilds = maxChilds;
    }

    public Integer getMaxExtraAdults() {
        return maxExtraAdults;
    }

    public void setMaxExtraAdults(Integer maxExtraAdults) {
        this.maxExtraAdults = maxExtraAdults;
    }

    public Integer getMaxExtraChilds() {
        return maxExtraChilds;
    }

    public void setMaxExtraChilds(Integer maxExtraChilds) {
        this.maxExtraChilds = maxExtraChilds;
    }
}
