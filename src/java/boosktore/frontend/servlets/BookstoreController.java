package boosktore.frontend.servlets;

import boosktore.frontend.business.LibraryBusinessI;
import boosktore.frontend.business.Simple;
import boosktore.frontend.domain.Response;
import java.io.IOException;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hhugohm
 */
@WebServlet(urlPatterns = {"/controller/*"}, name = "BookstoreController")
public class BookstoreController extends HttpServlet {

    @Inject
    @Simple
    private LibraryBusinessI libraryBusinessI;

    private static final String CATALOG = "/catalog";            //User wants to view catalog
    private static final String BOOK = "/book";                  //User wants to view book details
    private static final String LOGIN = "/login";               // User wants to login
    private static final String START_SESSION = "/start_session";//User wants to start login
    private static final String LOGOUT = "/logout";//User wants to logout
    private static final String MANAGE = "/manage";//User wants to edit or add a book
    private static final String MANAGE_ACTION="/manage_action";

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("###### -> init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> optional = Optional.ofNullable(request.getPathInfo());
        String uri = "NOT_VALUE";
        Response responseDomain = null;

        if (optional.isPresent()) {
            uri = optional.get();
        }
        System.out.println("SERVLET:::: " + uri);

        switch (uri) {
            case CATALOG: {
                System.out.println("processRequestCatalog::::");
                responseDomain = libraryBusinessI.processRequestCatalog(request, response);
                break;
            }
            case BOOK: {
                System.out.println("processRequestBook::::");
                responseDomain = libraryBusinessI.processRequestBook(request, response);
                break;
            }
            case LOGIN: {
                System.out.println("processRequestLogin::::");
                responseDomain=libraryBusinessI.processRedirectLogin(request,response);
                break;
            }
            case START_SESSION: {
                System.out.println("processRequestStartSession::::");
                responseDomain = libraryBusinessI.processRequestStartSession(request, response);
                break;
            }
            case LOGOUT: {
                System.out.println("processRequestLogout::::");
                responseDomain = libraryBusinessI.processRequestLogout(request, response);
                break;
            }
             case MANAGE: {
                System.out.println("processRequesManage::::");
                responseDomain = libraryBusinessI.processRequesManage(request, response);
                break;
            }
              case MANAGE_ACTION: {
                System.out.println("processRequesManageAction::::");
                responseDomain = libraryBusinessI.processRequesManageAction(request, response);
                break;
            }
            default: {
                responseDomain=libraryBusinessI.processDefault(request,response);
                break;
            }
        }
        this.dispatch(responseDomain);
    }

    private void dispatch(Response responseDomain) throws ServletException, IOException {
       if(responseDomain.isSendRedirect()){
            responseDomain.getResponse().sendRedirect(responseDomain.getTarget());
        }else{
             RequestDispatcher rd = responseDomain.getRequest().getRequestDispatcher(responseDomain.getTarget());
             rd.forward(responseDomain.getRequest(), responseDomain.getResponse());
        }
     
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
