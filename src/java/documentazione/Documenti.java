package documentazione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Documenti {

	Connection connessione;
	HttpServletRequest request;
	HttpServletResponse response;
	Login login;
	VisteLogin vistelogin;
	HttpSession sessione;
	VisteDocumenti visteDocumenti = null;
	
	public Documenti()
	{
		try {
		     
		      Class.forName("org.sqlite.JDBC");
		      		//+ ("com.mysql.jdbc.Driver");
		     
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
        
	
	public Documenti(Connection connessione){
		this.connessione = connessione;
                //this.sessione = login.sessione;
	}
        
	
	public boolean nuovoDocumento(String titolo, String categoria, String contenuto, String idautore){
		boolean esito = true;
                
		if (titolo == null || categoria == null || contenuto == null || idautore == null 
				|| titolo.equals("") || categoria.equals("") || contenuto.equals("")|| idautore.equals(""))
                    //idautore.equals("")
                {
			return false;
		}
		
		String sql = "INSERT INTO Documenti(titolo, contenuto, categoria, idautore) values(?,?,?,?);";
                //String sql = "INSERT INTO Documenti(titolo, contenuto, categoria,idautore) values(?,?,?);";
		PreparedStatement stmt = null;
		try{
			stmt = connessione.prepareStatement(sql);
			stmt.setString(1, titolo);
			stmt.setString(2, contenuto);
			stmt.setString(3, categoria);
                        stmt.setString(4, idautore); 
			stmt.execute();
                        
			
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Hey boss, il mio errore é"+e.getMessage());
			esito = false; 
                        
                        
                }
                   finally {
                        
                       try {
                        connessione.close();
                        stmt.close();
                        
                        } catch (Exception ex) {
                        ex.printStackTrace(); 
                }     
		
        }
            return esito;
	}
        
	
        
	public boolean cancellaDocumento(Login login, HttpServletRequest request,
	    	           
	        HttpServletResponse response, Connection connessione, String id){
	            
                    
		    boolean esito = false;
		    ResultSet rs = null;
		    
		    //In idoc dovrò mettere l'id del documento da cancellare
			
		    id= request.getParameter("id");
			
		
		    // se le variabili email, password, connessione sono null allora esito è uguale a FALSE	  
	            if (id=="" ||  connessione == null){
	            return false;	
		    
	            }else{  
               		          
	        
		        String sql = "DELETE from Documenti where id=?";
		        PreparedStatement stmt = null;
		        try {     
			
			stmt = connessione.prepareStatement(sql);
			stmt.setString(1,id);
			stmt.execute();
			rs = stmt.executeQuery();
			
			
		       } catch (Exception e){
                            try {
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

	               }}  return esito;
                     }
	
	    	
	public ResultSet listaDocumenti()
	{
		ResultSet rs = null;
                
                
		try
		{
	               
			String sql = "select * from Documenti";
			PreparedStatement stmt = connessione.prepareStatement(sql);
                       
			rs = stmt.executeQuery();
                              
		}
                
		catch (SQLException e)
		{
			e.printStackTrace();	
			System.out.println(e.getMessage());
		}
		return rs;
	}

        public ResultSet listaDocumentiAutore()
	{       
                String nome=request.getParameter("nome");
             
		ResultSet rs = null;
                
                
                nome=request.getParameter(nome);
                   
			String sql = "select Documenti.id from Documenti, Autori where Autori.nome=?";
			PreparedStatement stmt =null;
                        try {     
			
		        stmt = connessione.prepareStatement(sql);                      
                        stmt.setString(1,nome);
                        stmt.execute();
			rs = stmt.executeQuery();
			
		        }
                        
                        catch (Exception e){
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
                           return rs;  
                         } 
                        
                         
                       } 
        

        
        public ResultSet vediDocumenti(HttpServletRequest request,
	    	           
	        HttpServletResponse response,String id){ 
            
	        // documento da visualizzare
		id=request.getParameter("id");
                    
		ResultSet rs = null;
                  
                    
                String sql = "select titolo, contenuto from Documenti where id=?";
                PreparedStatement stmt = null;
                    
                    
		        try {     
			
		        stmt = connessione.prepareStatement(sql);
                        stmt.setString(1,id);
                        stmt.execute();
			rs = stmt.executeQuery();
			
		        } 
                        
                        catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());

		        }
                        
                        
                        //} 
                        /* finally {
                     
                          try {
                                                    
                           //rs.close();
                           stmt.close();
                        
                           } catch (Exception ex) {
                            ex.printStackTrace();
                           } */
                       return rs;      
                        
                      }
        
        public ResultSet CercaDocumenti(HttpServletRequest request,
	    	           
	        HttpServletResponse response,String chiave, ResultSet listaDocumenti){ 
            
	         // documento da visualizzare
		 chiave=request.getParameter("chiave");
                 //try {
                 //titolo=listaDocumenti.getString("titolo");
                 //contenuto=listaDocumenti.getString("contenuto");
                 //} catch (Exception ex) {
                 //ex.printStackTrace();
	          //System.out.println(ex.getMessage());
                 //}
		    ResultSet rs = null;
                  
                String sql=null; 
                sql = "select * from Documenti where titolo LIKE ? OR contenuto LIKE ?";
                 
                 PreparedStatement stmt = null;
                    
               
		        try {     
			
		        stmt = connessione.prepareStatement(sql);
                        stmt.setString(1,"%" + chiave + "%");
                        stmt.setString(2,"%" + chiave + "%");
                        stmt.execute();
			rs = stmt.executeQuery();
			
		        }
                        
                        catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
                        }
                        /*finally {
                        
                            try {
                                                        
                           rs.close();
                           stmt.close();
                        
                           } catch (Exception ex) {
                          ex.printStackTrace();
                        
                        }
                        
                        }*/
		        
                       return rs;
     	      
	         }
                }
                 
            
	         
	               	 	
      	      
	      
         
 
		  
        
	    	
	
        





