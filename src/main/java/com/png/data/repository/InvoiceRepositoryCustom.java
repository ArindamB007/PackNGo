package com.png.data.repository;

import com.png.data.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepositoryCustom {
    List<Invoice> findInvoicesByUserIdOrderByCheckInTimestamp(Long userId);
}
