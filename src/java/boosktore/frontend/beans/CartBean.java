package boosktore.frontend.beans;

import bookstore.backend.api.BookstoreDAO;
import bookstore.backend.api.ShoppingCartService;
import bookstore.backend.datamodel.Book;
import boosktore.frontend.locator.ServiceLocator;
import java.util.List;

/**
 *
 * @author hhugohm
 */
public class CartBean {

    //service locator and business delegate
    private BookstoreDAO bookstoreDAO;
    private ShoppingCartService shoppingCartService;

    public CartBean() {
        this.bookstoreDAO = ServiceLocator.<BookstoreDAO>getEJB("BookstoreDAO");
        this.shoppingCartService = ServiceLocator.<ShoppingCartService>getEJB("ShoppingCartIService");
    }
    
    public void add(int id){
        Book book = this.bookstoreDAO.getBookById(id);
        this.shoppingCartService.add(book);
    }
    
    public void remove(int id){
         Book book = this.bookstoreDAO.getBookById(id);
        this.shoppingCartService.remove(book);
    }
    
    public  List<Book> getItems(){
        List<Book> books = this.shoppingCartService.getItems();
        
        return books;
    }

}
