package com.png.services;

import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.entity.Invoice;
import com.png.data.entity.InvoiceLine;
import com.png.data.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class InvoiceCancellationService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceDto cancelInvoice(Long idInvoice) {
        Invoice invoice = invoiceRepository.findOne(idInvoice);
        if (invoice == null) {
            //throw invoice not found exception
            return null;
        }
        //adding a new cancellation line with cancellation charge
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setInvoice(invoice);
        invoiceLine.setInvoiceLineTypeCode(InvoiceLine.InvoiceLineTypeCodes.CANCEL_LINE.name());
        invoiceLine.setAmount(BigDecimal.ZERO); // cancellation charge to be calculated and put here as per rules
        invoiceLine.setAmountWithTax(BigDecimal.ZERO); // calculate tax on cancellation charge

        return new InvoiceDto(); // please remove
    }

}
