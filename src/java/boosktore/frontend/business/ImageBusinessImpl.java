/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boosktore.frontend.business;

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
public class ImageBusinessImpl  implements ImageBusinessI{

    @Override
    public void processRequestReadImage(HttpServletRequest request, HttpServletResponse response) {
       
    }

    @Override
    public void processRequestUploadImage(HttpServletRequest request, HttpServletResponse response) {
       
    }

    @Override
    public void processRequestPreviewImage(HttpServletRequest request, HttpServletResponse response) {
        
    }
    
}
