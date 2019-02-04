package com.png.data.dto.invoice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.png.data.dto.checkinoutdetails.InvoiceCheckInOutDetailsDto;
import com.png.data.dto.property.PropertyDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.InvoiceLine;
import com.png.data.entity.ItemType;
import com.png.util.CurrecySerializer;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceDto {
    private Long idInvoice;
    private String invoiceDate;
    private String invoiceNo;
    private String checkInTimestamp;
    private String checkOutTimestamp;
    private Integer maxAdults = 0;
    private Integer maxChilds = 0;
    private Integer maxExtraChilds = 0;
    private Integer maxExtraAdults = 0;
    private Integer nights;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal invoiceTotal;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal invoiceTotalWithTax;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal invoiceTotalTax;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal amountPaid;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal amountPending;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal invoiceTotalRefund;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal invoiceTotalWithTaxRefund;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal amountToBeRefunded;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal amountRefunded;
    private String invoiceStatusCode;
    private String travellerFirstName;
    private String travellerMiddleName;
    private String travellerLastName;
    private String travellerEmail;
    private String travellerMobile;
    private List<InvoiceTaxDto> appliedTaxes;
    private List<InvoiceLineDto> invoiceLines;
    private List<InvoicePaymentLineDto> invoicePaymentLines;
    private PropertyDto property;
    private UserContext userContext;
    private PaymentDto payment;

    public InvoiceDto() {
        invoiceTotal = invoiceTotalWithTax = invoiceTotalTax = amountPaid =
                amountPending = invoiceTotalRefund = invoiceTotalWithTaxRefund =
                        amountToBeRefunded = amountRefunded = BigDecimal.ZERO;
    }
    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public BigDecimal getInvoiceTotalWithTax() {
        return invoiceTotalWithTax;
    }

    public void setInvoiceTotalWithTax(BigDecimal invoiceTotalWithTax) {
        this.invoiceTotalWithTax = invoiceTotalWithTax;
    }

    public String getInvoiceStatusCode() {
        return invoiceStatusCode;
    }

    public void setInvoiceStatusCode(String invoiceStatusCode) {
        this.invoiceStatusCode = invoiceStatusCode;
    }

    public BigDecimal getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    public void setInvoiceTotalTax(BigDecimal invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }

    public List<InvoiceTaxDto> getAppliedTaxes() {
        return appliedTaxes;
    }

    public void setAppliedTaxes(List<InvoiceTaxDto> appliedTaxes) {
        this.appliedTaxes = appliedTaxes;
    }

    public List<InvoiceLineDto> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLineDto> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public PropertyDto getProperty() {
        return property;
    }

    public void setProperty(PropertyDto property) {
        this.property = property;
    }

    public UserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

    public List<InvoicePaymentLineDto> getInvoicePaymentLines() {
        return invoicePaymentLines;
    }

    public void setInvoicePaymentLines(List<InvoicePaymentLineDto> invoicePaymentLines) {
        this.invoicePaymentLines = invoicePaymentLines;
    }

    public String getTravellerFirstName() {
        return travellerFirstName;
    }

    public void setTravellerFirstName(String travellerFirstName) {
        this.travellerFirstName = travellerFirstName;
    }

    public String getTravellerMiddleName() {
        return travellerMiddleName;
    }

    public void setTravellerMiddleName(String travellerMiddleName) {
        this.travellerMiddleName = travellerMiddleName;
    }

    public String getTravellerLastName() {
        return travellerLastName;
    }

    public void setTravellerLastName(String travellerLastName) {
        this.travellerLastName = travellerLastName;
    }

    public String getTravellerEmail() {
        return travellerEmail;
    }

    public void setTravellerEmail(String travellerEmail) {
        this.travellerEmail = travellerEmail;
    }

    public String getTravellerMobile() {
        return travellerMobile;
    }

    public void setTravellerMobile(String travellerMobile) {
        this.travellerMobile = travellerMobile;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getAmountPending() {
        return amountPending;
    }

    public void setAmountPending(BigDecimal amountPending) {
        this.amountPending = amountPending;
    }

    public String getCheckInTimestamp() {
        return checkInTimestamp;
    }

    public BigDecimal getInvoiceTotalRefund() {
        return invoiceTotalRefund;
    }

    public void setInvoiceTotalRefund(BigDecimal invoiceTotalRefund) {
        this.invoiceTotalRefund = invoiceTotalRefund;
    }

    public BigDecimal getInvoiceTotalWithTaxRefund() {
        return invoiceTotalWithTaxRefund;
    }

    public void setInvoiceTotalWithTaxRefund(BigDecimal invoiceTotalWithTaxRefund) {
        this.invoiceTotalWithTaxRefund = invoiceTotalWithTaxRefund;
    }

    public BigDecimal getAmountToBeRefunded() {
        return amountToBeRefunded;
    }

    public void setAmountToBeRefunded(BigDecimal amountToBeRefunded) {
        this.amountToBeRefunded = amountToBeRefunded;
    }

    public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(BigDecimal amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public void setCheckInTimestamp(String checkInTimestamp) {
        this.checkInTimestamp = checkInTimestamp;
    }

    public String getCheckOutTimestamp() {
        return checkOutTimestamp;
    }

    public void setCheckOutTimestamp(String checkOutTimestamp) {
        this.checkOutTimestamp = checkOutTimestamp;
    }

    public Integer getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(Integer maxAdults) {
        this.maxAdults = maxAdults;
    }

    public Integer getMaxChilds() {
        return maxChilds;
    }

    public void setMaxChilds(Integer maxChilds) {
        this.maxChilds = maxChilds;
    }

    public Integer getMaxExtraChilds() {
        return maxExtraChilds;
    }

    public void setMaxExtraChilds(Integer maxExtraChilds) {
        this.maxExtraChilds = maxExtraChilds;
    }

    public Integer getMaxExtraAdults() {
        return maxExtraAdults;
    }

    public void setMaxExtraAdults(Integer maxExtraAdults) {
        this.maxExtraAdults = maxExtraAdults;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public void setInvoiceOccupancyInfo() {
        Integer maxAdults = 0;
        Integer maxChilds = 0;
        Integer maxExtraChilds = 0;
        Integer maxExtraAdults = 0;
        for (InvoiceLineDto invoiceLine: this.invoiceLines){
            if (invoiceLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name())){
                maxAdults = maxAdults + ((InvoiceMealPlanLineDto)invoiceLine).getMaxAdults();
                maxChilds = maxChilds + ((InvoiceMealPlanLineDto)invoiceLine).getMaxChilds();
            }
            else if(invoiceLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.EXTRA_PERSON.name())){
                if (((InvoiceLineItemDto)invoiceLine).getItemType()
                        .equals(ItemType.ItemTypeCodes.EXTRABEDADULT.name()))
                    maxExtraAdults = maxExtraAdults + ((InvoiceLineItemDto)invoiceLine).getQuantity();
                else if (((InvoiceLineItemDto)invoiceLine).getItemType()
                        .equals(ItemType.ItemTypeCodes.EXTRABEDCHILD.name()))
                    maxExtraChilds = maxExtraChilds + ((InvoiceLineItemDto)invoiceLine).getQuantity();
            }
        }
        this.setMaxAdults(maxAdults);
        this.setMaxChilds(maxChilds);
        this.setMaxExtraAdults(maxExtraAdults);
        this.setMaxExtraChilds(maxExtraChilds);
    }
}
