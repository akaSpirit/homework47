package lesson46;

import com.sun.net.httpserver.HttpExchange;
import server.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Lesson47Server extends Lesson46Server{
    public Lesson47Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/info", this::infoHandler);
        registerGet("/query", this::handleQueryRequest);
    }

    private void handleQueryRequest(HttpExchange exchange) {
        String query = getQueryParams(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");

        Map<String, Object> data = new HashMap<>();
        data.put("params", params);
        renderTemplate(exchange, "query.ftl", data);
    }

    private void infoHandler(HttpExchange exchange) {
        renderTemplate(exchange, "info.html", new DataModel());
    }

}
