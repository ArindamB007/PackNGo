package com.png.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "razorpay_response")
@PrimaryKeyJoinColumn(name="id")
public class RazorpayResponse extends PaymentResponse{
      public enum PaymentStatus {created, authorized, captured, refunded, failed}
      public enum RefundStatus {partial, full}
      public enum Methods {card,netbanking,wallet,emi}
      @Column(name = "entity")
      private String entity;
      @Column(name = "amount")
      private Integer amount;
      @Column(name = "currency")
      private String currency;
      @Column(name = "status")
      private String status;
      @Column(name = "method")
      private String method;
      @Column(name = "description")
      private String description;
      @Column(name = "amount_refunded")
      private Integer amount_refunded;
      @Column(name = "refund_status")
      private String refund_status;
      @Column(name = "email")
      private String email;
      @Column(name = "contact")
      private String contact;
      @Column(name = "fee")
      private Integer fee;
      @Column(name = "tax")
      private Integer tax;
      @Column(name = "error_code")
      private String error_code;
      @Column(name = "error_description")
      private String error_description;
      @Column(name = "created_at")
      private Timestamp created_at;

      public String getEntity() {
            return entity;
      }

      public void setEntity(String entity) {
            this.entity = entity;
      }

      public Integer getAmount() {
            return amount;
      }

      public void setAmount(Integer amount) {
            this.amount = amount;
      }

      public String getCurrency() {
            return currency;
      }

      public void setCurrency(String currency) {
            this.currency = currency;
      }

      public String getStatus() {
            return status;
      }

      public void setStatus(String status) {
            this.status = status;
      }

      public String getMethod() {
            return method;
      }

      public void setMethod(String method) {
            this.method = method;
      }

      public String getDescription() {
            return description;
      }

      public void setDescription(String description) {
            this.description = description;
      }

      public Integer getAmount_refunded() {
            return amount_refunded;
      }

      public void setAmount_refunded(Integer amount_refunded) {
            this.amount_refunded = amount_refunded;
      }

      public String getRefund_status() {
            return refund_status;
      }

      public void setRefund_status(String refund_status) {
            this.refund_status = refund_status;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getContact() {
            return contact;
      }

      public void setContact(String contact) {
            this.contact = contact;
      }

      public Integer getFee() {
            return fee;
      }

      public void setFee(Integer fee) {
            this.fee = fee;
      }

      public Integer getTax() {
            return tax;
      }

      public void setTax(Integer tax) {
            this.tax = tax;
      }

      public String getError_code() {
            return error_code;
      }

      public void setError_code(String error_code) {
            this.error_code = error_code;
      }

      public String getError_description() {
            return error_description;
      }

      public void setError_description(String error_description) {
            this.error_description = error_description;
      }

      public Timestamp getCreated_at() {
            return created_at;
      }

      public void setCreated_at(Long created_at) {
            this.created_at = new Timestamp(created_at*1000);
      }
}
