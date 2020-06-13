/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boosktore.frontend.domain;

import bookstore.backend.datamodel.Book;
import bookstore.backend.datamodel.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hhugohm
 */
public class Response {
    
  private HttpServletRequest request;  
  private HttpServletResponse response;
  private String target;
  private boolean sendRedirect;

    public Response(HttpServletRequest request, HttpServletResponse response,String target) {
        this.request = request;
        this.response = response;
        this.target=target;
    }
     public Response(HttpServletRequest request, HttpServletResponse response,String target,boolean sendRedirect) {
        this.request = request;
        this.response = response;
        this.target=target;
        this.sendRedirect=sendRedirect;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isSendRedirect() {
        return sendRedirect;
    }

    public void setSendRedirect(boolean sendRedirect) {
        this.sendRedirect = sendRedirect;
    }
 
    
}
