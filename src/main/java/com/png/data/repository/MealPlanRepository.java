package com.png.data.repository;

import java.util.List;

import com.png.data.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.png.data.entity.MealPlan;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
	List<MealPlan> findByRoomTypeId(Long roomTypeId);
}
