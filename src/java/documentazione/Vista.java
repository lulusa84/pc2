package documentazione;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;




public final class Vista {
	
	private String contenuto = "";
	
        
	
	public Vista(String modello) 
	{
		this.contenuto = contenutoFile(modello);
               
	} 
	
	
	public Vista sostituisci(String chiave, String valore)
	{
		contenuto = contenuto.replace("["+chiave+"]", valore);
		return this;
	}
	
	
	public String toString(){return contenuto;}
	
	
	private String contenutoFile(String file)
	{
		String risultato = "";
		try{
			Scanner s = new Scanner(new FileInputStream(new File(file)));
			while(s.hasNext()) 
				risultato += s.nextLine();
			s.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		return risultato;
	}


    
}
