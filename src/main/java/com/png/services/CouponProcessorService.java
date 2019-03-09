package com.png.services;

import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.entity.*;
import com.png.data.repository.ItemTaxRepository;
import com.png.data.requests.ApplyCouponRequest;
import com.png.data.mapper.InvoiceMapper;
import com.png.data.repository.DiscountCouponRepository;
import com.png.exception.ApiBusinessException;
import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponProcessorService {

    @Autowired
    DiscountCouponRepository discountCouponRepository;

    @Autowired
    ItemTaxRepository itemTaxRepository;

    public InvoiceDto processDiscountCoupon(ApplyCouponRequest applyCouponRequest) {
        String newCouponCode = applyCouponRequest.getCouponCode();
        InvoiceDto invoiceToProcess = applyCouponRequest.getInvoice();
        List<ItemTax> applicableTaxes;
        applicableTaxes = itemTaxRepository.findAllByItemTaxPercentEquals("0");
        Invoice invoice = InvoiceMapper.INSTANCE.InvoiceDtoToInvoice(invoiceToProcess);
        //get how many coupons are already applied - if coupon applied throw error
        List<InvoiceDiscountLine> appliedCouponLines = invoice.getInvoiceDiscountLines();
        if (appliedCouponLines != null && appliedCouponLines.size() > 0) {
            throw new ApiBusinessException("1000", "Each transaction can only accept a single discount coupon." +
                    " Discount coupon is already applied to this Invoice.",
                    String.format("Requested Coupon Code: %s", newCouponCode));
        }
        // get the coupon that needs to be applied
        DiscountCoupon couponToApply = discountCouponRepository.findEligibleDiscountCoupon(newCouponCode);
        //throw error if coupon is not found
        if (couponToApply == null)
            throw new ApiBusinessException("1000", "Invalid Coupon Code",
                    String.format("Requested Coupon Code: %s", newCouponCode));
        //throw error if coupon is expired, in case no valid upto date go ahead
        if (couponToApply.getValidUpto() != null)
            if (couponToApply.getValidUpto().before(DateFormatter.getCurrentTime()))
                throw new ApiBusinessException("1000",
                        "This coupon is expired, reach out to our support if you think there is an issue",
                        String.format("Requested Coupon Code: %s Expired: %s", newCouponCode,
                                DateFormatter.getDateStringFromTimestamp(couponToApply.getValidUpto())));
        //start applying the coupon
        invoice.getInvoiceLines().forEach(invoiceLine -> {
            invoiceLine.applyDiscountCoupon(couponToApply); //discount the lines
        });
        invoice.reapplyApplicableTaxesAfterDiscount(applicableTaxes); //re-apply the taxes after discount
        //re-calculate the taxes
        invoice.getInvoiceLines().forEach(InvoiceLine::calculateAmountWithTax);
        invoice.calculateInvoiceLevelTaxes(); // recalculate invoice level taxes
        invoice.calculateInvoiceTotalTax();   // recalculate
        invoice.calculateInvoiceTotal();
        invoice.calculateInvoiceTotalWithTax();

        // create a discount line and add
        InvoiceDiscountLine invoiceDiscountLine = new InvoiceDiscountLine();
        invoiceDiscountLine.setCouponCode(couponToApply.getCouponCode());
        invoiceDiscountLine.setCouponType(couponToApply.getCouponType());
        invoiceDiscountLine.setDescription(String.format("Coupon Code: %s, %d%% discount applied",
                couponToApply.getCouponCode(),
                couponToApply.getDiscountPercent()));
        invoiceDiscountLine.setSequenceNo(getInvoiceDiscountLineSequence(invoice.getInvoiceDiscountLines()));
        invoiceDiscountLine.setAmount(invoice.getInvoiceDiscount());
        //add the coupon line
        if (invoice.getInvoiceDiscountLines() != null)
            invoice.getInvoiceDiscountLines().add(invoiceDiscountLine);
        else {
            List<InvoiceDiscountLine> invoiceDiscountLines = new ArrayList<>();
            invoiceDiscountLines.add(invoiceDiscountLine);
            invoice.setInvoiceDiscountLines(invoiceDiscountLines);
        }
        // calculate the totals
        invoice.calculateInvoiceTotalDiscount();
        invoice.processTotalsForDiscount();
        InvoiceDto invoiceToReturn = InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(invoice);
        invoiceToReturn.setUserContext(invoiceToProcess.getUserContext());
        invoiceToReturn.setProperty(invoiceToProcess.getProperty());
        invoiceToReturn.setCancelledByUser(invoiceToProcess.getCancelledByUser());
        return invoiceToReturn;
    }

    public InvoiceDto removeDiscountCoupon(ApplyCouponRequest removeCouponRequest) {
        String appliedCouponCode = removeCouponRequest.getCouponCode();
        InvoiceDto invoiceToProcess = removeCouponRequest.getInvoice();
        List<ItemTax> applicableTaxes;
        applicableTaxes = itemTaxRepository.findAllByItemTaxPercentEquals("0");
        Invoice invoice = InvoiceMapper.INSTANCE.InvoiceDtoToInvoice(invoiceToProcess);
        //get coupons that are already applied - if coupon applied throw error
        List<InvoiceDiscountLine> appliedDiscountLines = invoice.getInvoiceDiscountLines();
        InvoiceDiscountLine appliedDiscountLine = null;
        if (appliedDiscountLines != null)
            appliedDiscountLine = appliedDiscountLines.stream()
                    .filter(appliedCoupon ->
                            appliedCoupon.getCouponCode()
                                    .equals(appliedCouponCode))
                    .findAny()
                    .orElse(null);
        if (appliedDiscountLine == null) //the coupon code was not applied
            throw new ApiBusinessException("1000", "No Such Coupon is applied",
                    String.format("Requested Coupon Code: %s", appliedCouponCode));
        // get the coupon that needs to be applied
        DiscountCoupon couponToRemove = discountCouponRepository.findEligibleDiscountCoupon(appliedCouponCode);
        //start removing the coupon
        invoice.getInvoiceLines().forEach(invoiceLine -> {
            invoiceLine.removeDiscountCoupon(couponToRemove); //discount the lines
        });
        invoice.reapplyApplicableTaxesAfterDiscount(applicableTaxes); //re-apply the taxes after discount removal
        //re-calculate the taxes
        invoice.getInvoiceLines().forEach(InvoiceLine::calculateAmountWithTax);
        invoice.calculateInvoiceLevelTaxes(); // recalculate invoice level taxes
        invoice.calculateInvoiceTotalTax();   // recalculate
        invoice.calculateInvoiceTotal();
        invoice.calculateInvoiceTotalWithTax();

        //remove the discount line already applied
        appliedDiscountLines.remove(appliedDiscountLine);
        invoice.setInvoiceDiscountLines(appliedDiscountLines);

        // calculate the totals
        invoice.calculateInvoiceTotalDiscount();
        invoice.processTotalsForDiscount();
        InvoiceDto invoiceToReturn = InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(invoice);
        invoiceToReturn.setUserContext(invoiceToProcess.getUserContext());
        invoiceToReturn.setProperty(invoiceToProcess.getProperty());
        invoiceToReturn.setCancelledByUser(invoiceToProcess.getCancelledByUser());
        return invoiceToReturn;
    }

    private Integer getInvoiceDiscountLineSequence(List<InvoiceDiscountLine> invoiceDiscountLines) {
        if (invoiceDiscountLines == null)
            return 1;
        return invoiceDiscountLines.size() + 1;
    }
}
