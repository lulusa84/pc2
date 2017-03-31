
	package documentazione;

   

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
        
    

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;

    
    import documentazione.Documenti;
    import documentazione.Login;
    
    
	/**
	 *
	 * @author FP80
	 */
	public class LoginController2 {
	    HttpServletRequest request;
		HttpServletResponse response;
		VisteAutori visteAutori = null;
		Documenti documenti;
		Autori autori;
		Login login;
                Login login1;
		VisteLogin vistelogin;
		Connection connessione1 = null;
		HttpSession sessione;
	        VisteDocumenti visteDocumenti = null;

		
		public LoginController2(
				HttpServletRequest request, 
				HttpServletResponse response,
				Autori autori,
				Connection connessione,
				String percorso,
				HttpSession sessione,
				VisteDocumenti vistedocumenti
				)
		
				{
					this.response = response;
					this.request = request;
					
					//Una potenziale ripetzione
					//Comunque passo vista E modello
					//Per le letture uso solo la vista o quasi
					//Per le scritture devo passare dal modello
					
					this.autori = autori;
					this.login=new Login(connessione1,sessione);
                                        this.login1=new Login(connessione1,sessione);
					//this.visteDocumenti = visteDocumenti;
			                this.connessione1 = connessione;
			                //sessione
			                this.sessione =login.sessione;
			                
					this.vistelogin = new VisteLogin(login, percorso);			
				}
 
		
	
		   
	boolean login(String email, String password) {

		String livello = "0";
		String idautore = "0";
		
		boolean esito1 = login.login(email, password, connessione1);
		
                //se il login è avvenuto allora scrivi sulla pagina l'email, l'id ed il livello
		if (esito1 == true) {

			String sql = "SELECT * FROM Autori WHERE email=? AND password=?";
			PreparedStatement stmt = null;

			try {

				stmt = connessione1.prepareStatement(sql);

				stmt.setString(1,email);
						
				stmt.setString(2,password);
						
				stmt.execute();
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {

					livello = rs.getString("livello");
					idautore = rs.getString("id");
				}
				
                           //scrivi sulla sessione il valore di email e password
				
				login.scriviUtenteLoggatoSuSession(email,
						password,						
						connessione1);
						
				
                            //carica la vista
				response.getWriter()
						.append(vistelogin.vistaGenerica(" "+ email.toUpperCase() + " " + "l'id é" +" "+ login.getId() +" "+"il livello é"+ " " + login.getLivello().toString()));
			   //idautore.toUpperCase();
				
			   } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
                        
                           finally {
                        
                        try {
                                                        
                        
                        stmt.close();
                        
                        } catch (Exception ex) {
                        ex.printStackTrace(); 
                    
		           }}}

		        else {
			
                       //blocca l'accesso
			try {
				response.getWriter().append(vistelogin.vistaGenerica("Utente non loggato").toString());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                        

		}
		return esito1;

	}
	     
	}
