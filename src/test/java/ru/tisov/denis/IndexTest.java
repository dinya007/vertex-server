package ru.tisov.denis;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;

/**
 * @author dinyat
 * 07/09/2017
 */
public class IndexTest extends AbstractTest {

    @Test
    public void testIndex(TestContext context) throws Exception {
        get("/", response -> {
            response.handler(body -> {
                context.assertEquals("<h1>It works!</h1>", body.toString());
            });
        });
    }

}
