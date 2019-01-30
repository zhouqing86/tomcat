package zq.chapter2.domain;

import zq.chapter2.SimpleServletServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class Response implements ServletResponse{

    private static final int BUFFER_SIZE = 1024;

    private OutputStream out;

    private Request request;

    private PrintWriter writer;

    public Response(OutputStream out) {
        this.out = out;
    }

    public void setRequest(Request request) {
        this.request = request;
    }


    public void sendStaticResource() throws IOException{
        byte [] bytes = new byte[BUFFER_SIZE];

        FileInputStream fis = null;

        try {
            File file = new File(SimpleServletServer.WEB_ROOT, request.getUri());
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

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(out, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long length) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
