package com.png.data.repository;

import com.png.data.entity.Invoice;
import com.png.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long>, InvoiceRepositoryCustom {
    List<Invoice> findInvoicesByUserOrderByCreatedTimestampDesc(User user);

    List<Invoice> findInvoicesByUserIdUserAndTravellerEmailContainsOrderByCreatedTimestampDesc(Long userId,
                                                                                               String travellerEmail);

    @Query(value = "SELECT invoice FROM Invoice invoice WHERE invoice.user.idUser = :userId")
    Page<Invoice> findPagedInvoicesByUserId(@Param("userId") Long userId, Pageable pageable);
}
