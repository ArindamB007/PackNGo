package com.png.data.mapper;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.invoice.InvoicePaymentLineDto;
import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.InvoicePaymentLine;
import com.png.data.entity.PaymentResponse;
import com.png.data.entity.RazorpayResponse;
import com.png.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
                PaymentResponse.PaymentProviders.RAZORPAY.name())){
            invoicePaymentLineDto.setTransactionMode(((RazorpayResponse)paymentResponse).getMethod());
            invoicePaymentLineDto.setAmountPaid(((RazorpayResponse)paymentResponse).getAmount());
            invoicePaymentLineDto.setTransactionTimeStamp(
                    DateFormatter.getDateStringFromTimestamp(((RazorpayResponse)paymentResponse).getCreated_at()));
            invoicePaymentLineDto.setTransactionId(paymentResponse.getIdPaymentResponse());
        }
        return invoicePaymentLineDto;
    }

}
