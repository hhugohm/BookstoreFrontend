/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boosktore.frontend.domain;

/**
 *
 * @author hhugohm
 */
public class Request {
    
    
    private UserDomain user;
    
    private BookDomain book;

    public Request() {
    }
    
    public Request(UserDomain user){
        this.user=user;
    }
    public Request(BookDomain book){
        this.book=book;
    }
    
    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }

    public BookDomain getBook() {
        return book;
    }

    public void setBook(BookDomain book) {
        this.book = book;
    }
 
}
