package ru.tisov.denis;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import ru.tisov.denis.dao.AccountDaoFactory;
import ru.tisov.denis.handler.AccountHandler;
import ru.tisov.denis.handler.TransferHandler;
import ru.tisov.denis.service.TransferServiceFactory;

/**
 * @author dinyat
 * 06/09/2017
 */
public class VerticleServer extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(VerticleServer.class.getName(), System.out::println);
    }

    @Override
    public void start(Future<Void> future) {
        Handler<RoutingContext> transferHandler = new TransferHandler(TransferServiceFactory.getTransferService());
        AccountHandler accountHandler = new AccountHandler(AccountDaoFactory.getAccountDao());

        Router router = Router.router(vertx);
        vertx
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(8080, result -> {
                if (result.succeeded()) {
                    future.complete();
                } else {
                    future.fail(result.cause());
                }
            });

        router.post("/account").handler(accountHandler::create);
        router.post("/transfer").handler(transferHandler);
        router.get("/").handler(this::index);
    }

    private void index(RoutingContext routingContext) {
        routingContext.response().end("<h1>It works!</h1>");
    }

}
