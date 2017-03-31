package documentazione;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;



public class VisteAutori {

	//public CharSequence formnuovoAutore() {
		// TODO Auto-generated method stub
		//return null;
	//}
	Autori autori;
	String percorso="";
	Vista  masterpage = null;
        
        ControllerAutori controllerautori;
	HttpServletRequest request;
	HttpServletResponse response;
	Connection connessione = null;
	Login login;
	HttpSession sessione;
	
	public VisteAutori(Autori autori, String percorso)
	{
	//Gli sto passando il database...
		this.autori = autori;
		this.percorso = percorso;
	        this.masterpage = new Vista(percorso+"/index.html");
                
		//this.masterpage = new Vista(percorso+"\\index.html");
                
                this.request=request;
	        this.response=response;
                this.login=new Login(connessione,sessione);
	        this.connessione = connessione;
	        this.sessione =login.sessione;
	}
	
        public String vistalistaautori(String messaggio) 
        { 
            
            return masterpage
                                 .sostituisci("loggatocome", messaggio)
                                 .sostituisci("contenuto", listaAutori())
		                 .sostituisci("contenuto2", "").toString(); 
        }  
        
	public String listaAutori()
	{
		//In questo caso il modello sta gi� formattando il dato...
		//Sarebbe SBAGLIATO...
		//E poi lo correggeremo
		//E' la vista che deve formattare il dato
		
		//In questo caso delego alla vista l'accesso al model... non � considerato sempre corretto.
		//Prendo i dati di listadocumenti
		
		ResultSet rs = autori.listaAutori();
		String risultato1 = "";
                String risultato2 = "";
		Vista v= null;
                
                Vista v2= null;
                
                v = new Vista(percorso+"/RES/rigaautore.html");
                v2 = new Vista(percorso+"/RES/autori.html");
                
		try
		{
                   
                        while(rs.next())
                        {
                           if(rs.isFirst()) {
                            
                            v2= v2.
                            sostituisci("nome",rs.getString("nome")).
                            sostituisci("email",rs.getString("email")).
                            sostituisci("id", rs.getString("id"));
                            
                            
                            risultato1 = risultato1 +v2.toString();
                            v= null;
                            
                            }
                            
                            int row=rs.getRow();
                            if(row>1) {
                            
                            v = new Vista(percorso+"/RES/rigaautore.html");
                            
                            v = v.
                                    sostituisci("nome", rs.getString("nome")).
                                    sostituisci("email", rs.getString("email")).
                                    sostituisci("id", rs.getString("id"));
                            
                              risultato2 = risultato2 + v.toString();
                        }
                    
		  }
                 }        
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
	
		
		
		return masterpage
                                    .sostituisci("contenuto",risultato1 + risultato2).toString();
	}
	
        
	public String formnuovoautore(){
		return masterpage
				.sostituisci("contenuto", 
					new Vista(percorso + "/RES/formnuovoautore.html").toString())
				   // new Vista(percorso + "\\RES\\formnuovoautore.html").toString())
				    .toString();
	}

	public Vista cancellaautori(HttpServletRequest request,HttpServletResponse response)
	{
		//In questo caso il modello sta gi� formattando il dato...
		//Sarebbe SBAGLIATO...
		//E poi lo correggeremo
		//E' la vista che deve formattare il dato
		
		//In questo caso delego alla vista l'accesso al model... non � considerato sempre corretto.
		//Prendo i dati di listadocumenti
		
		
		ResultSet rs = 
				autori.listaAutori();
		
		Vista v= null;
		try
		{		 
			v =  masterpage
			.sostituisci("contenuto", "L'autore avente nome"+" "+ request.getParameter("nome")+" "+ "è stato cancellato");
				//login.getId()	
				//percorso
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
		return v;
		
		}
        
	public String vistaGenerica(String messaggio){

		return masterpage.sostituisci("contenuto", messaggio)
                                 .sostituisci("contenuto2", "").toString();
		
	}
}



	

