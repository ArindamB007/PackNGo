package com.png.data.repository;

import com.png.data.entity.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long>, DiscountCouponRepositoryCustom {
}
