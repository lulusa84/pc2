package documentazione;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login {
	
	      
  Connection connessione1 = null;
  HttpServletRequest request;
  HttpServletResponse response;
  HttpSession sessione;	
  
  public Login(Connection connessione, HttpSession sessione){
		this.connessione1 = connessione;
		this.sessione = sessione;
		this.response = response;
		this.request = request;
      
	}
	
	
		//HttpSession sessione;
					
	    //Livello dell'utente loggato
		
	    public String getLivello()
	    {   Object livello="4";
	    	 
	          if(sessione != null){
                      livello = sessione.getAttribute("livello").toString();
	      	      return livello.toString();
	         } else {
			  return "4";
	         
	    }
	   }
	      
	      
          public String getId(){
          Object id="0";
          if(sessione != null){
              id = sessione.getAttribute("idutente").toString();
      	      return id.toString();
              }else {
		       return "0";	      	 	
      	      
	      }
            }
	    
       public String getEmail(){
    	   Object email = sessione.getAttribute("email");
    	   if(email != null) 
    		   return email.toString();
    	   else 
    		   return "";
      	    	 	
	      }  
       
       
       public String getPassword(){
    	   Object pass = sessione.getAttribute("password");
    	   if(pass != null) 
    		   return pass.toString();
    	   else 
    		   return "";
      	 
	      }  
       
       
	public boolean getEsito(){
            
           Object email = sessione.getAttribute("email");
    	   if(email != null) 
    		   return true;
    	   else {
    		   
               
           return false;
            }
            
}    
	    
	    public void scriviUtenteLoggatoSuSession(String email, String password, Connection connessione){
	    	
	    	Connection connessione1 = connessione;
	    	HttpServletRequest request=this.request;
	    	  String livello="";
	    	  String idutente="";
	    	//int livello=0;
	        //int idutente=0;
	    	ResultSet rs = null;
	    	
	    	//sessione.setAttribute("email",email);
	        //sessione.setAttribute("password",password);
	        
	        String sql="SELECT * FROM Autori WHERE email=? AND password=?";		
	        PreparedStatement stmt = null;
	        
			try {
				
				stmt = connessione1.prepareStatement(sql);
				stmt.setString(1, email);
				stmt.setString(2, password);
				stmt.execute();
				rs = stmt.executeQuery();
				
				//ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					email = rs.getString("email");
					password = rs.getString("password");
					livello = rs.getString("livello");
					idutente = rs.getString("id");
					
				             } 
			     } catch (Exception e) {
			       e.printStackTrace();
				   System.out.println(e.getMessage());
			                                            }
                        
                   finally {
                        
                        try {
                                                        
                        rs.close();
                        stmt.close();
                        
                        } catch (Exception ex) {
                        ex.printStackTrace(); 
                }     
			
		sessione.setAttribute("email",email);
	        sessione.setAttribute("password",password);
	        sessione.setAttribute("livello",livello);
	        sessione.setAttribute("idutente",idutente);
	        
			//return;
				 
	    }}
	    
	    //Va esteso... devo memorizzare email e affini...
	    //Per ora ricordo solo che sono loggato
	        
	     
	      //oggetto login
	      public boolean login(String email, String password, Connection connessione1){          
	    	         
		    	    
	    	          ResultSet rs = null;
	                  // se le variabili email, password, connessione sono null allora esito è uguale a FALSE	  
	  		          if (email.equals("") || password.equals("") || connessione1 == null){
                                   
	  		            return false;	
	  		          }
	     
	  		             String sql ="SELECT email, password, livello FROM Autori WHERE email=? AND password=?";
	  		             PreparedStatement stmt = null;
	    				//SELECT email FROM author WHERE email=email AND password=password.
	    				try  {
	    						
	    					stmt = connessione1.prepareStatement(sql);
	    						
	    					stmt.setString(1, email);
	    					stmt.setString(2, password);
	    					stmt.execute();	 
	    					rs = stmt.executeQuery();

	    					if (rs.next()) {
		    			              				  
		    				    return true; 
                                                          
	    					} else  {
                                                   
	    					    return false;
	    				
	    				       } } catch (Exception e) {
	    					   e.printStackTrace();
	    					   System.out.println(e.getMessage());
	    					   
	    					   return true; 
	    				}
                                         finally {
                        
                                      try {
                                                        
                                     rs.close();
                                     stmt.close();
                        
                                     } catch (Exception ex) {
                                     ex.printStackTrace(); 
                 }     
	    					
                } 
                                        
              } 
}
               






		
	 
			
		



