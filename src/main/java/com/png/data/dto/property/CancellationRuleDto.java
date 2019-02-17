package com.png.data.dto.property;

public class CancellationRuleDto {
    private Long idCancellationRule;
    private int daysFromCheckin;
    private int cancelationPercent;
    private String cancellationDescription;

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


    public void setCancellationDescription() {
        if (this.cancellationDescription == null) {
            if (this.daysFromCheckin > 0)
                this.cancellationDescription = String.format("%d%% of total amount will be charged for cancellation," +
                        " if cancelled within %d days before check in date.", this.cancelationPercent, this.daysFromCheckin);
            else if (this.daysFromCheckin == 0)
                this.cancellationDescription = String.format("%d%% of total amount will be charged for cancellation," +
                        " if cancelled on the day of check in.", this.cancelationPercent);
            else if (this.daysFromCheckin == -1)
                this.cancellationDescription = String.format("A minimum of %d%% of total amount will be charged for " +
                        "any cancellation. Applicable taxes extra.", this.cancelationPercent);

        }
    }
}
