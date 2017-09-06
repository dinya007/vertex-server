package ru.tisov.denis;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

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

    @Override
    public void start(Future<Void> fut) {
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

        router.get("/transfer/:from/:to").handler(this::transfer);
        router.get("/").handler(this::handleIndex);

    }

    private void handleIndex(RoutingContext routingContext) {
        routingContext.response().end("<h1>Hello from my first Vert.x 3 application</h1>");
    }

    private void transfer(RoutingContext routingContext) {
        routingContext.response().end("transfering...");
    }

}