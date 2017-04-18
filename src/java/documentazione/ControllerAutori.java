package documentazione;


	import java.io.IOException;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import java.sql.*;



        import javax.servlet.http.HttpSession;

	public class ControllerAutori {

		HttpServletRequest request;
		HttpServletResponse response;
		VisteAutori visteAutori = null;
		Documenti documenti;
		Autori autori;
		Login login;
                boolean login1;          
	        Connection connessione1 = null;
                HttpSession sessione;
                 
		public ControllerAutori(
				HttpServletRequest request, 
				HttpServletResponse response,
				VisteAutori visteAutori,
				Autori autori,
                                HttpSession sessione,
                                Connection connessione,                    
			        Login login,
                                Documenti documenti
				)
				{
					this.response = response;
					this.request = request;
                                        this.login=new Login(connessione1,sessione);
                                        this.connessione1 = connessione1;
                                        this.sessione =login.sessione;
					//Una potenziale ripetzione
					//Comunque passo vista E modello
					//Per le letture uso solo la vista o quasi
					//Per le scritture devo passare dal modello
					this.visteAutori = visteAutori;
					this.autori = autori;
                                        this.documenti=documenti;
				}

    ControllerAutori() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

		public void listaautori()
		{
		    boolean esito= login.getEsito();
                    if (esito == true){
                            try {
                                response.getWriter().append(visteAutori.vistalistaautori(" "+ login.getEmail().toUpperCase() + " " + "l'id é" +" "+ login.getId() +" "+"il livello é"+ " " + login.getLivello().toString()));
                                //response.getWriter().append(visteAutori.listaAutori());
                            } catch (Exception ex) {
                               
				ex.printStackTrace();			
			    }
                            } else{
                
                    try {
                    //response.getWriter().append(visteAutori.vistaGenerica("Impossibile accedere, è necessario essere loggati"));
                    response.sendRedirect("Documentazione?comando=formlogin");
                     } catch (Exception ex) {
                    ex.printStackTrace();
                 }
		 }	
		 }
		
		public void formnuovoautore(){
			
			try{
				response.getWriter().append(visteAutori.formnuovoautore());
			} catch (IOException e){
				e.printStackTrace();
			}
			
		}
		
		public void confermaInserimento(){

			String nome = request.getParameter("nome");
			//String cognome = request.getParameter("autore");
                        
			String email = request.getParameter("email");
			String password=request.getParameter("password");
			String livello=request.getParameter("livello");
		  
			try{
			//response.getWriter().append(nome);
			//response.getWriter().append(email);
			//response.getWriter().append(password);
			//response.getWriter().append(livello);
			
			if (!autori.nuovoAutore(nome,email,password,livello))
					response.getWriter().append(visteAutori.vistaGenerica("Autore non inserito"));
				else
					response.getWriter().append(visteAutori.vistaGenerica("Autore inserito con successo"));
			} catch(IOException e){
					e.printStackTrace();
			}				
				
		    }		
		
                
             void  cancellaAutore(Connection connessione, HttpServletRequest request,
	     HttpServletResponse response,Login login) {
             HttpSession sessione=login.sessione;
             
             
              //In id dovrò mettere l'id dell'autore da cancellare
      
             // Leggo dalla session il livello e l'id del soggetto loggato
             
             String idutente =login.getId();
             //String nome=request.getParameter("nome");
             String idautore=request.getParameter("id");
             String nome=request.getParameter("nome");
             String livello  =login.getLivello();
             //String id = "";
    
            ResultSet rs = null;
             //ResultSet rs2 = null;      
             boolean esito= login.getEsito();
             
                         if (esito == true){
                        
                                          
                               //String sql = "select * from Autori where nome=?";
                               //PreparedStatement stmt = null;
                               
                                try{
                                   
                                   //  stmt = connessione.prepareStatement(sql);
                                   // stmt.setString(1,nome);
                                   // stmt.execute();
                                   // rs = stmt.executeQuery();
                                   
                                   
                                   
                                   //if (rs.next()){
                                    //idautore=rs.getString("id");
                                    
                                   //}
                                 //              
                                   
                     switch(livello)  {
                                          
                                  case "1":
                                        rs=documenti.listaDocumentiAutore(request,response,nome);
                                        if(rs.next()){     			      
      		                         
                                         response.getWriter().append(visteAutori.vistaGenerica
                                         ("Impossibile cancellare l'Autore perche sono presenti suoi documenti in DB"));
                                        }
                                         else {
                                         response.getWriter().append(visteAutori.cancellaautori(request, response).toString());
                                         //autori.cancellaAutore(login, request, response, connessione, id);
                                         autori.cancellaAutore(login, request, response, connessione, idautore);
                                                   
                                               }
                                    
                                           break;
                                   case "2":
                                               //if(idutente.equals(idutente)) {
                                                   
                                           response.getWriter().append(visteAutori.vistaGenerica("Impossibile cancellare gli Autori"));
                                               //}else{
                                                   
                                                   //response.getWriter().append(visteAutori.vistaGenerica("Impossibile cancellare gli Autori"));
                                                   
                                               //}
                                               break;
                                               
                                   case "3":
                                               //if(idutente.equals(idutente)) {
                                                   
                                            response.getWriter().append(visteAutori.vistaGenerica("Impossibile cancellare gli Autori"));
                                              // }else{
                                                   
                                                   //response.getWriter().append(visteAutori.vistaGenerica("Impossibile cancellare gli Autori"));
                                                   
                                               //}
                                               break;
                                               
                                       }}
                                       catch (Exception ex) {                                   
                                         ex.printStackTrace();
                                     }                                 
                               
                               
                               finally {
                                   
                                  try {
                                       
                                        rs.close();
                                        //stmt.close();
                                       
                                      } catch (Exception ex) {
                                      ex.printStackTrace();
                          
                              }
                              }
                               }
                            
                           else {
                                
                              try {
                                    response.getWriter().append(visteAutori.vistaGenerica("Impossibile cancellare l'Autore"));
                                    
                                    
                             } catch(Exception e){
                               e.printStackTrace();
                               System.out.println(e.getMessage());
                                
                                
                                   
                             } 

                            
                                
                                
                                   
                    }
                                
   
         }}
         
        
	

	
	
	

