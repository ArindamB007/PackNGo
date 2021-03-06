package com.png.data.entity;


import com.png.payments.RazorPay;
import com.png.services.InvoiceCancellationService;
import com.png.util.DateFormatter;
import com.png.util.Utils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity{
    public enum InvoiceStatusCodes {TOTALED,
        PAID, PAYMENT_PENDING, REFUNDED, PARTIAL_REFUNDED, REFUND_PENDING
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice")
    private Long idInvoice;

    @Column(name = "invoice_no", nullable = false,unique = true)
    private String invoiceNo;

    @Column(name = "invoice_total")
    private BigDecimal invoiceTotal;

    @Column(name = "invoice_discount")
    private BigDecimal invoiceDiscount;

    @Column(name = "invoice_total_with_tax")
    private BigDecimal invoiceTotalWithTax;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    @Column(name = "amount_pending")
    private BigDecimal amountPending;

    @Column(name = "invoice_total_tax")
    private BigDecimal invoiceTotalTax;

    @Column(name = "invoice_total_refund")
    private BigDecimal invoiceTotalRefund;

    @Column(name = "invoice_total_with_tax_refund")
    private BigDecimal invoiceTotalWithTaxRefund;

    @Column(name = "amount_to_be_refunded")
    private BigDecimal amountToBeRefunded;

    @Column(name = "amount_refunded")
    private BigDecimal amountRefunded;

    @Column(name = "invoice_status_code")
    private String invoiceStatusCode;

    @Column(name="traveller_first_name")
    @NotEmpty
    private String travellerFirstName;

    @Column(name="traveller_middle_name")
    private String travellerMiddleName;

    @Column(name="traveller_last_name")
    @NotEmpty
    private String travellerLastName;

    @Column(name="traveller_email")
    @Email
    @NotEmpty
    private String travellerEmail;

    @Column (name="traveller_mobile")
    @NotEmpty
    private String travellerMobile;


    @Column(name = "check_in_timestamp", nullable = false)
    private Timestamp checkInTimestamp;

    @Column(name = "check_out_timestamp", nullable = false)
    private Timestamp checkOutTimestamp;

    @Column(name = "allow_cancel_flag", nullable = false)
    private Boolean allow_cancel_flag = true;

    @OneToMany (mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceTax> appliedTaxes;

    @OneToMany (mappedBy = "invoice",cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "groupSequenceNo ASC, sequenceNo ASC")
    private List<InvoiceLine> invoiceLines;

    @OneToMany (mappedBy = "invoice",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicePaymentLine> invoicePaymentLines;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDiscountLine> invoiceDiscountLines;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn
    private Property property;

    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private User cancelledByUser;

    public Invoice() {
        invoiceTotal = invoiceTotalWithTax = invoiceTotalTax = amountPaid =
                amountPending = invoiceTotalRefund = invoiceTotalWithTaxRefund =
                        amountToBeRefunded = amountRefunded = invoiceDiscount = BigDecimal.ZERO;
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
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

    public BigDecimal getInvoiceDiscount() {
        return invoiceDiscount;
    }

    public void setInvoiceDiscount(BigDecimal invoiceDiscount) {
        this.invoiceDiscount = invoiceDiscount;
    }

    public BigDecimal getInvoiceTotalWithTax() {
        return invoiceTotalWithTax;
    }

    public void setInvoiceTotalWithTax(BigDecimal invoiceTotalWithTax) {
        this.invoiceTotalWithTax = invoiceTotalWithTax;
    }

    public BigDecimal getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    public void setInvoiceTotalTax(BigDecimal invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }


    public String getInvoiceStatusCode() {
        return invoiceStatusCode;
    }

    public void setInvoiceStatusCode(String invoiceStatusCode) {
        this.invoiceStatusCode = invoiceStatusCode;
    }

    public List<InvoiceTax> getAppliedTaxes() {
        return appliedTaxes;
    }

    public void setAppliedTaxes(List<InvoiceTax> appliedTaxes) {
        this.appliedTaxes = appliedTaxes;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public List<InvoiceDiscountLine> getInvoiceDiscountLines() {
        return invoiceDiscountLines;
    }

    public void setInvoiceDiscountLines(List<InvoiceDiscountLine> invoiceDiscountLines) {
        this.invoiceDiscountLines = invoiceDiscountLines;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCancelledByUser() {
        return cancelledByUser;
    }

    public void setCancelledByUser(User cancelledByUser) {
        this.cancelledByUser = cancelledByUser;
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

    public List<InvoicePaymentLine> getInvoicePaymentLines() {
        return invoicePaymentLines;
    }

    public void setInvoicePaymentLines(List<InvoicePaymentLine> invoicePaymentLines) {
        this.invoicePaymentLines = invoicePaymentLines;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
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

    public Timestamp getCheckInTimestamp() {
        return checkInTimestamp;
    }

    public void setCheckInTimestamp(Timestamp checkInTimestamp) {
        this.checkInTimestamp = checkInTimestamp;
    }

    public Timestamp getCheckOutTimestamp() {
        return checkOutTimestamp;
    }

    public void setCheckOutTimestamp(Timestamp checkOutTimestamp) {
        this.checkOutTimestamp = checkOutTimestamp;
    }

    public Boolean getAllow_cancel_flag() {
        return allow_cancel_flag;
    }

    public void setAllow_cancel_flag(Boolean allow_cancel_flag) {
        this.allow_cancel_flag = allow_cancel_flag;
    }

    public void addInvoiceTax(InvoiceTax invoiceTax){
        if (this.appliedTaxes == null)
            this.appliedTaxes = new ArrayList<>();
        this.appliedTaxes.add(invoiceTax);
        if (invoiceTax != null)
            invoiceTax.setInvoice(this);
    }

    public void removeInvoiceTax(InvoiceTax invoiceTax){
        this.appliedTaxes.remove(invoiceTax);
        if (invoiceTax != null)
            invoiceTax.setInvoice(null);
    }


    public void addInvoicePaymentLine(PaymentResponse paymentResponse) {
        //create new payment line
        InvoicePaymentLine invoicePaymentLine = new InvoicePaymentLine();
        invoicePaymentLine.setPaymentResponse(paymentResponse);
        if (paymentResponse.getPaymentProvider().equals(PaymentResponse.PaymentProviders.RAZORPAY.name())) {
            //RazorpayPaymentResponse razorpayPaymentResponse = (RazorpayPaymentResponse)paymentResponse;
            if (paymentResponse.getTransactionType()
                    .equalsIgnoreCase(InvoicePaymentLine.TransactionTypes.PAYMENT.name()))
                invoicePaymentLine.setTransactionType(InvoicePaymentLine.TransactionTypes.PAYMENT.name());
            else if (paymentResponse.getTransactionType()
                    .equalsIgnoreCase(InvoicePaymentLine.TransactionTypes.REFUND.name()))
                invoicePaymentLine.setTransactionType(InvoicePaymentLine.TransactionTypes.REFUND.name());
        } else {
            //todo throw error
        }
        if (this.invoicePaymentLines == null){
            this.invoicePaymentLines = new ArrayList<>();
        }
        invoicePaymentLine.setSequenceNo(invoicePaymentLines.size()+1); //set the sequence number
        processTotalsForPayment(invoicePaymentLine);
        try {
            if (invoicePaymentLine.getTransactionType().equals(InvoicePaymentLine.TransactionTypes.PAYMENT.name()))
                updateInvoiceStatus(InvoiceStatusCodes.PAID);
            else if (invoicePaymentLine.getTransactionType().equals(InvoicePaymentLine.TransactionTypes.REFUND.name()))
                updateInvoiceStatus(InvoiceStatusCodes.REFUNDED);
        } catch (Exception e) {
            //todo log exception
        }
        this.invoicePaymentLines.add(invoicePaymentLine);
        invoicePaymentLine.setInvoice(this);
    }

    public void removeInvoicePaymentLine(InvoicePaymentLine invoicePaymentLine){
        this.invoicePaymentLines.remove(invoicePaymentLine);
        if (invoicePaymentLine != null)
            invoicePaymentLine.setInvoice(null);

    }

    public void addInvoiceLine(InvoiceLine invoiceLine){
        if (this.invoiceLines == null)
            this.invoiceLines = new ArrayList<>();
        this.invoiceLines.add(invoiceLine);
        if (invoiceLine != null)
            invoiceLine.setInvoice(this);
    }

    public void removeInvoiceLine(InvoiceLine invoiceLine){
        invoiceLines.remove(invoiceLine);
        if (invoiceLine != null)
            invoiceLine.setInvoice(null);
    }

    public void addInvoiceDiscountLine(InvoiceDiscountLine invoiceDiscountLine) {
        if (this.invoiceDiscountLines == null)
            this.invoiceDiscountLines = new ArrayList<>();
        this.invoiceDiscountLines.add(invoiceDiscountLine);
        if (invoiceDiscountLine != null)
            invoiceDiscountLine.setInvoice(this);
    }

    public void removeInvoiceDiscountLine(InvoiceLine invoiceLine) {
        invoiceLines.remove(invoiceLine);
        if (invoiceLine != null)
            invoiceLine.setInvoice(null);
    }

    public void addBooking(Booking booking) {
        if (bookings == null)
            bookings = new ArrayList<>();
        bookings.add(booking);
        if (booking != null)
            booking.setInvoice(this);
    }


    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        if (booking != null)
            booking.setInvoice(null);
    }

    private void processTotalsForPayment(InvoicePaymentLine invoicePaymentLine) {
        //payment block
        if (invoicePaymentLine.getTransactionType().equals(InvoicePaymentLine.TransactionTypes.PAYMENT.name())){
            //razorpay block
            PaymentResponse paymentResponse = invoicePaymentLine.getPaymentResponse();
            if (paymentResponse.getPaymentProvider().equals(
                    PaymentResponse.PaymentProviders.RAZORPAY.name())){
                BigDecimal amountPaid = new BigDecimal(((RazorpayPaymentResponse) paymentResponse).getAmount())
                        .divide(BigDecimal.valueOf(100)); //Divide by hundred to get value in rupees
                this.amountPaid = this.amountPaid
                        .add(amountPaid);
                this.amountPending = this.invoiceTotalWithTax
                        .subtract(amountPaid);
            }

        } else if (invoicePaymentLine.getTransactionType().equals(InvoicePaymentLine.TransactionTypes.REFUND.name())) {
            //razorpay block
            PaymentResponse paymentResponse = invoicePaymentLine.getPaymentResponse();
            if (paymentResponse.getPaymentProvider().equals(
                    PaymentResponse.PaymentProviders.RAZORPAY.name())) {
                BigDecimal amountRefunded = new BigDecimal(((RazorpayRefundResponse) paymentResponse).getAmount())
                        .divide(BigDecimal.valueOf(100)); //Divide by hundred to get value in rupees
                this.amountRefunded = this.amountRefunded
                        .add(amountRefunded);
                this.amountToBeRefunded = this.amountToBeRefunded
                        .subtract(this.amountToBeRefunded);
                this.amountPaid = this.invoiceTotalWithTax;
            }

        }
    }

    private void processTotalsForCancellation() {
        this.amountToBeRefunded = this.amountPaid.subtract(this.invoiceTotalWithTax);
    }

    public void processTotalsForDiscount() {
        this.invoiceTotal = this.invoiceTotal.subtract(this.invoiceDiscount);
        this.amountPending = this.invoiceTotalWithTax;
    }

    private void updateInvoiceStatus(InvoiceStatusCodes newStatus) throws Exception {
        if (this.invoiceStatusCode.equals(InvoiceStatusCodes.PAID.name())
                && newStatus.equals(InvoiceStatusCodes.REFUND_PENDING)) // PAID -> REFUND_PENDING
            this.invoiceStatusCode = newStatus.name();
        else if (this.invoiceStatusCode.equals(InvoiceStatusCodes.REFUND_PENDING.name())
                && newStatus.equals(InvoiceStatusCodes.REFUNDED)) // REFUND_PENDING -> REFUNDED
            this.invoiceStatusCode = InvoiceStatusCodes.REFUNDED.name();
        else if ((this.invoiceStatusCode.equals(InvoiceStatusCodes.TOTALED.name()))
                && newStatus.equals(InvoiceStatusCodes.PAID)) // TOTALED -> PAID
            this.invoiceStatusCode = InvoiceStatusCodes.PAID.name();
        else if ((this.invoiceStatusCode.equals(InvoiceStatusCodes.TOTALED.name()))
                && newStatus.equals(InvoiceStatusCodes.PAYMENT_PENDING)) // TOTALED -> PAYMENT_PENDING
            this.invoiceStatusCode = InvoiceStatusCodes.PAYMENT_PENDING.name();
        else
            throw new Exception();
    }

    private int getLastInvoiceLineGroupSequenceNo() {
        this.invoiceLines.sort(new Comparator<InvoiceLine>() {
            public int compare(InvoiceLine l1, InvoiceLine l2) {
                return l2.getGroupSequenceNo() - l1.getGroupSequenceNo();
            }
        });
        return this.invoiceLines.get(this.invoiceLines.size() - 1).getGroupSequenceNo();
    }

    //reapply applicable taxes after discount
    public void reapplyApplicableTaxesAfterDiscount(List<ItemTax> applicableTaxes) {
        //find distinct group of invoice lines
        List<InvoiceLine> distinctInvoiceGroupLines = this.invoiceLines.stream()
                .filter(Utils.distinctByKey(InvoiceLine::getGroupSequenceNo))
                .collect(Collectors.toList());
        //process each group of invoice lines
        for (InvoiceLine invoiceLine : distinctInvoiceGroupLines) {
            // filter out the invoice lines in this group from original array
            List<InvoiceLine> groupLines = this.invoiceLines.stream()
                    .filter(invLine -> invLine.getGroupSequenceNo() == invoiceLine.getGroupSequenceNo())
                    .collect(Collectors.toList());
            //calculate the total taxable amount
            BigDecimal groupTotal = BigDecimal.ZERO;
            for (InvoiceLine invLine : groupLines) {
                groupTotal = groupTotal.add(invLine.getTaxableAmount());
            }
            // tax hardcode for SGST and CGST check limits and assign rates
            if (groupTotal.compareTo(new BigDecimal(0)) >= 0 && groupTotal.compareTo(new BigDecimal(1500)) < 0)
                groupLines.forEach(invLine -> invLine.getInvoiceLineTaxes()
                        .forEach(invLineTax -> invLineTax.setItemTaxPercent("0")));
            else if (groupTotal.compareTo(new BigDecimal(1500)) >= 0 && groupTotal.compareTo(new BigDecimal(2500)) < 0)
                groupLines.forEach(invLine -> invLine.getInvoiceLineTaxes()
                        .forEach(invLineTax -> invLineTax.setItemTaxPercent("6")));
            else if (groupTotal.compareTo(new BigDecimal(2500)) >= 0 && groupTotal.compareTo(new BigDecimal(7500)) < 0)
                groupLines.forEach(invLine -> invLine.getInvoiceLineTaxes()
                        .forEach(invLineTax -> invLineTax.setItemTaxPercent("9")));
        }
    }


    private void processInvoiceLineCancellation(InvoiceLine invoiceRefundLine, Item cancellationItem,
                                                BigDecimal cancelPercent) {
        if (invoiceRefundLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name()) ||
                invoiceRefundLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.EXTRA_PERSON.name())) {
            ((InvoiceLineItem) invoiceRefundLine).setCancelCharge(invoiceRefundLine.getAmount()
                    .multiply(cancelPercent)
                    .divide(new BigDecimal(100)));//send deduction for calculation
            cancellationItem.getItemPrice()
                    .setBasePrice(((InvoiceLineItem) invoiceRefundLine)
                            .getCancelCharge()); //set cancellation charge for tax calculation
        }
        invoiceRefundLine.setDescription(invoiceRefundLine.getDescription() + " - " +
                cancellationItem.getDescription());
        invoiceRefundLine.setInvoiceLineStatusCode(InvoiceLine.InvoiceLineStatusCodes.REFUND.name());
        //remove all tax lines
        invoiceRefundLine.getInvoiceLineTaxes().removeAll(invoiceRefundLine.getInvoiceLineTaxes());
        getInvoiceLineTaxesForItem(cancellationItem).forEach(invoiceRefundLine::addInvoiceLineTax);
        //invoiceRefundLine.setInvoiceLineTaxes(getInvoiceLineTaxesForItem(cancellationItem));
        if (invoiceRefundLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name()) ||
                invoiceRefundLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.EXTRA_PERSON.name()))
            ((InvoiceLineItem) invoiceRefundLine).calculateAmountWithTaxForCancellation();
    }

    @Transactional
    public void processFullInvoiceCancellation(Item cancellationItem,
                                               InvoiceCancellationService.CancellationMode cancellationMode) {
        int daysBeforeCheckIn = DateFormatter.getDiffInDays(DateFormatter.getCurrentTime(), this.checkInTimestamp);
        //todo if days Before Checkin is negative throw error
        //store the first rule IMP list sorted asc by days from check in with query
        CancellationRule applicableCancelRule = this.property.getCancellationRules().get(0);
        for (CancellationRule cancellationRule : this.property.getCancellationRules()) {
            if (daysBeforeCheckIn > applicableCancelRule.getDaysFromCheckin() &&
                    daysBeforeCheckIn <= cancellationRule.getDaysFromCheckin()) {
                applicableCancelRule = cancellationRule;
            }

        }
        //now got the cancel percent based on check
        // in date and current date
        cancellationItem.setDescription(String.format("Charged %d%% for cancellation",
                applicableCancelRule.getCancelationPercent()));    // set cancellation charge text
        try {
            updateInvoiceStatus(InvoiceStatusCodes.REFUND_PENDING);
            for (InvoiceLine invLine : this.invoiceLines) {
                processInvoiceLineCancellation(invLine, cancellationItem,
                        BigDecimal.valueOf(applicableCancelRule.getCancelationPercent()));
            }
            calculateInvoiceLevelTaxes(); // recalculate invoice level taxes
            calculateInvoiceTotalTax();   // recalculate
            calculateInvoiceTotal();
            calculateInvoiceTotalWithTax();
            processTotalsForCancellation(); // find the total refund
            // Cancel the booking and take the payment only if cancellation is confirmed
            if (cancellationMode.equals(InvoiceCancellationService.CancellationMode.PROCESS_CANCELLATION)) {
                // add the cancellation date for each booking to mark them as cancelled
                bookings.forEach(booking -> booking.setCancelledTimestamp(DateFormatter.getCurrentTime()));
                RazorPay razorPay = new RazorPay();
                RazorpayRefundResponse refundResponse = razorPay.captureRazorPayRefund(
                        invoicePaymentLines.get(0).getPaymentResponse().getIdPaymentResponse(),
                        (amountToBeRefunded.multiply(BigDecimal.valueOf(100)).toString()));
                // todo check if the response is successful in case not raise error
                addInvoicePaymentLine(refundResponse);
            }
        } catch (Exception e) {
            //todo log exception
        }
    }

/*    public void prepareFullInvoiceCancellation(Item cancellationItem) {
        int daysBeforeCheckIn = DateFormatter.getDiffInDays(DateFormatter.getCurrentTime(),this.checkInTimestamp);
        //todo if days Before Checkin is negative throw error
        //store the first rule IMP list sorted asc by days from check in with query
        CancellationRule applicableCancelRule = this.property.getCancellationRules().get(0);
        for(CancellationRule cancellationRule : this.property.getCancellationRules()){
            if (daysBeforeCheckIn >= cancellationRule.getDaysFromCheckin())
                applicableCancelRule = cancellationRule;
            else
                break;
        }
        //getting cancel percent based on checkin date and current date
        try {
            updateInvoiceStatus(InvoiceStatusCodes.REFUND_PENDING);
            for (InvoiceLine invLine :this.invoiceLines){
                processInvoiceLineCancellation(invLine, cancellationItem,
                        BigDecimal.valueOf(applicableCancelRule.getCancelationPercent()));
            }
            calculateInvoiceLevelTaxes(); // recalculate invoice level taxes
            calculateInvoiceTotalTax();   // recalculate
            calculateInvoiceTotal();
            calculateInvoiceTotalWithTax();
            processTotalsForCancellation(); // find the total refund
        } catch (Exception e) {
            //todo log exception
        }
    }*/



    private List<InvoiceLineTax> getInvoiceLineTaxesForItem(Item item) {
        List<InvoiceLineTax> invoiceLineTaxes = new ArrayList<>();
        item.getAppliedTaxes().forEach(applicableTax -> {
            InvoiceLineTax invoiceLineTax = new InvoiceLineTax();
            invoiceLineTax.setItemTaxCode(applicableTax.getItemTaxCode());
            invoiceLineTax.setItemTaxPercent(applicableTax.getItemTaxPercent());
            invoiceLineTax.setItemTaxDescription(applicableTax.getItemTaxDescription());
            invoiceLineTax.setItemTaxAmount((item.getItemPrice().getBasePrice()
                    .multiply(new BigDecimal(applicableTax.getItemTaxPercent()))
                    .divide(new BigDecimal(100))));
            invoiceLineTaxes.add(invoiceLineTax);
        });
        return invoiceLineTaxes;
    }

    public void calculateInvoiceLevelTaxes() {
        List<InvoiceTax> appliedCurrentTaxes;
        if (this.appliedTaxes == null)
            appliedCurrentTaxes = new ArrayList<>();
        else {
            appliedCurrentTaxes = this.appliedTaxes;
            appliedCurrentTaxes.removeAll(this.appliedTaxes);
        }
        for (InvoiceLine invoiceLine : this.getInvoiceLines()) {
            for (InvoiceLineTax invoiceLineTax : invoiceLine.getInvoiceLineTaxes()) {
                InvoiceTax currentInvoiceTax = appliedCurrentTaxes.stream()
                        .filter(appliedTax ->
                                invoiceLineTax.getItemTaxCode()
                                        .equals(appliedTax.getItemTaxCode()) &&
                                        invoiceLineTax.getItemTaxPercent().equals(appliedTax.getItemTaxPercent()))
                        .findAny()
                        .orElse(null);
                if (currentInvoiceTax == null) { //new tax code
                    InvoiceTax appliedTax = new InvoiceTax();
                    appliedTax.setIdInvoiceTax(invoiceLineTax.getIdInvoiceLineTax());
                    appliedTax.setItemTaxCode(invoiceLineTax.getItemTaxCode());
                    appliedTax.setItemTaxDescription(invoiceLineTax.getItemTaxDescription());
                    appliedTax.setItemTaxPercent(invoiceLineTax.getItemTaxPercent());
                    appliedTax.setItemTaxAmount(invoiceLineTax.getItemTaxAmount());
                    appliedCurrentTaxes.add(appliedTax); // add the new tax code
                } else //existing tax code just set the new amount
                {
                    int existingIndex = appliedCurrentTaxes.indexOf(currentInvoiceTax);
                    currentInvoiceTax.setItemTaxAmount(currentInvoiceTax.getItemTaxAmount()
                            .add(invoiceLineTax.getItemTaxAmount()));
                    appliedCurrentTaxes.set(existingIndex, currentInvoiceTax);
                }
            }
        }
        this.appliedTaxes = appliedCurrentTaxes;
    }

    public void calculateInvoiceTotalTax() {
        BigDecimal totalInvoiceTax = BigDecimal.ZERO;
        for (InvoiceTax appliedTax : this.appliedTaxes) {
            totalInvoiceTax = totalInvoiceTax.add(appliedTax.getItemTaxAmount());
        }
        setInvoiceTotalTax(totalInvoiceTax);
    }

    public void calculateInvoiceTotalDiscount() {
        BigDecimal totalInvoiceDiscount = BigDecimal.ZERO;
        for (InvoiceDiscountLine appliedDiscount : this.invoiceDiscountLines) {
            totalInvoiceDiscount = totalInvoiceDiscount.add(appliedDiscount.getAmount());
        }
        setInvoiceDiscount(totalInvoiceDiscount);
    }

    public void calculateInvoiceTotal() {
        this.invoiceTotal = BigDecimal.ZERO;
        this.invoiceDiscount = BigDecimal.ZERO;
        this.invoiceTotalRefund = BigDecimal.ZERO;
        for (InvoiceLine invoiceLine : this.getInvoiceLines()) {
            if (invoiceLine.getInvoiceLineStatusCode().equals(InvoiceLine.InvoiceLineStatusCodes.SALE.name())) {
                this.invoiceTotal = this.invoiceTotal.add(invoiceLine.getAmount());
                this.invoiceDiscount = this.invoiceDiscount.add(invoiceLine.getDiscountAmount());
            }
            else if (invoiceLine.getInvoiceLineStatusCode().equals(InvoiceLine.InvoiceLineStatusCodes.REFUND.name())) {
                this.invoiceTotal = this.invoiceTotal.add(((InvoiceLineItem) invoiceLine).getCancelCharge());
                this.invoiceTotalRefund = this.invoiceTotalRefund.add(invoiceLine.getAmount());
            }
        }
    }

    public void calculateInvoiceTotalWithTax() {
        this.invoiceTotalWithTax = BigDecimal.ZERO;
        this.invoiceTotalWithTaxRefund = BigDecimal.ZERO;
        for (InvoiceLine invoiceLine : this.getInvoiceLines()) {
            if (invoiceLine.getInvoiceLineStatusCode().equals(InvoiceLine.InvoiceLineStatusCodes.SALE.name()))
                this.invoiceTotalWithTax = this.invoiceTotalWithTax.add(invoiceLine.getAmountWithTax());
            else if (invoiceLine.getInvoiceLineStatusCode().equals(InvoiceLine.InvoiceLineStatusCodes.REFUND.name())) {
                this.invoiceTotalWithTax = invoiceTotalWithTax.add(((InvoiceLineItem) invoiceLine).getCancelChargeWithTax());
                this.invoiceTotalWithTaxRefund = this.invoiceTotalWithTaxRefund.add(invoiceLine.getAmountWithTax());
            }
        }
    }
}
