package com.png.data.mapper;

import com.png.data.dto.invoice.InvoicePaymentLineDto;
import com.png.data.entity.InvoicePaymentLine;
import com.png.data.entity.PaymentResponse;
import com.png.data.entity.RazorpayPaymentResponse;
import com.png.data.entity.RazorpayRefundResponse;
import com.png.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface InvoicePaymentLineMapper {
    InvoicePaymentLineMapper INSTANCE = Mappers.getMapper(InvoicePaymentLineMapper.class);
    List<InvoicePaymentLineDto> InvoicePaymentLineToInvoicePaymentLineDtos
            (List<InvoicePaymentLine> invoicePaymentLines);
    List<InvoicePaymentLine> InvoicePaymentLineDtosToInvoicePaymentLines
            (List<InvoicePaymentLineDto> invoicePaymentLineDtos);
    default InvoicePaymentLineDto InvoicePaymentLineToInvoicePaymentLineDto (InvoicePaymentLine invoicePaymentLine){
        if ( invoicePaymentLine == null ) {
            return null;
        }
        InvoicePaymentLineDto invoicePaymentLineDto = new InvoicePaymentLineDto();
        invoicePaymentLineDto.setIdInvoicePaymentLine(invoicePaymentLine.getIdInvoicePaymentLine());
        invoicePaymentLineDto.setSequenceNo(invoicePaymentLine.getSequenceNo());
        invoicePaymentLineDto.setTransactionType(invoicePaymentLine.getTransactionType());
        PaymentResponse paymentResponse = invoicePaymentLine.getPaymentResponse();
        if (paymentResponse != null && paymentResponse.getPaymentProvider().equals(
                PaymentResponse.PaymentProviders.RAZORPAY.name()) &&
                paymentResponse.getTransactionType()
                        .equalsIgnoreCase(InvoicePaymentLine.TransactionTypes.PAYMENT.name())) {
            invoicePaymentLineDto.setTransactionMode(((RazorpayPaymentResponse) paymentResponse).getMethod());
            invoicePaymentLineDto.setAmountPaid(new BigDecimal(((RazorpayPaymentResponse) paymentResponse).getAmount())
                    .divide(BigDecimal.valueOf(100)));
            invoicePaymentLineDto.setTransactionTimeStamp(
                    DateFormatter.getDateStringFromTimestamp(((RazorpayPaymentResponse) paymentResponse).getCreated_at()));
            invoicePaymentLineDto.setTransactionId(paymentResponse.getIdPaymentResponse());
        } else if (paymentResponse != null && paymentResponse.getPaymentProvider().equals(
                PaymentResponse.PaymentProviders.RAZORPAY.name()) &&
                paymentResponse.getTransactionType()
                        .equalsIgnoreCase(InvoicePaymentLine.TransactionTypes.REFUND.name())) {
            invoicePaymentLineDto.setTransactionMode(((RazorpayRefundResponse) paymentResponse).getEntity());
            invoicePaymentLineDto.setAmountPaid(new BigDecimal(((RazorpayRefundResponse) paymentResponse).getAmount())
                    .divide(BigDecimal.valueOf(100)));
            invoicePaymentLineDto.setTransactionTimeStamp(
                    DateFormatter.getDateStringFromTimestamp(((RazorpayRefundResponse) paymentResponse).getCreated_at()));
            invoicePaymentLineDto.setTransactionId(paymentResponse.getIdPaymentResponse());
        }
        return invoicePaymentLineDto;
    }

}
