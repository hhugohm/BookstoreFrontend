/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boosktore.frontend.business;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.api.UserDAO;
import bookstore.backend.datamodel.Book;
import bookstore.backend.datamodel.User;
import bookstore.backend.datamodel.enums.Availability;
import bookstore.backend.datamodel.enums.BookFormat;
import boosktore.frontend.domain.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hhugohm
 */
@Simple
@Named
@RequestScoped
public class LibraryBusinessImpl implements LibraryBusinessI {

    @EJB
    private BookstoreDAO bookstoreDAO;
    @EJB
    private UserDAO userDAO;

    @Override
    public Response processRequestLogin(HttpServletRequest request, HttpServletResponse response) {
        Response responseDomain = null;

        return responseDomain;
    }

    @Override
    public Response processRequestStartSession(HttpServletRequest request, HttpServletResponse response) {
        Response responseDomain = null;
        String user = Optional.ofNullable(request.getParameter("pUsername")).get();
        String password = Optional.ofNullable(request.getParameter("pPassword")).get();

        Optional<User> userOptional = Optional.ofNullable(this.userDAO.getUserByUsernamePassword(user, password));

        if (userOptional.isPresent()) {
            System.out.println("######USER ID: " + userOptional.get().toString());
            HttpSession session = request.getSession();
            session.setAttribute("sessionActive", "Y");
           // session.setAttribute("sessionType", userOptional.get().getUserType().toString());
            session.setAttribute("sessionUserId", userOptional.get().getId());
            session.setAttribute("sessionUserName", userOptional.get().getName());
            session.setAttribute("sessionType", userOptional.get().getUserType().toString());
            session.setAttribute("user", userOptional.get());
            
            System.out.println("$$$$$$$$"+userOptional.get().getUserType().toString() + "-");

            responseDomain = new Response(request, response, "catalog", true);

        } else {
            responseDomain = new Response(request, response, "login", true);
        }
        return responseDomain;
    }
    
    
     @Override
    public Response processRequesManage(HttpServletRequest request, HttpServletResponse response) {
         Response responseDomain = null;
         String action = Optional.ofNullable(request.getParameter("pAction")).get();
         int id;
         Book book;
        
         if(action.equals("EDIT")){
             id= Integer.parseInt(Optional.ofNullable(request.getParameter("pId")).get());
             book = this.bookstoreDAO.getBookById(id);
             request.setAttribute("book", book);
              responseDomain = new Response(request, response, "/pages/jsp/manageBook.jsp");
         }else{
             responseDomain = new Response(request, response, "/pages/jsp/manageBook.jsp");
         }
         
         
         return responseDomain;
    }
    @Override
    public Response processRequesManageAction(HttpServletRequest request, HttpServletResponse response) {
        Response responseDomain = null;
        String action = Optional.ofNullable(request.getParameter("pAction")).get();

        switch (action) {
            case "ADD": {
                this.processRequestAdd(request, response);
                responseDomain = new Response(request, response, "catalog",true);
                break;
            }
            case "DELETE": {
                this.processRequestDelete(request, response);
                 responseDomain = new Response(request, response, "catalog",true);
                break;
            }

            case "EDIT": {
                this.processRequestEdit(request, response);
                 responseDomain = new Response(request, response, "catalog",true);
                break;
            }
            default: {

                break;
            }
        }

        

        return responseDomain;
    }

    public void processRequestAdd(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("processRequestAdd::::");
        this.bookstoreDAO.insert(buildBookFromRequest(request));
    }
    
    public void processRequestDelete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("processRequestDelete::::");
        
         int id = Integer.valueOf(Optional.ofNullable(request.getParameter("pId")).get());
         Book book = new Book();
         book.setId(id);
         this.bookstoreDAO.delete(book);
    }

    public void processRequestEdit(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("processRequestEdit::::");
        int id = Integer.valueOf(Optional.ofNullable(request.getParameter("pId")).get());
        String title = Optional.ofNullable(request.getParameter("pTitle")).get();
        String description = Optional.ofNullable(request.getParameter("pDescription")).get();
        String author = Optional.ofNullable(request.getParameter("pAuthor")).get();
        String publisher = Optional.ofNullable(request.getParameter("pPublisher")).get();

        Date publisherDate = null;
        try {
            publisherDate = new SimpleDateFormat("dd/MM/yyyy").parse(Optional.ofNullable(request.getParameter("pPublisherDate")).get());
        } catch (Exception e) {
            e.printStackTrace();
            publisherDate = new Date();
        }
        String language = Optional.ofNullable(request.getParameter("pLanguage")).get();
        int numberOfPages = Integer.valueOf(Optional.ofNullable(request.getParameter("pNumberOfPages")).get());
        BookFormat format = BookFormat.valueOf(Optional.ofNullable(request.getParameter("pFormat")).get());
        double price = Double.parseDouble(Optional.ofNullable(request.getParameter("pPrice")).get());
        Availability availability = Availability.valueOf(Optional.ofNullable(request.getParameter("pAvailability")).get());
        int stockQuantity = Integer.valueOf(Optional.ofNullable(request.getParameter("pStockQuantity")).get());
        
        Book book= this.bookstoreDAO.getBookById(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publisherDate);
        book.setLanguage(language);
        book.setNumberOfPages(numberOfPages);
        book.setFormat(format);
        book.setPrice(price);
        book.setAvailability(availability);
        book.setStockQuantity(stockQuantity);
        
        
        this.bookstoreDAO.update(book);
    }
    
    private Book buildBookFromRequest(HttpServletRequest request) {

        System.out.println("buildBookFromRequest::::");

        String title = Optional.ofNullable(request.getParameter("pTitle")).get();
        String description = Optional.ofNullable(request.getParameter("pDescription")).get();
        String author = Optional.ofNullable(request.getParameter("pAuthor")).get();
        String publisher = Optional.ofNullable(request.getParameter("pPublisher")).get();

        Date publisherDate = null;
        try {
            publisherDate = new SimpleDateFormat("dd/MM/yyyy").parse(Optional.ofNullable(request.getParameter("pPublisherDate")).get());
        } catch (Exception e) {
            e.printStackTrace();
            publisherDate = new Date();
        }
        String language = Optional.ofNullable(request.getParameter("pLanguage")).get();
        int numberOfPages = Integer.valueOf(Optional.ofNullable(request.getParameter("pNumberOfPages")).get());
        BookFormat format = BookFormat.valueOf(Optional.ofNullable(request.getParameter("pFormat")).get());
        double price = Double.parseDouble(Optional.ofNullable(request.getParameter("pPrice")).get());
        Availability availability = Availability.valueOf(Optional.ofNullable(request.getParameter("pAvailability")).get());
        int stockQuantity = Integer.valueOf(Optional.ofNullable(request.getParameter("pStockQuantity")).get());

        Book book = new Book();

        book.setTitle(title);
        book.setDescription(description);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublishedDate(publisherDate);
        book.setLanguage(language);
        book.setNumberOfPages(numberOfPages);
        book.setFormat(format);
        book.setPrice(price);
        book.setAvailability(availability);
        book.setStockQuantity(stockQuantity);

        return book;
    }


    @Override
    public Response processRequestLogout(HttpServletRequest request, HttpServletResponse response) {
        Response responseDomain = null;

        HttpSession session = request.getSession();
        session.invalidate();

        responseDomain = new Response(request, response, "catalog", true);

        return responseDomain;
    }

    @Override
    public Response processRequestBook(HttpServletRequest request, HttpServletResponse response) {
        Response reponseDomain = null;
        int pId = Integer.parseInt(Optional.ofNullable(request.getParameter("pId")).get());

        Book book = this.bookstoreDAO.getBookById(pId);
        System.out.println("######BOOK: " + book.toString());

        request.setAttribute("book", book);

        reponseDomain = new Response(request, response, "/pages/jsp/book.jsp");

        return reponseDomain;
    }

    @Override
    public Response processRequestCatalog(HttpServletRequest request, HttpServletResponse response) {
        Response reponseDomain = null;
        //extract requets parameters
        //NA

        //invoke Model
        // List<Book> books = this.bookstoreDAO.getAllBooks();
        List<Book> books = this.bookstoreDAO.getAllBooks();

        //prepare data
        request.setAttribute("books", books);
        reponseDomain = new Response(request, response, "/pages/jsp/catalog.jsp");

        return reponseDomain;
    }

    @Override
    public Response processDefault(HttpServletRequest request, HttpServletResponse response) {
        Response reponseDomain = null;
        reponseDomain = new Response(request, response, "/pages/jsp/notValue.jsp");

        return reponseDomain;

    }

    @Override
    public Response processRedirectLogin(HttpServletRequest request, HttpServletResponse response) {
        Response reponseDomain = null;
        reponseDomain = new Response(request, response, "/pages/jsp/login.jsp");

        return reponseDomain;
    }

   

}
