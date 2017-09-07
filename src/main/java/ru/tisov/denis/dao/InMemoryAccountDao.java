package ru.tisov.denis.dao;

import ru.tisov.denis.domain.Account;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author dinyat
 * 06/09/2017
 */
class InMemoryAccountDao implements AccountDao {

    private final ConcurrentHashMap<Long, Account> accounts = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    @Override
    public Account create(BigDecimal initialBalance) {
        long id = idSequence.incrementAndGet();
        Account account = new Account(id, initialBalance);
        accounts.put(id, account);
        return account;
    }

    @Override
    public Account get(long id) {
        return accounts.get(id);
    }
}
