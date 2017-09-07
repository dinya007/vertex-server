package ru.tisov.denis.handler;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import ru.tisov.denis.dao.AccountDao;
import ru.tisov.denis.domain.Account;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 07/09/2017
 */
public class AccountHandler {

    private final AccountDao accountDao;

    public AccountHandler(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void create(RoutingContext routingContext) {
        String initialBalance = routingContext.request().getParam("initial_balance");

        if (initialBalance == null || initialBalance.isEmpty()) {
            initialBalance = "0";
        }

        Account account = accountDao.create(BigDecimal.valueOf(Double.valueOf(initialBalance)));
        routingContext
            .response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(account));
    }

}
