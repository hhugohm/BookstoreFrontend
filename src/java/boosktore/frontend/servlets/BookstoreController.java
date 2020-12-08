package boosktore.frontend.servlets;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.api.ShoppingCartService;
import bookstore.backend.api.UserDAO;
import bookstore.backend.datamodel.Book;
import bookstore.backend.datamodel.User;
import bookstore.backend.datamodel.enums.Availability;
import bookstore.backend.datamodel.enums.BookFormat;
import boosktore.frontend.beans.CartBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookstoreController extends HttpServlet {
    
    @EJB
    private BookstoreDAO bookstoreDAO;
    
    @EJB
    private UserDAO userDAO;
    
   
    
    private static final String CATALOG = "/catalog";                 // User wants to view catalog
    private static final String BOOK = "/book";                       // User wants to view book details (http://.../.../book?pId=4)
    private static final String LOGIN = "/login";                     // User wants to login in
    private static final String START_SESSION = "/start_session";     // User wants to start session
    private static final String LOGOUT = "/logout";                   // User wants to logout
    private static final String MANAGE = "/manage";                   // User wants to add or edit a book
    private static final String MANAGE_ACTION = "/manage_action";     // User request add, edit, or delete
    private static final String CART = "/cart";                       // User wants to view his/her cart
    private static final String ADD_TO_CART = "/addToCart";           // User wants to add a book to cart
    private static final String REMOVE_FROM_CART = "/removeFromCart"; // User wants to remove a book from cart
    
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("### init()");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String uri = request.getPathInfo();
        
        switch(uri){
            case CATALOG:
                this.processRequestCatalog(request, response);
                break;
            case BOOK:
                this.processRequestBook(request, response);
                break;
            case LOGIN:
                this.processRequestLogin(request, response);
                break;
            case START_SESSION:
                this.processRequestStartSession(request, response);
                break;
            case LOGOUT:
                this.processRequestLogout(request, response);
                break;
            case MANAGE:
                this.processRequestManage(request, response);
                break;
            case MANAGE_ACTION:
                this.processRequestManageAction(request, response);
                break;
            case CART:
                this.processRequestCart(request, response);
                break;
            case ADD_TO_CART:
                this.processRequestAddToCart(request, response);
                break;
            case REMOVE_FROM_CART:
                this.processRequestRemoveFromCart(request, response);
                break;
        }
        
    }
    
    private void processRequestCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        // NA
        
        // Invoke M:
        List<Book> books = this.bookstoreDAO.getAllBooks();
        
        // Prepare data:
        request.setAttribute("books", books);
        
        // Invoke V:
        this.dispatch(request, response, "/pages/jsp/catalog.jsp");
    }
    
    private void processRequestBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        int id = Integer.parseInt(request.getParameter("pId"));
        
        // Invoke M:
        Book book = this.bookstoreDAO.getBookById(id);
        
        // Prepare data:
        request.setAttribute("book", book);
        
        // Invoke V:
        this.dispatch(request, response, "/pages/jsp/bookDetails.jsp");
        //this.dispatch(request, response, "/pages/jsp/book.jsp");
    }
    
    private void processRequestLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        // NA
        
        // Invoke M:
        // NA
        
        // Prepare data:
        // NA
        
        // Invoke V:
        this.dispatch(request, response, "/pages/jsp/login.jsp");
    }
    
    private void processRequestStartSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        String username = request.getParameter("pUsername");
        String password = request.getParameter("pPassword");
        
        // Invoke M:
        //User user = this.userDAO.getUserByUsernamePassword(username, password);
        
        boolean userIsValid = false;
        try{
            request.login(username, password);
            userIsValid=true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("########## Invalid user!!");
        }
        
        
        // Prepare data:
        if(userIsValid){
            User user = this.userDAO.getUserByUsernamePassword(username, password);
            // Create session:
            HttpSession session = request.getSession();
            session.setAttribute("sessionActive", "Y");
            /*
            session.setAttribute("sessionUserId", user.getId());
            session.setAttribute("sessionUsername", user.getName());
            */
            session.setAttribute("sessionUser", user);
            session.setAttribute("sessionType", user.getUserType().toString());
            
            // Invoke V:
            response.sendRedirect("catalog");
            
        }else{
            this.dispatch(request, response, "/pages/jsp/login.jsp");
        }
        
    }
    
    private void processRequestLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate session:
        HttpSession session = request.getSession();
        session.invalidate();
        
        // Invoke V:
        response.sendRedirect("catalog");
    }
    
    private void processRequestManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        String action = request.getParameter("pAction");
        int id = 0;
        Book book = null;
        if(action.equals("EDIT")){
            id = Integer.parseInt(request.getParameter("pId"));
            
            // Invoke M:
            book = this.bookstoreDAO.getBookById(id);
            
            // Prepare data:
            request.setAttribute("book", book);
        }
        
        // Invoke V:
        this.dispatch(request, response, "/pages/jsp/manageBook.jsp");
    }
    
    private void processRequestManageAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("pAction");
        switch(action){
            case "ADD":
                this.processRequestAdd(request, response);
                break;
            case "DELETE":
                this.processRequestDelete(request, response);
                break;
            case "EDIT":
                this.processRequestEdit(request, response);
                break;
        }
    }
    
    private void processRequestAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        String title = request.getParameter("pTitle");
        String description = request.getParameter("pDescription");
        String author = request.getParameter("pAuthor");
        String publisher = request.getParameter("pPublisher");
        Date publishedDate = null;
        try{
            publishedDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("pPublishedDate"));
        }catch(Exception e){}
        String language = request.getParameter("pLanguage");
        int numberOfPages = Integer.parseInt(request.getParameter("pNumberOfPages"));
        BookFormat format = BookFormat.valueOf(request.getParameter("pFormat"));
        double price = Double.parseDouble(request.getParameter("pPrice"));
        Availability availability = Availability.valueOf(request.getParameter("pAvailability"));
        int stockQuantity = Integer.parseInt(request.getParameter("pStockQuantity"));
        
        // Invoke M:
        
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publishedDate);
        book.setLanguage(language);
        book.setNumberOfPages(numberOfPages);
        book.setFormat(format);
        book.setPrice(price);
        book.setAvailability(availability);
        book.setStockQuantity(stockQuantity);
        
        this.bookstoreDAO.insert(book);
        
        // Prepare data:
        // NA
        
        // Invoke V:
        this.dispatch(request, response, "catalog");
        
    }
    
    private void processRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        int id = Integer.parseInt(request.getParameter("pId"));
         Book book = new Book();
         book.setId(id);
        // Invoke M:
        this.bookstoreDAO.delete(book);
        
        // Prepare data:
        // NA
        
        // Invoke V:
        this.dispatch(request, response, "catalog");
    }
    
    private void processRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        int id = Integer.parseInt(request.getParameter("pId"));
        String title = request.getParameter("pTitle");
        String description = request.getParameter("pDescription");
        String author = request.getParameter("pAuthor");
        String publisher = request.getParameter("pPublisher");
        Date publishedDate = null;
        try{
            publishedDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("pPublishedDate"));
        }catch(Exception e){}
        String language = request.getParameter("pLanguage");
        int numberOfPages = Integer.parseInt(request.getParameter("pNumberOfPages"));
        BookFormat format = BookFormat.valueOf(request.getParameter("pFormat"));
        double price = Double.parseDouble(request.getParameter("pPrice"));
        Availability availability = Availability.valueOf(request.getParameter("pAvailability"));
        int stockQuantity = Integer.parseInt(request.getParameter("pStockQuantity"));
        
        // Invoke M:
        
        Book book = this.bookstoreDAO.getBookById(id);
        
        book.setTitle(title);
        book.setDescription(description);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publishedDate);
        book.setLanguage(language);
        book.setNumberOfPages(numberOfPages);
        book.setFormat(format);
        book.setPrice(price);
        book.setAvailability(availability);
        book.setStockQuantity(stockQuantity);
        
        this.bookstoreDAO.update(book);
        
        // Prepare data:
        // NA
        
        // Invoke V:
        this.dispatch(request, response, "catalog");
    }

    private void processRequestCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        // [PENDING]
        
        // Invoke M:
       // List<Book> books = this.shoppingCartService.getItems();
        
        // Prepare data:
        //HttpSession session = request.getSession();
        //session.setAttribute("books", books);
        
        // Invoke V:
        this.dispatch(request, response, "/pages/jsp/cart.jsp");
    }
    
    private void processRequestAddToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(":::::processRequestAddToCart::::::: ");

        // Extract request parameters:
        int id = Integer.parseInt(request.getParameter("pId"));
        
        // Invoke M:
        //Reemplazado x el business delegate
        //Book book = this.bookstoreDAO.getBookById(id);
        //this.shoppingCartService.add(book);
        
        // Prepare data:
       HttpSession session = request.getSession();
       CartBean cartBean=null;
       if(session.getAttribute("cartBean")==null){
           cartBean=new CartBean();
           session.setAttribute("cartBean", cartBean);
       }else{
          cartBean= (CartBean) session.getAttribute("cartBean");
       }
               
       cartBean.add(id);
        // Invoke V:
        // NA
    }
    
    private void processRequestRemoveFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Extract request parameters:
        int id = Integer.parseInt(request.getParameter("pId"));
        // Invoke M:
        //Book book = this.bookstoreDAO.getBookById(id);
        //this.shoppingCartService.remove(book);
        
        HttpSession session = request.getSession();
        CartBean cartBean= (CartBean) session.getAttribute("cartBean");
        cartBean.remove(id);
        // Prepare data:
        // NA
        
        // Invoke V:
        // NA
    }
    
    // -------------------------------------------------------------------------
    
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request, response);
    }
    
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("### destroy()");
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

/*
// #. Recibir los requests (se da por hecho)
// 1. Interpretar la interacción del usuario con el V (View)(jsps)
// 2. Extracción de parametros del request (en caso de que aplique)
// 3. Invocar a M (Model)(backend)(en caso de que aplique)
// 4. Preparar datos (en caso de que aplique)
      = Disponer en los scopes los datos que necesitará eventualmente la JSP = subir los datos a scope
// 5. Invocar (ir) el V (jsps)
// #. Regresar la respuesta
*/