package documentazione;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import javax.servlet.http.HttpSession;


public class ControllerDocumenti {

	HttpServletRequest request;
	HttpServletResponse response;
	VisteDocumenti visteDocumenti = null;
	Documenti documenti;
	Autori autori;
        Login login;
        
	Connection connessione1 = null;
        HttpSession sessione;
	
	
        
	public ControllerDocumenti(
			HttpServletRequest request, 
			HttpServletResponse response,
			VisteDocumenti visteDocumenti,
			Documenti documenti,
			HttpSession sessione,
			Autori autori,
			Connection connessione,                    
			Login login
                       
			)
			{
		                this.request = request;
				this.response = response;		
				this.autori = autori;
				this.login=new Login(connessione1,sessione);
                                
                                this.connessione1 = connessione;
                                this.sessione =login.sessione;
				this.visteDocumenti = visteDocumenti;		               				
				this.documenti = documenti;
				
			}
	//Una potenziale ripetizione
        //Comunque passo vista E modello
	//Per le letture uso solo la vista o quasi
	//Per le scritture devo passare dal modello

   
	public void listadocumenti() 
	{  
            boolean esito= login.getEsito();
                if (esito == true){
            
		   try
		        {
			
                        //response.getWriter().append(visteDocumenti.listadocumenti());
                        
                        response.getWriter().append(visteDocumenti.vistalistadocumenti(" "+ login.getEmail().toUpperCase() + " " + "l'id é" +" "+ login.getId() +" "+"il livello é"+ " " + login.getLivello().toString()));
                        }
		catch(Exception e)
		{
			e.printStackTrace();			
		}
                   
                } else{
                
                    try {
                    //response.getWriter().append(visteDocumenti.vistaGenerica("Impossibile accedere, è necessario essere loggati"));
                    response.sendRedirect("Documentazione?comando=formlogin");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                }
	      }
	
	
	public void formnuovodocumento(){
		
		try{
			response.getWriter().append(visteDocumenti.formnuovodocumento());
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	public void confermaInserimento(){
                String email=login.getEmail();
                String password=login.getPassword();
                boolean esito= login.getEsito();
                if (esito == true){
                    //if (login.login(email, password, connessione1)){
		String titolo = request.getParameter("titolo");
		String categoria = request.getParameter("categoria");
		String contenuto = request.getParameter("contenuto");
                String idautore = login.getId().toString();
		
		try{
			if (!documenti.nuovoDocumento(titolo, categoria, contenuto,idautore))
				response.getWriter().append(visteDocumenti.vistaGenerica("Documento non inserito"));
                        else {
				response.getWriter().append(visteDocumenti.vistaGenerica("Documento inserito con successo"));
		        }}
                catch(Exception e){
		e.printStackTrace();
	        }}
                else {	
                    try{
                        response.getWriter().append(visteDocumenti.vistaGenerica("Impossibile inserire il Documento senza essersi loggati")); 
                        }catch(Exception e){
		e.printStackTrace();
	        }}	

	 }

	
	public void  cancellaDocumento(Connection connessione, HttpServletRequest request,
	       HttpServletResponse response,Login login)  {
		HttpSession sessione=login.sessione;
              
            // Leggo dalla session il livello e l'id del soggetto loggato
             
             String idautoredoc="";
             String titolo="";
             String email="";
             String password="";
             
             //In id dovrò mettere l'id del documento da cancellare
             String id=request.getParameter("id");
             
             ResultSet rs = null;
             
             boolean esito= login.getEsito();
                if (esito == true){
             //if (login.login(email, password, connessione1)){
             String idutente =login.getId();
             String livello  =login.getLivello();    
            
                
             String sql = "select * from Documenti where id=?";
             PreparedStatement stmt = null;
             
             try{
        	 
        	 stmt = connessione.prepareStatement(sql);			 
    	         stmt.setString(1,id);
			 stmt.execute();
			 rs = stmt.executeQuery();
          
			 
			 
      		        if (rs.next()){
      			      
      		           id =rs.getString("id");
      		           
       	    	           titolo = rs.getString("titolo");	
       	    	           idautoredoc = rs.getString("idautore");
       	    	    
       	    	            switch(livello)  {
                                
                                    
                                case "1":
                                response.getWriter().append(visteDocumenti.cancelladocumenti(request, response).toString());
               	                documenti.cancellaDocumento(login, request, response, connessione, id);
                                break;
                                   
       	    	                
                                case "2":
                                if(idutente.equals(idautoredoc)) {
       	    		    
       	    		        response.getWriter().append(visteDocumenti.cancelladocumenti(request, response).toString());
               	                documenti.cancellaDocumento(login, request, response, connessione, id);
                                 
               
        	                }else{
    	    	     
			        response.getWriter().append(visteDocumenti.vistaGenerica("Impossibile cancellare il Documento, se non si è l'autore")); 		                
                                
                                }
                                break;
                                    
                                case "3":
                                response.getWriter().append(visteDocumenti.vistaGenerica("Impossibile cancellare il Documento, se non si è l'autore")); 			    	    	    
    	    	                break;                    
                              
                                } 
                            
                             }
                        }
            catch(Exception e)
            { e.printStackTrace();
            System.out.println(e.getMessage());
           
			      }
            finally {
                        
                try {
                                                        
                   
                    stmt.close();
                        
                        } catch (Exception ex) {
                          ex.printStackTrace(); 
                              
       	    	       	    	
		           }
                             
                    }
             } else {
                    try { 
                        response.getWriter().append(visteDocumenti.vistaGenerica("Impossibile cancellare il Documento senza essersi loggati"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                      System.out.println(ex.getMessage());
                    }
               }
	         
              }
			   
             
        
        
        public void vedidocumento(Connection connessione, HttpServletRequest request,
	       HttpServletResponse response)  {
                
                String id;
                
                id=request.getParameter("id");   
                
                String formato;
                formato=request.getParameter("formato");
            
                ResultSet rs=documenti.vediDocumenti(request,response,id); 
                
                switch(formato)  {
                case "HTML":
            
		try{
                        
                       documenti.vediDocumenti(request,response,id);
                       
                       response.getWriter().append(visteDocumenti.vistahtml(request,response));
                       try {
                                                    
                           rs.close();
                           
                        
                           } catch (Exception ex) {
                            ex.printStackTrace();
                           } 
                break; 
                
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
                
                
                case "TXT": 
                   
                try {        
                       
                        documenti.vediDocumenti(request,response,id);  
                        
			response.getWriter().append(visteDocumenti.vistatxt(request,response));
                break;        
               } catch (Exception e){
	         e.printStackTrace();
	       }
               
               
               case "XML":
                  
               try {       
                       
                        documenti.vediDocumenti(request,response,id);
                        
			response.getWriter().append(visteDocumenti.vistaxml(request,response));      
                break;
               } catch (Exception e){
		 e.printStackTrace();
		
	       }
          }
         }  
        
        public void cercadocumento(Connection connessione, HttpServletRequest request,
	       HttpServletResponse response){
            
	    String chiave;
            chiave = request.getParameter("chiave");      
            ResultSet rs = null;
            ResultSet listaDocumenti=documenti.listaDocumenti();
            /*
            String titolo = null;
            String contenuto = null;
            try {
                titolo = listaDocumenti.getString("titolo");
                //contenuto = listaDocumenti.getString("contenuto");
            } catch (Exception ex) {
                ex.printStackTrace();
			System.out.println(ex.getMessage());
            }
            
            */
            
            boolean esito= login.getEsito();
             if (esito == true){
             if (!chiave.equals("")) {
               
                 rs=documenti.CercaDocumenti(request,response,chiave,listaDocumenti);       
                           try {
                           if (rs.next()){
            
                            
                            //response.getWriter().append(visteDocumenti.vistaGenerica("Contenuto trovato"));
                            response.getWriter().append(visteDocumenti.cercadocumenti(request,response).toString());
                            //response.getWriter().append(visteDocumenti.vistalistadocumenti(" "+ login.getEmail().toUpperCase() + " " + "l'id é" +" "+ login.getId() +" "+"il livello é"+ " " + login.getLivello().toString()));
                                          
                            
       
                             }
                            else {
                       
                            try {
                           String titolo = listaDocumenti.getString("titolo");
                             //contenuto = listaDocumenti.getString("contenuto");
                            } catch (Exception ex) {
                             ex.printStackTrace();
			    System.out.println(ex.getMessage());
            }
                            
                            response.getWriter().append(visteDocumenti.vistaGenerica("Contenuto non trovato"));
                            } 
                                           }
                             
                            catch (Exception ex) {
                            ex.printStackTrace();
                                                }
                   
                       } else {
    
                        
                            try {
                            response.getWriter().append(visteDocumenti.vistaGenerica("inserire chiave"));
                            } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                
                   
        }
         }

}}
 


            
        
             
        
        
        

 


	


