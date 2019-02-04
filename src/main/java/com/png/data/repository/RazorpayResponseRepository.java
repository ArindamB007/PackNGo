package com.png.data.repository;

import com.png.data.entity.RazorpayPaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RazorpayResponseRepository extends JpaRepository<RazorpayPaymentResponse, String> {
}
