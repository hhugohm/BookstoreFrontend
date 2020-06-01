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
import boosktore.frontend.domain.Response;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String user = Optional.ofNullable(request.getParameter("pUsername")).get();
        String password = Optional.ofNullable(request.getParameter("pPassword")).get();

        User userDB = this.userDAO.getUserByKey(user, password);
        System.out.println("######USER: " + userDB.toString());

        request.setAttribute("user", userDB);
        responseDomain = new Response(request, response, "/pages/jsp/user.jsp");

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
