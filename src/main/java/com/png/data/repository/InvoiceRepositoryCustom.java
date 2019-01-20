package com.png.data.repository;

import com.png.data.entity.Invoice;


import java.util.List;

public interface InvoiceRepositoryCustom {
    List<Invoice> findInvoicesByUserIdOrderByCheckInTimestamp(Long userId);
}
