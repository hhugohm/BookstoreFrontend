package boosktore.frontend.servlets;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.api.UserDAO;
import bookstore.backend.datamodel.Book;
import bookstore.backend.datamodel.User;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hhugohm
 */
public class BookstoreController extends HttpServlet {

    @EJB
    private BookstoreDAO bookstoreDAO;
    
    @EJB
    private UserDAO userDAO;

    private static final String CATALOG = "/catalog";            //User wants to view catalog
    private static final String BOOK = "/book";                  //User wants to view book details
    private static final String LOGIN = "/login";               // User wants to login
    private static final String START_SESSION = "/start_session";//User wants to start login

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("###### -> init()");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> optional = Optional.ofNullable(request.getPathInfo());
        String uri = "NOT_VALUE";

        if (optional.isPresent()) {
            uri = optional.get();
        }
        System.out.println("SERVLET::::");

        switch (uri) {
            case CATALOG: {
                System.out.println("processRequestCatalog::::");
                processRequestCatalog(request, response);
                break;
            }
            case BOOK: {
                System.out.println("processRequestBook::::");
                processRequestBook(request, response);
                break;
            }
            case LOGIN: {
                System.out.println("processRequestLogin::::");
                processRequestStartSession(request, response);
               
                break;
            }
            case START_SESSION: {
                System.out.println("processRequestStartSession::::");
                 processRequestLogin(request, response);
                break;
            }
            default: {
                this.dispatch(request, response, "/pages/jsp/notValue.jsp");
                break;
            }
        }
    }

    private void processRequestCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //extract requets parameters
        //NA

        //invoke Model
        List<Book> books = this.bookstoreDAO.getAllBooks();

        //prepare data
        request.setAttribute("books", books);
        this.dispatch(request, response, "/pages/jsp/catalog.jsp");

    }

    private void processRequestBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pId = Integer.parseInt(Optional.ofNullable(request.getParameter("pId")).get());

        Book book = this.bookstoreDAO.getBookById(pId);
        System.out.println("######BOOK: " + book.toString());

        request.setAttribute("book", book);
        this.dispatch(request, response, "/pages/jsp/bookDetails.jsp");
    }

    private void processRequestLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //extract requets parameters
        String user = Optional.ofNullable(request.getParameter("pUsername")).get();
        String password = Optional.ofNullable(request.getParameter("pPassword")).get();
        
       User userDB =  this.userDAO.getUserByKey(user,password);
        System.out.println("######USER: " + userDB.toString());

        //invoke Model
        //PENDING

        //prepare data
        //NA
        
        //Invoke view
        this.dispatch(request, response, "/pages/jsp/notValue.jsp");
    }
    private void processRequestStartSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //extract requets parameters
        //NA

        //invoke Model
        //NA

        //prepare data
        //NA
        
        //Invoke view
        this.dispatch(request, response, "/pages/jsp/login.jsp");
        
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request, response);
        //rd.include(request, response);
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("###### -> destroy()");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
