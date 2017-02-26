package net.proselyte.boxoffice.controller;


import net.proselyte.boxoffice.controller.request_handlers.RequestHandler;
import net.proselyte.boxoffice.controller.request_handlers.RequestHandlerFactory;
import net.proselyte.boxoffice.dao.BoxOfficeDaoFactory;
import net.proselyte.boxoffice.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The BoxOfficeController class is responsible for the
 * managing JSP requests.
 *
 * @author deniskovpaka
 */
@WebServlet(name = "/boxoffice", urlPatterns = "/")
public class BoxOfficeController extends HttpServlet {
    public static final String JSP_REQUEST_NAME = "jspRequest";
    private DaoFactory factory;
    private Connection connection;

    /**
     * Initialize a DAO Factory and
     * connect to DB.
     * @throws ServletException
     */
    public void init() throws ServletException {
        System.out.println("init() ");
        factory = new BoxOfficeDaoFactory();
        try {
            connection = (Connection) factory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method set initialize request in order to
     * call initial JSP file.
     * It should be called only once during initialization
     * of the BoxOfficeController.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doGet() ");
        req.getRequestDispatcher(RequestHandler.JSP_INIT_FILENAME).forward(req, resp);
    }

    /**
     * This method uses for receiving all requests from jsp files
     * and processing them in appropriate request handler.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doPost() ");
        String jspRequestName = req.getParameter(JSP_REQUEST_NAME);
        if (jspRequestName != null) {
            RequestHandler requestHandler =
                    RequestHandlerFactory.createRequestHandler(jspRequestName, factory, connection);
            requestHandler.processRequest(req, resp);
        } else {
            throw new UnsupportedOperationException("JSP Request is NULL!!!");
        }
    }
}