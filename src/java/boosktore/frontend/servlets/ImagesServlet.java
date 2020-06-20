package boosktore.frontend.servlets;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.datamodel.Book;
import boosktore.frontend.business.ImageBusinessI;
import boosktore.frontend.business.Simple;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author hhugohm
 */
@WebServlet(name = "ImagesServlet", urlPatterns = {"/images"})
@MultipartConfig(location = "/Users/hhugohm/media/upload"   )
public class ImagesServlet extends HttpServlet {

    private static final String ACTION_READ="R";
    private static final String ACTION_UPLOAD="U";
    private static final String ACTION_PREVIEW="P";
    
    
    @Inject
    @Simple
    private ImageBusinessI imageBusinessI;
    
    
     @EJB
    private BookstoreDAO bookstoreDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String action = Optional.ofNullable(request.getParameter("pAction")).get();
        switch(action){
            case ACTION_READ:{
                processRequestReadImage(request,response);
                break; 
            }
             case ACTION_UPLOAD:{
                  processRequestUploadImage(request,response);
                break; 
            } 
             case ACTION_PREVIEW:{
                  processRequestPreviewImage(request,response);
                break; 
            } 
             default:{
                 break;
             }
            
        }

    }
    
    
  private void processRequestReadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("processRequestReadImage::::");
      int id = Integer.parseInt(Optional.ofNullable(request.getParameter("pId")).get());  
       Book book = this.bookstoreDAO.getBookById(id);
       
       byte[] imageBytes = book.getImage();
       
       OutputStream out =  response.getOutputStream();
       out.write(imageBytes);
       out.close();
       
       
  }  
  private void processRequestUploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("processRequestUploadImage::::");
       
       
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("pId")).get());
        
       Book book = this.bookstoreDAO.getBookById(id);
       
       Part imagePart = request.getPart("pImage");
       long imageSize= imagePart.getSize();
       byte[] imageBytes= new byte[(int)imageSize];
       InputStream  inputStream =imagePart.getInputStream();
       inputStream.read(imageBytes);
       
       book.setImage(imageBytes);
       
       this.bookstoreDAO.update(book);
       response.sendRedirect("controller/manage?pAction=EDIT&pId="+id);
       
  
  }  
  private void processRequestPreviewImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("processRequestPreviewImage::::");
       
       Part imagePart = request.getPart("pImage");
       long imageSize= imagePart.getSize();
       byte[] imageBytes= new byte[(int)imageSize];
       InputStream  inputStream =imagePart.getInputStream();
       inputStream.read(imageBytes);
       
       OutputStream out =  response.getOutputStream();
       out.write(imageBytes);
       out.close();
       //response.sendRedirect("controller/manage?pAction=EDIT&pId="+id);
  
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
