package ru.tisov.denis.handler;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import ru.tisov.denis.service.TransferService;

import java.math.BigDecimal;

/**
 * @author dinyat
 * 06/09/2017
 */
public class TransferHandler implements Handler<RoutingContext> {

    private final TransferService transferService;

    public TransferHandler(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        Long fromAccountId = Long.valueOf(routingContext.request().getParam("from"));
        Long toAccountId = Long.valueOf(routingContext.request().getParam("to"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(routingContext.request().getParam("amount")));

        try {
            transferService.transfer(fromAccountId, toAccountId, amount);
            routingContext.response().end();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            routingContext.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end();
        }

    }
}
