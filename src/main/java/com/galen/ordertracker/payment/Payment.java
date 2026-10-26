package com.galen.ordertracker.payment;

import com.galen.ordertracker.order.Order;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant paidAt;

    protected Payment() {}

    public Payment(Order order, PaymentMethod method, BigDecimal amount) {
        this.order = order;
        this.method = method;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

}
