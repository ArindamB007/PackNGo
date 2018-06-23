package com.png.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="meal_plan")
public class MealPlan extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_meal_plan")
    private Long idMealPlan;
	
	@Column (name="meal_plan_code", nullable = false)
    @NotEmpty
    private String mealPlanCode;
	
	@Column (name="room_type_id")
    private Long roomTypeId;
	
	@OneToOne
	@JoinColumn (name = "item_id")
	private Item item;
	
	
	@Column(name="description")
    private String description;

	public Long getIdMealPlan() {
		return idMealPlan;
	}

	public void setIdMealPlan(Long idMealPlan) {
		this.idMealPlan = idMealPlan;
	}

	public String getMealPlanCode() {
		return mealPlanCode;
	}

	public void setMealPlanCode(String mealPlanCode) {
		this.mealPlanCode = mealPlanCode;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
