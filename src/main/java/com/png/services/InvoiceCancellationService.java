package com.png.services;

import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.entity.Invoice;
import com.png.data.entity.Item;
import com.png.data.entity.User;
import com.png.data.mapper.InvoiceMapper;
import com.png.data.repository.InvoiceRepository;
import com.png.data.repository.ItemRepository;
import com.png.data.repository.UserRepository;
import com.png.util.DateFormatter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvoiceCancellationService {
    public enum CancellationMode {PREPARE_CANCELLATON, PROCESS_CANCELLATION}

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public InvoiceDto processCancelInvoice(Long idInvoice, Long idUserCancelledBy) {
        Invoice cancelledInvoice = invoiceRepository.findOne(idInvoice);
        User cancelledByUser = userRepository.findByIdUser(idUserCancelledBy);
        if (cancelledInvoice == null) {
            //throw invoice not found exception
            return null;
        } else if (cancelledInvoice.getInvoiceStatusCode().equals(Invoice.InvoiceStatusCodes.REFUNDED.name())) {
            //throw invoice is already cancelled, contact support for more
            return null;
        }
        // todo check if the invoice can be cancelled based on checkin date
        Item cancelItem = itemRepository.findItemByDescriptionEquals("Cancellation Charge");
        if (cancelItem == null) {
            //throw cancellation item not found description
            return null;
        }
        cancelledInvoice.processFullInvoiceCancellation(cancelItem, CancellationMode.PROCESS_CANCELLATION);
        cancelledInvoice.setCancelledByUser(cancelledByUser); // setting the user who cancelled the invoice
        cancelledInvoice.setUpdatedTimestamp(DateFormatter.getCurrentTime());
        //setting cancelled by for the booking entity
        cancelledInvoice.getBookings().forEach(booking -> {
            booking.setCancelledByUser(cancelledByUser);
            booking.setCancelledTimestamp(DateFormatter.getCurrentTime());
        });
        cancelledInvoice = invoiceRepository.save(cancelledInvoice);
        return InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(cancelledInvoice);
    }

    public InvoiceDto prepareCancelInvoice(Long idInvoice) {
        Invoice cancelledInvoice = invoiceRepository.findOne(idInvoice);
        if (cancelledInvoice == null) {
            //todo throw invoice not found exception
            return null;
        } else if (cancelledInvoice.getInvoiceStatusCode().equals(Invoice.InvoiceStatusCodes.REFUNDED.name())) {
            //todo throw invoice is already cancelled, contact support for more
            return null;
        }
        // retrieve all lazy child
        Hibernate.initialize(cancelledInvoice.getInvoiceLines());
        cancelledInvoice.getInvoiceLines().forEach(invoiceLine -> Hibernate.initialize(invoiceLine.getInvoiceLineTaxes()));
        Hibernate.initialize(cancelledInvoice.getAppliedTaxes());
        Hibernate.initialize(cancelledInvoice.getInvoicePaymentLines());
        invoiceRepository.detach(cancelledInvoice);
        // todo check if the invoice can be cancelled based on checkin date
        Item cancelItem = itemRepository.findItemByDescriptionEquals("Cancellation Charge");
        if (cancelItem == null) {
            //todo throw cancellation item not found description
            return null;
        }
        Hibernate.initialize(cancelItem.getAppliedTaxes());
        itemRepository.detach(cancelItem);
        cancelledInvoice.processFullInvoiceCancellation(cancelItem, CancellationMode.PREPARE_CANCELLATON);
        return InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(cancelledInvoice);
    }

}
