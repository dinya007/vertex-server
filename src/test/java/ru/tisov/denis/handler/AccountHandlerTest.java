package ru.tisov.denis.handler;

import io.vertx.core.json.Json;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import ru.tisov.denis.AbstractTest;
import ru.tisov.denis.domain.Account;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 07/09/2017
 */
public class AccountHandlerTest extends AbstractTest {

    @Test
    public void testCreateAccountWithoutInitialBalance(TestContext context) {
        post("/account", response -> {
            response.handler(body -> {
                Account account = Json.decodeValue(body, Account.class);
                context.assertNotNull(account.getId());
                context.assertTrue(BigDecimal.valueOf(0).compareTo(account.getBalance()) == 0);
            });
        });
    }

    @Test
    public void testCreateAccountWithInitialBalance(TestContext context) {
        post("/account?initial_balance=1000", response -> {
            response.handler(body -> {
                Account account = Json.decodeValue(body, Account.class);
                context.assertNotNull(account.getId());
                context.assertTrue(BigDecimal.valueOf(1000).compareTo(account.getBalance()) == 0);
            });
        });
    }

}
