package com.png.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountCouponMapper {
    DiscountCouponMapper INSTANCE = Mappers.getMapper(DiscountCouponMapper.class);
}
