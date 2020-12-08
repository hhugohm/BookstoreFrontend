package boosktore.frontend.servlets;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.datamodel.Book;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "ImagesServlet", urlPatterns = {"/images"})
@MultipartConfig(location = "C:\\Users\\vsarabia\\Home\\playground\\sandbox\\foreign\\training\\cursojee\\bookstore\\upload")
public class ImagesServlet extends HttpServlet {
    
    @EJB
    private BookstoreDAO bookstoreDAO;
    
    private static final String ACTION_READ = "R";
    private static final String ACTION_UPLOAD = "U";
    private static final String ACTION_PREVIEW = "P";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("pAction");
        
        switch(action){
            case "R":
                this.processRequestReadImage(request, response);
                break;
            case "U":
                this.processRequestUploadImage(request, response);
                break;
            case "P":
                this.processRequestPreviewImage(request, response);
                break;
        }
        
    }
    
    private void processRequestReadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        int id = Integer.parseInt(request.getParameter("pId"));
        
        // Invoke M:
        Book book = this.bookstoreDAO.getBookById(id);
        
        byte[] imageBytes = book.getImage();
        
        // Prepare content to return:
        OutputStream out = response.getOutputStream();
        out.write(imageBytes);
        out.close();
    }
    
    private void processRequestUploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parameters:
        int id = Integer.parseInt(request.getParameter("pId"));
        
        // Extract request parts:
        Part imagePart = request.getPart("pImage");
        long imageSize = imagePart.getSize();
        
        byte[] imageBytes = new byte[(int)imageSize];
        
        InputStream is = imagePart.getInputStream();
        is.read(imageBytes);
        
        // Invoke M:
        Book book = this.bookstoreDAO.getBookById(id);
        book.setImage(imageBytes);
        
        this.bookstoreDAO.update(book);
        
        response.sendRedirect("controller/manage?pAction=EDIT&pId=" + id);
    }
    
    private void processRequestPreviewImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract request parts:
        Part imagePart = request.getPart("pImage");
        long imageSize = imagePart.getSize();
        
        byte[] imageBytes = new byte[(int)imageSize];
        
        InputStream is = imagePart.getInputStream();
        is.read(imageBytes);
        
        // Prepare content to return:
        OutputStream out = response.getOutputStream();
        out.write(imageBytes);
        out.close();
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
