package documentazione;             

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * Servlet implementation class Documentazione
 */
public class Documentazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Documentazione() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//	JDBC.
		try {
			
			Class.forName("org.sqlite.JDBC");
			//("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//	Connessione.
		Connection connessione = null;
		try {
			connessione = DriverManager.getConnection("jdbc:sqlite:/Users/sarahtarantino/Desktop/Documenti.db");
			//connessione = DriverManager.getConnection("jdbc:mysql:/localhost/docant?user=root");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		
		//Creo gli oggetti DAO che faranno parte del model
		//Il model può avere anche oggetti non DAO
		Autori autori = new Autori(connessione);
				
		//Modello per documenti
		Documenti documenti = new Documenti(connessione);
		
		//Preparo la sessione
	        HttpSession sessione = request.getSession();
		
		Login login = new Login(connessione,sessione);
		
		//Istanzio anche un oggetto "vista" per documenti
		VisteDocumenti visteDocumenti =
				new VisteDocumenti
				(documenti,getServletContext().getRealPath("")); 
		
		
		 
		VisteAutori visteAutori =
				new VisteAutori
				(autori,getServletContext().getRealPath("")); 
		
		VisteLogin visteLogin =
				new VisteLogin
				(login, getServletContext().getRealPath("")); 
		
		
		
		
                ControllerDocumenti controllerDocumenti;
               
            
		ControllerAutori controllerAutori = 
				new ControllerAutori(request, response, visteAutori, autori, sessione, connessione,login);
		
		
		LoginController2 loginController2 = new LoginController2(request,response,autori,connessione,getServletContext().getRealPath(""),sessione, visteDocumenti);
		controllerDocumenti = new ControllerDocumenti(request,response,visteDocumenti,documenti,sessione,autori,connessione,login);
		
		String comando = request.getParameter("comando");
		if (comando==null)comando="";
		
		//Buona idea...
		Vista masterpage = new Vista(getServletContext().getRealPath("index.html"));
				
		Vista view = null;
                
               // boolean logged = loginController2.login(
						//request.getParameter("email").toString(),
						//request.getParameter("password").toString()
						//);
		
		//Login login = new Login(connessione,sessione);
		
            switch(comando){
		
						
				
			case "":
					view = new Vista(getServletContext().getRealPath("RES/credits.html"));
                                        
                                       
                                        response.getWriter().append(
			                    masterpage
                                                        .sostituisci("loggatocome", "") 
                                                        .sostituisci("contenuto", view.toString())
                                
                                                        .sostituisci("contenuto2", "").toString());      
                                        
                                        
                                        break;
			
			case "Ant doc":
				view = new Vista(getServletContext().getRealPath("RES/credits.html"));
                       
                                response.getWriter().append(
			                    masterpage
                                                        .sostituisci("loggatocome", "") 
                                                        .sostituisci("contenuto", view.toString())
                                
                                                        .sostituisci("contenuto2", "").toString());
                                 
                                
			break;
			
			
                        case "formlogin":
				
				view = new Vista(getServletContext().getRealPath("RES/formlogin.html"));				
				
                                        response.getWriter().append(masterpage
                                        .sostituisci("contenuto", view.toString())
                                        .sostituisci("contenuto2", "").toString());
				break;
				
			case "login":
				
				try
			    {
				
				    boolean logged = loginController2.login(
						request.getParameter("email").toString(),
						request.getParameter("password").toString()
						);
				
				
				
				    if(logged) {
				    response.getWriter().append(
						
						masterpage.sostituisci
						("loggatocome"," " + sessione.getAttribute("email").toString() +" "+ "l'id è" +" "+
						sessione.getAttribute("idautore").toString()) +" "+ "il livello è"+" "
						+ sessione.getAttribute("livello").toString().toString());
				        
				    } 
				}
				catch (Exception e)
				{
					
				}
				break;	
								
			case "documenti":
				controllerDocumenti.listadocumenti();
				break;

			case "formnuovodocumento":
				controllerDocumenti.formnuovodocumento();
				break;
				
			case "confermainserimento":
				controllerDocumenti.confermaInserimento();
				break;	
			
			case "cancelladocumento":
				try
				{
				controllerDocumenti.cancellaDocumento(connessione, request, response,login);
				}
				catch (Exception e)
				{
					 e.printStackTrace();
					 System.out.println(e.getMessage());	
				}
				break;
                            
                        case "vedidocumento":
                                try
				{
                                controllerDocumenti.vedidocumento(connessione,request,response);
                                }
                                
                            catch (Exception e)
				{
					 e.printStackTrace();
					 System.out.println(e.getMessage());	
				}
                                
                                break;
                            
                        case "cercadocumento":
                                try
				{
                                controllerDocumenti.cercadocumento(connessione,request,response);
                                }
                                
                            catch (Exception e)
				{
					 e.printStackTrace();
					 System.out.println(e.getMessage());	
				}
                                
                                break;
			//Ho raggruppato di seguito gli eventi dei documenti
	                  
			case "formnuovoautore":
				controllerAutori.formnuovoautore();
		
				break;
                            
			case "autore":
				controllerAutori.confermaInserimento();
				
				break;
				
			case "listaautori":
				controllerAutori.listaautori();
                                
				break;
				
			case "confermainserimentoautore":
				controllerAutori.confermaInserimento();
                                
				break;
				
			case "cancellautore":
				try
				{
					controllerAutori.cancellaAutore(connessione, request, response,login);
				}
				catch (Exception e)
				{
					 e.printStackTrace();
					   System.out.println(e.getMessage());	
				}
                                
				break;
                        
                            //Ho raggruppato di seguito gli eventi degli autori
                            
                            
                            
			case "info":
				response.getWriter().append(masterpage
                                        //.sostituisci("loggatocome", " "+ login.getEmail().toUpperCase() + " " + "l'id é" +" "+ login.getId() +" "+"il livello é"+ " " + login.getLivello())
                                        .sostituisci("contenuto", "Qui ci sarà:<br />la pagina di info").toString());
				break;
		}
	}

         
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
//		Connessione.
			Connection connessione = null;
			try {
				connessione = DriverManager.getConnection("jdbc:sqlite:/Users/sarahtarantino/Desktop/Documenti.db");
				//connessione = DriverManager.getConnection("jdbc:mysql:/localhost/docant?user=root");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		//Preparo la sessione
	    HttpSession sessione = request.getSession();
	    Login login = new Login(connessione,sessione);
	}
	
	
}
