package com.png.data.repository;

import com.png.data.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository <Invoice,Long> {
}
