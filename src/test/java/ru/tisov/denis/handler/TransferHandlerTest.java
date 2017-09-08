package ru.tisov.denis.handler;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import ru.tisov.denis.AbstractTest;
import ru.tisov.denis.dao.AccountDao;
import ru.tisov.denis.dao.AccountDaoFactory;
import ru.tisov.denis.domain.Account;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 06/09/2017
 */
public class TransferHandlerTest extends AbstractTest {

    @Test
    public void testSuccessTransfer(TestContext context) {
        AccountDao accountDao = AccountDaoFactory.getAccountDao();
        Account account1 = accountDao.create(BigDecimal.valueOf(1000));
        Account account2 = accountDao.create(BigDecimal.ZERO);
        String url = String.format("/transfer?from=%s&to=%s&amount=%s", account1.getId(), account2.getId(), 100.50);

        post(url, response -> {
            context.assertEquals(200, response.statusCode());
            context.assertTrue(BigDecimal.valueOf(899.50).compareTo(accountDao.get(account1.getId()).getBalance()) == 0);
            context.assertTrue(BigDecimal.valueOf(100.50).compareTo(accountDao.get(account2.getId()).getBalance()) == 0);
        });
    }

    @Test
    public void testTransferWithEmptyParams(TestContext context) {
        AccountDao accountDao = AccountDaoFactory.getAccountDao();
        Account account1 = accountDao.create(BigDecimal.valueOf(1000));
        Account account2 = accountDao.create(BigDecimal.ZERO);
        String url = String.format("/transfer?from=%s&to=%s", account1.getId(), account2.getId());

        post(url, response -> {
            context.assertEquals(400, response.statusCode());
        });
    }

}
