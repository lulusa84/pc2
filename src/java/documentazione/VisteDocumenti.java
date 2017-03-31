package documentazione;

import java.lang.String;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;




public class VisteDocumenti {
	
	Documenti documenti;
	String percorso="";
	Vista masterpage = null;
	ControllerDocumenti controllerdocumenti;
	HttpServletRequest request;
	HttpServletResponse response;
	Connection connessione = null;
	Login login;
	HttpSession sessione;
        
        
	public VisteDocumenti(Documenti documenti, String percorso)
	{
	   //Gli sto passando il database...
           this.documenti = documenti;
           this.percorso = percorso;
           
	   this.masterpage = new Vista(percorso + "/index.html");
           
	   this.request=request;
	   this.response=response;
           this.login=new Login(connessione,sessione);
	   this.connessione = connessione;
	   this.sessione =login.sessione;
	}
        
	public String vistalistadocumenti(String messaggio) 
        { 
            
            return masterpage
                                 .sostituisci("loggatocome", messaggio)
                                 .sostituisci("contenuto", listadocumenti())
		                 .sostituisci("contenuto2", "").toString(); 
        }  
        
	public String listadocumenti() 
	{
		//In questo caso il modello sta già formattando il dato...
		//Sarebbe SBAGLIATO...
		//E poi lo correggeremo
		//E' la vista che deve formattare il dato
		
		//In questo caso delego alla vista l'accesso al model... non
                //è considerato sempre corretto.
		//Prendo i dati di listadocumenti
	
		ResultSet rs = documenti.listaDocumenti();
		String risultato1="";
                String risultato2="";
                
	        Vista v= null;
                
                Vista v2= null;
                
                 v = new Vista(percorso+"/RES/rigadocumento.html");
                v2 = new Vista(percorso+"/RES/formlistadocumenti.html");
                
		try
		{
                    
		while(rs.next()) {
                
                 if(rs.isFirst()) {
                     
                         v2= v2.     
			    sostituisci("titolo",rs.getString("titolo")).
		            sostituisci("idautore",rs.getString("idautore")).					        
			    sostituisci("categoria", rs.getString("Categoria")).
			    sostituisci("id",rs.getString("id"));
                                     
                            risultato1 = risultato1 +v2.toString(); 
                            v= null;   
                              
		      }
                        
                        int row=rs.getRow();
                        if(row>1) {
                   
                        
                        v = new Vista(percorso+"/RES/rigadocumento.html");
                        
                        
                        v= v.
                          
                              
			    sostituisci("titolo",rs.getString("titolo")).
		            sostituisci("idautore",rs.getString("idautore")).					        
			    sostituisci("categoria", rs.getString("Categoria")).
			    sostituisci("id",rs.getString("id"));
                                
			    
                            
		            
                            risultato2 =  risultato2 + v.toString();
                            
                    }
                   
                 } 
                
		} 
		catch (Exception e)
		
		{
			e.printStackTrace();		
		}
	
            	
                
            
            return masterpage
                            .sostituisci("contenuto", risultato1 + risultato2)
                            .toString();
                              
	    } 
	
        
	public Vista cancelladocumenti(HttpServletRequest request,
        HttpServletResponse response)
	{
		//In questo caso il modello sta gi� formattando il dato...
		//Sarebbe SBAGLIATO...
		//E poi lo correggeremo
		//E' la vista che deve formattare il dato
		
		//In questo caso delego alla vista l'accesso al model... non é 
                //considerato sempre corretto.
		//Prendo i dati di listadocumenti
		
		
		ResultSet rs = documenti.listaDocumenti();
		
		Vista v= null;
		try
		{		 
		  v =  masterpage
		    .sostituisci("contenuto", "Il documento n."+" "+ Integer.parseInt(request.getParameter("id"))+""+
                    "dal titolo"+""+ rs.getString("titolo")+" "+ "è stato cancellato");
									
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
		return v;
		
	}
	
	
	public String formnuovodocumento(){
		return masterpage
				.sostituisci("contenuto", 
			             new Vista(percorso + 
                                     "/RES/formnuovodocumento.html").toString())
						//new Vista(percorso+"\\RES\\
                                                //formnuovodocumento.html").
                                                //toString())
				.toString();
	
	}
        
        public String vistahtml(HttpServletRequest request,HttpServletResponse response) {
                
                ResultSet rs = documenti.vediDocumenti(request,response,request.getParameter("id"));
	
                String risultato = "";
		Vista v= null;
                
		try {
			while(rs.next())
			{      
				v = new Vista(percorso+"/RES/vistahtml.html");
				
                               
		
                                v = v.
                                        
                                        sostituisci("titolo",rs.getString("titolo")).
                                        sostituisci("contenuto",rs.getString("contenuto"));
                                
                            
						
						
				risultato = risultato + v.toString();
			}
		}
		
		catch (Exception e)
		
		{
			e.printStackTrace();		
		}
	
		
		return
                        masterpage
			 .sostituisci("contenuto", risultato)
                         .sostituisci("contenuto2", "")      
                                .toString();
	      }
		
	
          public String vistatxt(HttpServletRequest request,HttpServletResponse response) {
                
                ResultSet rs = documenti.vediDocumenti(request,response,request.getParameter("id"));
		
            
		String risultato = "";
                
		Vista v= null;
                
		try {
			while(rs.next())
			{      
				v = new Vista(percorso+"/RES/vistatxt.html");
				
                                
                            
                                v = v.
                                        
                                        sostituisci("titolo",rs.getString("titolo")).
                                        sostituisci("contenuto",rs.getString("contenuto"));
                                
                            
						
						
				risultato = risultato + v.toString();
			}
		}
		
		catch (Exception e)
		
		{
			e.printStackTrace();		
		}
	
		
		return risultato;
                                
	        }
		

        
    public String vistaxml(HttpServletRequest request,HttpServletResponse response) throws SQLException {
                
                ResultSet rs = documenti.vediDocumenti(request,response,request.getParameter("id"));
		
                
		
                
                
                String risultato = "";
		Vista v= null;
                
		try {
			while(rs.next())
			{      
				v = new Vista(percorso+"/RES/vistaxml.html");
				
                                //String id;
                                //id = request.getParameter("id");
                            
                                v = v.
                                        
                                        sostituisci("titolo",rs.getString("titolo")).
                                        sostituisci("contenuto",rs.getString("contenuto"));
                                
                            
						
						
				risultato = risultato + v.toString();
			}
		}
		
		catch (Exception e)
		
		{
			e.printStackTrace();		
		}
	
		
		return risultato;
                        //masterpage
				//.sostituisci("contenuto", risultato)
                               // .toString();
	        
                        
	        }
    
      public String cercadocumenti(HttpServletRequest request, HttpServletResponse response) throws SQLException
	{
                String chiave=request.getParameter("chiave");
		//In questo caso il modello sta gi� formattando il dato...
		//Sarebbe SBAGLIATO...
		//E poi lo correggeremo
		//E' la vista che deve formattare il dato
		
		//In questo caso delego alla vista l'accesso al model... non � considerato sempre corretto.
		//Prendo i dati di listadocumenti
                String titolo = null; 
                String contenuto = null;
                
		ResultSet listaDocumenti=documenti.listaDocumenti();
		ResultSet rs = documenti.CercaDocumenti(request,response,chiave,listaDocumenti);
                   
            
		String risultato1 = "";
                String risultato2="";
		Vista v= null;
                Vista v2= null;
                v = new Vista(percorso+"/RES/rigadocumento.html");
                v2 = new Vista(percorso+"/RES/formlistadocumenti.html");

                /**
                try {
                titolo = listaDocumenti.getString("titolo");
                contenuto = listaDocumenti.getString("contenuto");
                } catch (Exception ex) {
                ex.printStackTrace();
			System.out.println(ex.getMessage());
                
               }
                */
                //Vista v2= null;
		try
		{
			while(rs.next())
			{    
                            if(rs.isFirst()) {
                     
                         v2= v2.     
			    sostituisci("titolo",rs.getString("titolo")).
		            sostituisci("idautore",rs.getString("idautore")).					        
			    sostituisci("categoria", rs.getString("Categoria")).
			    sostituisci("id",rs.getString("id"));
                                     
                            risultato1 = risultato1 +v2.toString(); 
                            v= null;   
                             
		           } 
                             int row=rs.getRow();
                             if(row>1) {

				v = new Vista(percorso+"/RES/rigadocumento.html");
				//v = new Vista(percorso+"\\RES\\rigadocumento.html");
                                
				v = v.
						
                                                sostituisci("titolo",rs.getString("titolo")).
						sostituisci("idautore",rs.getString("idautore")).
						//sostituisci("anteprima", rs.getString("contenuto")).
						sostituisci("categoria", rs.getString("Categoria")).
						sostituisci("id",rs.getString("id"));
						
				risultato2 = risultato2 + v.toString();
			}
		}
                }
		
		catch (Exception e)
		
		{
			e.printStackTrace();		
		}
	
		
		//risultato = "<table border=0>" + risultato + "</table>";
		
		return masterpage
				.sostituisci("contenuto", risultato1 + risultato2)
                                .sostituisci("contenuto2", "")
				.toString();
	}
	    
        public String vistaGenerica(String messaggio){
                
		return masterpage
                                 //.sostituisci("loggatocome", messaggio)
                                 .sostituisci("contenuto", messaggio)
		                 .sostituisci("contenuto2", "").toString();
	}
        }

  

