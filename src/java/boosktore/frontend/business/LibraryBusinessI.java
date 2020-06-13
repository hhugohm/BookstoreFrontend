/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boosktore.frontend.business;

import boosktore.frontend.domain.Response;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hhugohm
 */
public interface LibraryBusinessI {
    
     
      Response processRequestLogin(HttpServletRequest request, HttpServletResponse response);
      
      Response processRequestStartSession(HttpServletRequest request, HttpServletResponse response);
      
      Response processRequestBook(HttpServletRequest request, HttpServletResponse response);
      
      Response processRequestCatalog(HttpServletRequest request, HttpServletResponse response);
      
      Response processDefault(HttpServletRequest request, HttpServletResponse response);
      
      Response processRedirectLogin(HttpServletRequest request, HttpServletResponse response);
      
      Response processRequestLogout(HttpServletRequest request, HttpServletResponse response);
      
      Response processRequesManage(HttpServletRequest request, HttpServletResponse response);
       
      Response processRequesManageAction(HttpServletRequest request, HttpServletResponse response);
           
     
}
