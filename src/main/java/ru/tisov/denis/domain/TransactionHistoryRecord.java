package ru.tisov.denis.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author dinyat
 * 06/09/2017
 */
public class TransactionHistoryRecord {

    private final Long fromAccountId;
    private final Long toAccountId;
    private final BigDecimal amount;
    private final LocalDateTime time;

    public TransactionHistoryRecord(Long fromAccountId, Long toAccountId, BigDecimal amount, LocalDateTime time) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.time = time;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "TransactionHistoryRecord{" +
            "fromAccountId=" + fromAccountId +
            ", toAccountId=" + toAccountId +
            ", amount=" + amount +
            ", time=" + time +
            '}';
    }
}
