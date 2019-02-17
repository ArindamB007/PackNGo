package com.png.data.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "cancellation_rule")
public class CancellationRule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cancellation_rule")
    private Long idCancellationRule;

    @OrderBy(value = "daysFromCheckin DESC")
    @Column(name = "days_from_checkin")
    private int daysFromCheckin;

    @Column(name = "cancellation_percent")
    private int cancelationPercent;

    @Column(name = "property_id")
    private Long propertyId;

    public Long getIdCancellationRule() {
        return idCancellationRule;
    }

    public void setIdCancellationRule(Long idCancellationRule) {
        this.idCancellationRule = idCancellationRule;
    }

    public int getDaysFromCheckin() {
        return daysFromCheckin;
    }

    public void setDaysFromCheckin(int daysFromCheckin) {
        this.daysFromCheckin = daysFromCheckin;
    }

    public int getCancelationPercent() {
        return cancelationPercent;
    }

    public void setCancelationPercent(int cancelationPercent) {
        this.cancelationPercent = cancelationPercent;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
