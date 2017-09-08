package ru.tisov.denis.service;

import org.junit.Assert;
import org.junit.Test;
import ru.tisov.denis.dao.AccountDao;
import ru.tisov.denis.dao.AccountDaoFactory;
import ru.tisov.denis.domain.Account;
import ru.tisov.denis.utils.ConcurrentShooter;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 07/09/2017
 */
public class TransferServiceTest {

    private final AccountDao accountDao = AccountDaoFactory.getAccountDao();
    private final TransferService transferService = TransferServiceFactory.getTransferService();

    @Test
    public void testSuccessTransferConcurrently() throws Exception {
        Account account1 = accountDao.create(BigDecimal.valueOf(1_000_000));
        Account account2 = accountDao.create(BigDecimal.ZERO);
        ConcurrentShooter concurrentShooter = new ConcurrentShooter(4, 250_000);

        concurrentShooter.shootConcurrently(() -> {
            transferService.transfer(account1.getId(), account2.getId(), BigDecimal.ONE);
        });

        BigDecimal balance1 = accountDao.get(account1.getId()).getBalance();
        BigDecimal balance2 = accountDao.get(account2.getId()).getBalance();
        Assert.assertTrue(BigDecimal.ZERO.compareTo(balance1) == 0);
        Assert.assertTrue(BigDecimal.valueOf(1_000_000).compareTo(balance2) == 0);
    }

    @Test(timeout = 5000)
    public void testSuccessTransferWithoutDeadlock() throws Exception {
        Account account1 = accountDao.create(BigDecimal.valueOf(1_000_000_000));
        Account account2 = accountDao.create(BigDecimal.valueOf(1_000_000_000));
        ConcurrentShooter concurrentShooter = new ConcurrentShooter(4, 250_000);

        concurrentShooter.shootConcurrently(() -> {
            transferService.transfer(account1.getId(), account2.getId(), BigDecimal.ONE);
            transferService.transfer(account2.getId(), account1.getId(), BigDecimal.TEN);
        });

        BigDecimal balance1 = accountDao.get(account1.getId()).getBalance();
        BigDecimal balance2 = accountDao.get(account2.getId()).getBalance();
        Assert.assertTrue(BigDecimal.valueOf(1_009_000_000).compareTo(balance1) == 0);
        Assert.assertTrue(BigDecimal.valueOf(991_000_000).compareTo(balance2) == 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testTransferMoreThanAvailable() throws Exception {
        Account account1 = accountDao.create(BigDecimal.valueOf(1_000));
        Account account2 = accountDao.create(BigDecimal.ZERO);

        transferService.transfer(account1.getId(), account2.getId(), BigDecimal.valueOf(1001));
    }
}
