package documentazione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Autori {
	
	// Variabile di istanza.
	//private static Connection connessione = null;
        protected Connection connessione = null;
	HttpServletRequest request;
	HttpServletResponse response;
	Login login;
	VisteLogin vistelogin;
	
        VisteAutori visteAutori = null;
    
	public Autori()
	{

	{
		try {
		      Class.forName("org.sqlite.JDBC");
		      //Class.forName("com.mysql.jdbc.Driver");
		      //connessione = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Shimoda\\Desktop\\AntUltimaversione\\Documenti.db");
		      connessione = DriverManager.getConnection("jdbc:sqlite:/Users/sarahtarantino/Desktop/Documenti.db");
		} 
			catch (ClassNotFoundException e)
			{
		      System.err.println(e.getMessage());
		      System.exit(0);
		      
		    } catch (SQLException e) {
				e.printStackTrace();
			    System.exit(0);
			}
                
	
	}
	}
	
	
	public Autori(Connection connessione){
		
		//	Inietto la connessiaone da fuori.
		this.connessione = connessione;
		
	}
	
	
	public String getNome(String email){
		String nome = "";
		
		//SELECT nome FROM autori WHERE email=email.
		try {
			PreparedStatement stmt = connessione.prepareStatement("SELECT nome FROM Autori WHERE email=?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
				nome = rs.getString("nome");
			} catch (SQLException e) {
			e.printStackTrace();
		        }
                       finally {
                        
                            try {
                                                        
                           connessione.close();
                           
                        
                           } catch (Exception ex) {
                          ex.printStackTrace(); 
 	      
		     
	                 }}
		return nome;
	}

	
		public boolean nuovoAutore(String nome, String email, String password, String livello){	
		boolean esito = true;
		
		
		
		if (nome.equals("") || //cognome.equals("") 
				 email.equals("") || password.equals("")|| livello.equals("")){
			return false;
		}
		String sql = "INSERT INTO Autori(nome, email, password, livello) values(?,?,?,?);";
		PreparedStatement stmt = null;
		try{
			stmt = connessione.prepareStatement(sql);
			stmt.setString(1, nome);
			//stmt.setString(2, cognome);
			stmt.setString(2, email);
			stmt.setString(3, password);
			stmt.setString(4, livello);
			stmt.execute();
			
			//stmt.close();
		} catch (Exception e){
			System.out.println("Hey boss, il mio errore �"+e.getMessage());
			e.printStackTrace();
			esito = false;
		}
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return esito;
	}
	
	public ResultSet listaAutori()
	{
		ResultSet rs = null;
		try
		{
			String sql = "select * from Autori";
			PreparedStatement stmt = connessione.prepareStatement(sql);
			rs = stmt.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		}
                /*finally {
                        
                            try {
                                                        
                           connessione.close();
                           
                        
                           } catch (Exception ex) {
                          ex.printStackTrace(); 
 	      
		     
	                 }
                        }*/
		return rs;
	}
        
        public boolean cancellaAutore(Login login, HttpServletRequest request,
	    	           
	        HttpServletResponse response, Connection connessione, String nome){
	    			
		    boolean esito = false;
		    ResultSet rs = null;
		    
		    //In idoc dovrò mettere l'id del documento da cancellare
			
		    nome=request.getParameter("nome");
			
		
		    // se le variabili email, password, connessione sono null allora esito è uguale a FALSE	  
	          if (nome.equals("") ||  connessione == null){
	          return false;	
		    
	          }else  {  
               		          
	        
		    String sql = "DELETE from Autori where nome=?";
		    PreparedStatement stmt = null;
		    try {     
			     
			stmt = connessione.prepareStatement(sql);
			stmt.setString(1,nome);
			stmt.execute();
			rs = stmt.executeQuery();
			
			
		    } catch (Exception e){
			e.printStackTrace();
			System.out.println(" Il mio errore è"+e.getMessage());
			esito=false;
			
		    }
		finally {
                        
                            try {
                            rs.close();                            
                            stmt.close();
                           
                        
                           } catch (Exception ex) {
                          ex.printStackTrace(); 
 	      
		     
	                 }}
	      }
            return esito;

            }   
        
}
