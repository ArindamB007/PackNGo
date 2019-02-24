package com.png.data.repository;

import com.png.data.entity.DiscountCoupon;

public interface DiscountCouponRepositoryCustom {
    DiscountCoupon findEligibleDiscountCoupon(String couponCode);

    DiscountCoupon findEligibleDiscountCoupon(String couponCode, Long userId);

    DiscountCoupon findEligibleDiscountCoupon(String couponCode, Long userId, String propertyId);
}
