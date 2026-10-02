package com.example.demo.mapper;

import com.example.demo.dto.PaymentResponse;
import com.example.demo.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getUser().getId(),
                payment.getUser().getFirstName() + " " + payment.getUser().getLastName(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getNextDueDate(),
                payment.getCreatedAt()
        );
    }
}