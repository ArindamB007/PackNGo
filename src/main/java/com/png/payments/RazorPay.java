package com.png.payments;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.png.data.entity.PaymentResponse;
import com.png.data.entity.RazorpayRefundResponse;
import com.png.data.entity.RazorpayPaymentResponse;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.Refund;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RazorPay {
    private final String razorPayKey = "rzp_test_9LJbdDeHn1EYIr";
    private final String razorPaySecret = "ZCdjpt0xMYnO3yuYtiAgQBLf";

    public RazorpayPaymentResponse captureRazorPayPayment(String razor_payment_id, String amountPaid) {
        RazorpayPaymentResponse razorpayPaymentResponse = null;
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorPayKey, razorPaySecret);
            JSONObject options = new JSONObject();
            options.put("amount", new BigDecimal(amountPaid));

            Payment razorpayPayment = razorpayClient.Payments.capture(razor_payment_id, options);
            System.out.println(razorpayPayment.toString());
            System.out.println("Status: " + razorpayPayment.get("status").toString());

            ObjectMapper razorpayMapper = new ObjectMapper();
            razorpayMapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                razorpayPaymentResponse = razorpayMapper.readValue(razorpayPayment.toString(), RazorpayPaymentResponse.class);
                razorpayPaymentResponse.setIdPaymentResponse(razor_payment_id);
                razorpayPaymentResponse.setPaymentProvider(PaymentResponse.PaymentProviders.RAZORPAY.name());
                razorpayPaymentResponse.setTransactionType(razorpayPaymentResponse.getEntity());
            } catch (Exception e) {
                System.out.println(e.getMessage()); // todo put logs
            }

        } catch (Exception e) {
            //log the exception
        }
        return razorpayPaymentResponse;
    }

    public RazorpayRefundResponse captureRazorPayRefund(String razor_payment_id, String amountRefunded) {
        RazorpayRefundResponse razorpayRefundResponse = null;
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorPayKey, razorPaySecret);
            JSONObject options = new JSONObject();
            options.put("amount", new BigDecimal(amountRefunded));
            options.put("payment_id", razor_payment_id);

            Refund razorpayRefund = razorpayClient.Payments.refund(options);
            System.out.println(razorpayRefund.toString());
            //System.out.println("Status: " + razorpayPayment.get("status").toString());

            ObjectMapper razorpayMapper = new ObjectMapper();
            razorpayMapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                razorpayRefundResponse = razorpayMapper.readValue(razorpayRefund.toString(), RazorpayRefundResponse.class);
                razorpayRefundResponse.setIdPaymentResponse(razorpayRefundResponse.getId());
                razorpayRefundResponse.setPaymentProvider(PaymentResponse.PaymentProviders.RAZORPAY.name());
                razorpayRefundResponse.setTransactionType(razorpayRefundResponse.getEntity());
            } catch (Exception e) {
                System.out.println(e.getMessage()); // todo put logs
            }

        } catch (Exception e) {
            //log the exception
        }
        return razorpayRefundResponse;
    }


}
