package zq.chapter1.domain;

import zq.chapter1.SimpleServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

    private static final int BUFFER_SIZE = 1024;

    private OutputStream out;

    private zq.chapter1.domain.Request request;

    public Response(OutputStream out) {
        this.out = out;
    }

    public void setRequest(zq.chapter1.domain.Request request) {
        this.request = request;
    }


    public void sendStaticResource() throws IOException{
        byte [] bytes = new byte[BUFFER_SIZE];

        FileInputStream fis = null;

        try {
            File file = new File(SimpleServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                String responseHeader = "HTTP/1.1 200\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n";
                out.write(responseHeader.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0 , BUFFER_SIZE);
                while (ch != -1) {
                    out.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0 , BUFFER_SIZE);
                }
            } else {
                String errorResponse = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                out.write(errorResponse.getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            fis.close();
        }
    }
}
