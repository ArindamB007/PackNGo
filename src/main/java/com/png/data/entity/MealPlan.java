package com.png.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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

	@OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MealPlanInclude> mealPlanIncludes;
	
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

	public void addMealPlanInclude(MealPlanInclude mealPlanInclude) {
		if (mealPlanInclude == null)
			return;
		if (this.mealPlanIncludes == null) {
			this.mealPlanIncludes = new ArrayList<>();
		}
		this.mealPlanIncludes.add(mealPlanInclude);
		mealPlanInclude.setMealPlan(this);
	}

	public void removeMealPlanInclude(MealPlanInclude mealPlanInclude) {
		this.mealPlanIncludes.remove(mealPlanInclude);
		if (mealPlanInclude != null)
			mealPlanInclude.setMealPlan(null);
	}

	
	
}
