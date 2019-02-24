package com.png.services;

import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.requests.ApplyCouponRequest;
import com.png.data.entity.DiscountCoupon;
import com.png.data.entity.Invoice;
import com.png.data.entity.InvoiceDiscountLine;
import com.png.data.mapper.InvoiceMapper;
import com.png.data.repository.DiscountCouponRepository;
import com.png.exception.CouponExpiredException;
import com.png.exception.InvalidCouponCodeException;
import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponProcessorService {

    @Autowired
    DiscountCouponRepository discountCouponRepository;

    public InvoiceDto processDiscountCoupon(ApplyCouponRequest applyCouponRequest) {
        String newCouponCode = applyCouponRequest.getCouponCode();
        Invoice invoice = InvoiceMapper.INSTANCE.InvoiceDtoToInvoice(applyCouponRequest.getInvoice());
        //get how many coupons are already applied - if coupon applied throw error
        List<InvoiceDiscountLine> appliedCouponLines = invoice.getInvoiceDiscountLines();
        if (appliedCouponLines != null && appliedCouponLines.size() > 0) {
            throw new InvalidCouponCodeException("1006", "Each transaction can only accept a single discount coupon." +
                    " Discount coupon is already applied to this Invoice.");
        }
        // get the coupon that needs to be applied
        DiscountCoupon couponToApply = discountCouponRepository.findEligibleDiscountCoupon(newCouponCode);
        //throw error if coupon is not found
        if (couponToApply == null)
            throw new InvalidCouponCodeException("1007", "Invalid Coupon Code");
        //throw error if coupon is expired, in case no valid upto date go ahead
        if (couponToApply.getValidUpto() != null)
            if (couponToApply.getValidUpto().before(DateFormatter.getCurrentTime()))
                throw new CouponExpiredException("1008",
                        "This coupon is expired, reach out to our support if you think there is an issue");
        //start applying the coupon
        invoice.getInvoiceLines().forEach(invoiceLine -> {
            invoiceLine.applyDiscountCoupon(couponToApply); //discount the lines
            invoiceLine.calculateAmountWithTax();           //re-calculate the taxes
        });
        invoice.calculateInvoiceLevelTaxes(); // recalculate invoice level taxes
        invoice.calculateInvoiceTotalTax();   // recalculate
        invoice.calculateInvoiceTotal();
        invoice.calculateInvoiceTotalWithTax();


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
        return InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(invoice);
    }

    private Integer getInvoiceDiscountLineSequence(List<InvoiceDiscountLine> invoiceDiscountLines) {
        if (invoiceDiscountLines == null)
            return 1;
        return invoiceDiscountLines.size() + 1;
    }
}
