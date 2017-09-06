package ru.tisov.denis.domain;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 06/09/2017
 */
public class Account {

    private final long id;
    private BigDecimal balance;

    public Account(long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public void addBalance(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void subtractBalance(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", balance=" + balance +
            '}';
    }
}
