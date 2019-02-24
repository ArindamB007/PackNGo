package com.png.data.repository;

import com.png.data.entity.DiscountCoupon;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class DiscountCouponRepositoryImpl implements DiscountCouponRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public DiscountCoupon findEligibleDiscountCoupon(String couponCode) {
        try {
            return em.createQuery("SELECT OBJECT (discountCoupon) FROM DiscountCoupon discountCoupon " +
                    "WHERE discountCoupon.couponCode = :couponCode " +
                    "AND discountCoupon.enabledFlag = true", DiscountCoupon.class)
                    .setParameter("couponCode", couponCode)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public DiscountCoupon findEligibleDiscountCoupon(String couponCode, Long userId) {
        try {
            return em.createQuery("SELECT OBJECT (DiscountCoupon) FROM DiscountCoupon discountCoupon " +
                    "WHERE discountCoupon.couponCode = :couponCode " +
                    "AND discountCoupon.userId = :userId " +
                    "AND discountCoupon.enabledFlag = true", DiscountCoupon.class)
                    .setParameter("couponCode", couponCode)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public DiscountCoupon findEligibleDiscountCoupon(String couponCode, Long userId, String propertyId) {
        try {
            return em.createQuery("SELECT OBJECT (DiscountCoupon) FROM DiscountCoupon discountCoupon " +
                    "WHERE discountCoupon.couponCode = :couponCode " +
                    "AND discountCoupon.userId = :userId " +
                    "AND discountCoupon.propertyId = :propertyId " +
                    "AND discountCoupon.enabledFlag = true", DiscountCoupon.class)
                    .setParameter("couponCode", couponCode)
                    .setParameter("userId", userId)
                    .setParameter("propertyId", propertyId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
