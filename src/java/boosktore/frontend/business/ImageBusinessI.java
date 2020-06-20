/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boosktore.frontend.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hhugohm
 */
public interface ImageBusinessI {
    
    void processRequestReadImage(HttpServletRequest request, HttpServletResponse response);
    void processRequestUploadImage(HttpServletRequest request, HttpServletResponse response);
    void processRequestPreviewImage(HttpServletRequest request, HttpServletResponse response);
    
}
