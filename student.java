

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
class student {
    private String prn;
    private int exit;
   
	void scanPrn() {
		Scanner sc = new Scanner(System.in); 
		System.out.println("Welcome to the attendance marking system!");
		System.out.println("Scan your ID to mark your attendance");
	    prn = sc.next();
		System.out.println("Your PRN is : " + prn);
	
		     
		      try {
		   
		         Connection c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Test",
		            "ashok", "123");
		       

		         Statement stmt = c.createStatement();
		         String sql = "UPDATE studentdata SET attendance=1 WHERE prn=?;" ;
		         PreparedStatement preparedStmt = c.prepareStatement(sql);
		         preparedStmt.setString (1, prn);
		          exit  = preparedStmt.executeUpdate();
		       
		        
		         stmt.close();
		         c.close();
		      } catch ( Exception e ) {
		    	  System.out.println("An exception has occured.");
		      }
		      if( exit==1) {
		      System.out.println("Your attendance has been marked for today! ");
		      }
		      else {
		    	  System.out.println("Unable to update attendance. You data is not stored in the database. Please contact admin.");
		      }
		 
		   }
	
		      
		      

	

}
