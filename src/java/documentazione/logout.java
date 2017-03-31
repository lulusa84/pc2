/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentazione;

import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sarahtarantino
 */
public class logout {
    
	
	      
  Connection connessione1 = null;
  HttpServletRequest request;
  HttpServletResponse response;
 
 
  
    /**
     *
     * @param connessione
     * @param sessione
     */
    public logout(Connection connessione, HttpSession sessione){
		this.connessione1 = connessione;		
		this.response = response;
		this.request = request;
                
    }
  
    protected void logout(HttpServletRequest request, HttpServletResponse response,HttpSession sessione){
        sessione = request.getSession(false);
   	
       //sessione.removeAttribute("livello");         
       //sessione.getMaxInactiveInterval();
       sessione.invalidate(); 
    }
   }

