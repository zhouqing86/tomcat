package zq.chapter2;

import zq.chapter2.domain.Request;
import zq.chapter2.domain.Response;

import java.io.IOException;

public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
