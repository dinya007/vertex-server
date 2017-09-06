package ru.tisov.denis.dao;

import ru.tisov.denis.domain.Account;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dinyat
 * 06/09/2017
 */
class InMemoryAccountDao implements AccountDao {

    private final ConcurrentHashMap<Long, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public void create(Account account) {
        accounts.putIfAbsent(account.getId(), account);
    }

    @Override
    public Account get(long id) {
        return accounts.get(id);
    }
}
