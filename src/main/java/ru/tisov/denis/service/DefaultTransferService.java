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
    public void transfer(long sourceAccountId, long destinationAccountId, BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("You should specify transfer amount");
        }

        Account sourceAccount = accountDao.get(sourceAccountId);
        Account destinationAccount = accountDao.get(destinationAccountId);

        Account firstMonitor;
        Account secondMonitor;
        if (sourceAccountId < destinationAccountId) {
            firstMonitor = sourceAccount;
            secondMonitor = destinationAccount;
        } else {
            firstMonitor = destinationAccount;
            secondMonitor = sourceAccount;
        }

        synchronized (firstMonitor) {
            synchronized (secondMonitor) {
                if (sourceAccount.getBalance().compareTo(amount) < 0) {
                    throw new IllegalStateException("Source account doesn't have enough money");
                }

                sourceAccount.subtractBalance(amount);
                destinationAccount.addBalance(amount);
            }
        }


    }
}
