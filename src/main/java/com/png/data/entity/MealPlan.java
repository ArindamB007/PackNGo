package com.png.data.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
	
	@OneToMany
	@JoinTable(name = "meal_plan_items", joinColumns = @JoinColumn(name = "id_meal_plan"), inverseJoinColumns = @JoinColumn(name = "id_item"))
	private List<Item> items;
	
	@Column(name="description", nullable = false)
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
	
}
