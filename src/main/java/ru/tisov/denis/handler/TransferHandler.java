package ru.tisov.denis.handler;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import ru.tisov.denis.service.TransferService;

import java.math.BigDecimal;

import static ru.tisov.denis.utils.StringUtils.isEmpty;

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
        String fromParam = routingContext.request().getParam("from");
        String toParam = routingContext.request().getParam("to");
        String amountParam = routingContext.request().getParam("amount");

        if (!checkParams(fromParam, toParam, amountParam)) {
            routingContext.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end();
            return;
        }

        Long fromAccountId = Long.valueOf(fromParam);
        Long toAccountId = Long.valueOf(toParam);
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(amountParam));

        try {
            transferService.transfer(fromAccountId, toAccountId, amount);
            routingContext.response().end();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            routingContext.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end();
        }

    }

    private boolean checkParams(String to, String from, String amount) {
        return !isEmpty(to) && !isEmpty(from) && !isEmpty(amount);
    }

}
