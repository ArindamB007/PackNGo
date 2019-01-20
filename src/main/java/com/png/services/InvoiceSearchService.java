package com.png.services;

import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.entity.Invoice;
import com.png.data.mapper.InvoiceMapper;
import com.png.data.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceSearchService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<InvoiceDto> searchInvoiceByUserId(Long userId, String sortOrd) {
        List<Invoice> invoices = invoiceRepository.findInvoicesByUserIdOrderByCheckInTimestamp(userId);
        return InvoiceMapper.INSTANCE.InvoicesToInvoiceDtos(invoices);
    }

    public PagedResponse<InvoiceDto> searchPagedInvoiceByUserId(Long userId,
                                                                Integer pageNo,
                                                                Integer pageSize,
                                                                Sort.Direction sortDirection) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, new Sort(sortDirection,
                "checkInTimestamp"));

        Page<Invoice> invoicesPage = invoiceRepository.findPagedInvoicesByUserId(userId, pageRequest);
        return new PagedResponse<>(InvoiceMapper.INSTANCE.InvoicesToInvoiceDtos(invoicesPage.getContent()),
                invoicesPage.getTotalElements(),
                invoicesPage.getTotalPages(), invoicesPage.getSize());
    }

}
