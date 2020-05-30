package boosktore.frontend.servlets;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.datamodel.Book;
import java.io.IOException;
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
    
    private static final String CATALOG="/catalog"; //user wants to view catalog
    private static final String BOOK="/book";       // User wants to view book details
    
    @Override
    public void init() throws ServletException{
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
        String uri="NOT_VALUE";
        
        if(optional.isPresent()){
            uri=optional.get();
        }
        
        
        
       System.out.println("SERVLET::::");
        
        switch(uri){
            case CATALOG:{
                processRequestCatalog(request,response);
                break;
            }
           
             case BOOK:{
                System.out.println("processRequestBook::::");
                 processRequestBook(request,response);
                break;
            }  
                
            default:{
                this.dispatch(request,response,"/pages/jsp/notValue.jsp");
                break;
            }
        }
    }
    private void processRequestCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    
    }
     private void processRequestBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         int pId= Integer.parseInt(Optional.ofNullable(request.getParameter("pId")).get());
    
         Book book = bookstoreDAO.getBookById(pId);
         System.out.println("######BOOK: " + book.toString());
         
         request.setAttribute("book", book);
         this.dispatch(request,response,"/pages/jsp/bookDetails.jsp");
     }
     
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String target)throws ServletException, IOException {
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
