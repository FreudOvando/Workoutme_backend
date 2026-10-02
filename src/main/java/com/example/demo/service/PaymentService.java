package com.example.demo.service;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.exception.MonthlyFeeNotSetException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.PaymentMapper;
import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentResponse create(PaymentRequest request, boolean isAdmin) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id " + request.userId()));

        BigDecimal amount;
        if (isAdmin && request.amount() != null) {
            amount = request.amount();
        } else {
            if (user.getMonthlyFee() == null) {
                throw new MonthlyFeeNotSetException("Tu entrenador todavía no configuró tu mensualidad");
            }
            amount = user.getMonthlyFee();
        }

        LocalDate paymentDate = request.paymentDate() != null ? request.paymentDate() : LocalDate.now();

        Payment payment = Payment.builder()
                .user(user)
                .amount(amount)
                .paymentDate(paymentDate)
                .nextDueDate(paymentDate.plusDays(30))
                .build();

        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> findAllByUser(Long userId) {
        return paymentRepository.findAllByUserIdOrderByPaymentDateDesc(userId)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PaymentResponse> findAll() {
        return paymentRepository.findAllByOrderByPaymentDateDesc()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }
}
