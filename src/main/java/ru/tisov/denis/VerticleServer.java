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

//    http://vertx.io/docs/vertx-core/java/#_writing_http_servers_and_clients
//    http://vertx.io/docs/vertx-unit/java/
//    http://vertx.io/docs/vertx-web/java/
//    https://github.com/vert-x3/vertx-examples/blob/master/web-examples/src/main/java/io/vertx/example/web/rest/SimpleREST.java
//    https://github.com/npryce/goos-code-examples/blob/master/testing-multithreaded-code/src/book/example/threading/races/MultithreadedStressTester.java

//    Написать кастомные exception
//    Продумать все fail пути

    @Override
    public void start(Future<Void> fut) {
        Handler<RoutingContext> transferHandler = new TransferHandler(TransferServiceFactory.getTransferService());
        AccountHandler accountHandler = new AccountHandler(AccountDaoFactory.getAccountDao());

        Router router = Router.router(vertx);
        vertx
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(8080, result -> {
                if (result.succeeded()) {
                    fut.complete();
                } else {
                    fut.fail(result.cause());
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
