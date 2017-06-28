/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentazione;

import java.sql.Connection;

import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sarahtarantino
 */
public class LoginTest {
  
        Connection connessione1 = null;
        HttpSession sessione = null;
      
			
    public LoginTest() {
   
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLivello method, of class Login.
     */
    @Test
    public void testGetLivello() {
        Connection connessione1 = null;
        HttpSession sessione = null;
        
        System.out.println("getLivello");
        Login login = new Login(this.connessione1,this.sessione);
        String expResult = "0";
        String result = login.getLivello();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Login.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Login instance = null;
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Login.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Login instance = null;
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Login.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Login instance = null;
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEsito method, of class Login.
     */
    @Test
    public void testGetEsito() {
        System.out.println("getEsito");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.getEsito();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scriviUtenteLoggatoSuSession method, of class Login.
     */
    @Test
    public void testScriviUtenteLoggatoSuSession() {
        System.out.println("scriviUtenteLoggatoSuSession");
        String email = "";
        String password = "";
        Connection connessione = null;
        Login instance = null;
        instance.scriviUtenteLoggatoSuSession(email, password, connessione);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class Login.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "";
        String password = "";
        Connection connessione1 = null;
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.login(email, password, connessione1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
