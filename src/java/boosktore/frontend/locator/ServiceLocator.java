package boosktore.frontend.locator;

import bookstore.backend.api.BookstoreDAO;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author hhugohm
 */
public class ServiceLocator {
    
    
     public  static <T> T getEJB(String name){
        T ejb = null;
        
        try{
            Context ctx = new InitialContext();
            //ejb= (T)ctx.lookup("java:global/BookstoreBackend/"+name);
             ejb= (T)ctx.lookup("java:global/BookstoreApp/BookstoreBackend/"+name);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return ejb;
    } 
    
}
