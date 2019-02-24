package com.png.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "discount_coupon")
public class DiscountCoupon extends BaseEntity {
    public enum DiscountCouponTypeCodes {SINGLE_USE}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_discount_coupon")
    private Long idDiscountCoupon;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "coupon_code", nullable = false, unique = true)
    private String couponCode;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent;

    @Column(name = "coupon_type")
    private String couponType;

    @Column(name = "valid_upto", nullable = false)
    private Timestamp validUpto;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "user_id")
    private Long userId;

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

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Timestamp getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(Timestamp validUpto) {
        this.validUpto = validUpto;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
