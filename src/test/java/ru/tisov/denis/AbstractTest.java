package ru.tisov.denis;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.function.Consumer;

/**
 * @author dinyat
 * 07/09/2017
 */
@RunWith(VertxUnitRunner.class)
public abstract class AbstractTest {

    private Vertx vertx;
    private TestContext testContext;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(VerticleServer.class.getName(), context.asyncAssertSuccess());
        testContext = context;
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    protected void get(String url, Consumer<HttpClientResponse> responseConsumer) {
        Async async = testContext.async();

        vertx.createHttpClient().getNow(8080, "localhost", url,
            response -> {
                responseConsumer.accept(response);
                async.complete();
            });
    }

    protected void post(String url, Consumer<HttpClientResponse> responseConsumer) {
        Async async = testContext.async();

        vertx.createHttpClient().post(8080, "localhost", url,
            response -> {
                responseConsumer.accept(response);
                async.complete();
            })
            .end();
    }

}
