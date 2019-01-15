package com.png.data.repository;

import com.png.data.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Invoice> findInvoicesByUserIdOrderByCheckInTimestamp(Long userId) {
        return em.createQuery("SELECT OBJECT(invoice)FROM Invoice invoice " +
                "WHERE invoice.user.idUser = :userId " +
                "ORDER BY invoice.booking.checkInTimestamp DESC", Invoice.class)
                .setParameter("userId", userId)
                .getResultList();
    }

}
