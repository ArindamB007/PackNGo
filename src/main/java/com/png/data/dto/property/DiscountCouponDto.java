package com.png.data.dto.property;

public class DiscountCouponDto {
    public Long getIdDiscountCoupon() {
        return idDiscountCoupon;
    }

    public void setIdDiscountCoupon(Long idDiscountCoupon) {
        this.idDiscountCoupon = idDiscountCoupon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountCouponType() {
        return discountCouponType;
    }

    public void setDiscountCouponType(String discountCouponType) {
        this.discountCouponType = discountCouponType;
    }

    private Long idDiscountCoupon;
    private String description;
    private String couponCode;
    private int discountPercent;
    private String discountCouponType;
}
