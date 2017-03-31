package documentazione;

import java.io.IOException;

public class VisteLogin {
	String percorso="";
	Vista masterpage = null;
	//String loggatocome="";
	private Login login;
	
    
   public VisteLogin(Login login, String percorso)
	{
	//Gli sto passando il database...
             this.login = login;
	     this.percorso = percorso;
	     this.masterpage = new Vista(percorso+"/index.html");
             masterpage.sostituisci("contenuto2", "").toString();
		//this.masterpage = new Vista(percorso+"\\index.html");
     }
	
	
	public String vistaGenerica(String messaggio){
	String messaggio2="Keep the page ideal for 30 seconds and try reloading the page you will be redirected to home page automatically";
			
		return masterpage
               .sostituisci("loggatocome", messaggio)	
	       .sostituisci("contenuto", "sei loggato come"+ messaggio)
	       .sostituisci("contenuto2", messaggio2).toString();           
		//messaggio
		
        

	}
}


	






