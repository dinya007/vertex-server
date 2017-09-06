package ru.tisov.denis.handler;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import ru.tisov.denis.service.TransferService;

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
    public void handle(RoutingContext event) {
        event.response().end("transfering...");
    }
}
