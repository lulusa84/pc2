/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentazione;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
/**
 *
 * @author sarahtarantino
 */
public class LogoutController {

    
	        HttpServletRequest request;
		HttpServletResponse response;
	
		Login login;
                logout logout;
		VisteLogin vistelogin;
		Connection connessione1 = null;
		HttpSession sessione;
	        

		
		public LogoutController(
				HttpServletRequest request, 
				HttpServletResponse response,
				String percorso,
				Connection connessione
				//HttpSession sessione
				//Login login,
                                //logout logout
				)
		
				{
					this.response = response;
					this.request = request;
					
					this.login=new Login(connessione1,sessione);
                                        this.logout = new logout(connessione1,sessione);
                                        
                                        this.connessione1 = connessione;
					//sessione
					//this.sessione =login.sessione;
			                
			                this.vistelogin = new VisteLogin(login, percorso);			
				}
 
            
                
            public void logout(){ 
              sessione = request.getSession(false);
              sessione.getMaxInactiveInterval();
              
            //sessione=login.sessione;
              if(sessione!=null){ 
              logout.logout(request,response,sessione); 
                try {
                    response.getWriter().append(vistelogin.vistaGenerica("Logout effettuato").toString());
                    sessione.getMaxInactiveInterval();
                    //response.sendRedirect("Documentazione?comando=formlogin");
                } catch (Exception e) {
                 e.printStackTrace();
                }
				
                           
  }  
  }         
  }        
         

