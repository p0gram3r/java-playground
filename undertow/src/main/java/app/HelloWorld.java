package app;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.resource.PathResourceManager;

import java.nio.file.Path;
import java.nio.file.Paths;

import static io.undertow.Handlers.resource;

public class HelloWorld {

    public static void main(final String[] args) {
        //        HttpHandler handler = new HttpHandler() {
        //            @Override
        //            public void handleRequest(final HttpServerExchange exchange) throws Exception {
        //                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        //                exchange.getResponseSender().send("Hello World");
        //            }
        //        };

        Path base = Paths.get(System.getProperty("user.home"), "Dropbox/Pictures/dilbert/");
        HttpHandler handler = resource(new PathResourceManager(base, 100))
                .setDirectoryListingEnabled(true);

        Undertow server = Undertow
                .builder()
                .addHttpListener(8080, "localhost")
                .setHandler(handler)
                .build();
        server.start();
    }
}