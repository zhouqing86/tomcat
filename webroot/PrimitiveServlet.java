
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * This class is for test SimpleServletServer, after run SimpleServletServer, visit http://localhost:8080/servlet/PrimitiveServlet then visit the servlet
 * Remember to add PrimitiveServer under webroot path, and add webroot as sources directory
 */
public class PrimitiveServlet implements Servlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Init " + this.getClass().getName());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("From service ==== ");
        String responseHeader = "HTTP/1.1 200\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n";
        PrintWriter out = res.getWriter();
        out.println(responseHeader);
        out.println("Hello from servlet! ==== ");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Destroy" + this.getClass().getName());
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
