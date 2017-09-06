package ru.tisov.denis.service;

import ru.tisov.denis.dao.AccountDao;
import ru.tisov.denis.domain.Account;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 06/09/2017
 */
class DefaultTransferService implements TransferService {


    private final AccountDao accountDao;

    DefaultTransferService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public synchronized void transfer(long sourceAccountId, long destinationAccountId, BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("You should specify transfer amount");
        }

        Account sourceAccount = accountDao.get(sourceAccountId);
        Account destinationAccount = accountDao.get(destinationAccountId);

        sourceAccount.subtractBalance(amount);
        destinationAccount.addBalance(amount);
    }
}
