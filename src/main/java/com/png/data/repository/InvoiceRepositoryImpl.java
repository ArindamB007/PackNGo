package com.png.data.repository;

import com.png.data.entity.Invoice;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Invoice> findInvoicesByUserIdOrderByCheckInTimestamp(Long userId) {
        return em.createQuery("SELECT OBJECT(invoice)FROM Invoice invoice " +
                "WHERE invoice.user.idUser = :userId " +
                "ORDER BY invoice.checkInTimestamp DESC", Invoice.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void detach(Invoice invoice) {
        em.detach(invoice);
    }



}
