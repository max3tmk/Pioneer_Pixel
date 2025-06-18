package com.max.pioneer_pixel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
@Getter
@Setter
@NoArgsConstructor
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_user_id", nullable = false)
    private Long fromUserId;

    @Column(name = "to_user_id", nullable = false)
    private Long toUserId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "transaction_status")
    private TransactionStatus status;

    @Column(name = "error_message")
    private String errorMessage;

    public TransactionHistory(Long fromUserId, Long toUserId, BigDecimal amount,
                              LocalDateTime timestamp, TransactionStatus status, String errorMessage) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
