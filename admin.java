
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
class admin {
	
	private int id;
	private int password;
	private  String name, branch, year, division, prn;
	int valid;
	void getIdAndPassword() {
		System.out.println("Welcome to the admin panel!");
		while(valid!=1) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your ID and password: ");
			id = sc.nextInt();
		    password = sc.nextInt();
			if(id!=1234 || password!=1234){
			System.out.println("Wrong Credentials! Please try again.");
			valid = -1;
		    }
		    else {
			valid = 1;
			System.out.println("Successful Login!");
		   
		  }
		
	    }
		
	}
	public void insert() {
	    
	      int detailsCorrect = 0;
	      try {
	    	 Scanner sc = new Scanner(System.in);
	    	 
	         Connection c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Test","ashok","123");
	        
	       
	         while( detailsCorrect!=1) {
	         System.out.println("Enter the name of the student:");
	         name = sc.nextLine();
	         System.out.println("Enter the PRN of the student:");
	         prn = sc.nextLine();
	         System.out.println("Enter the year of the student:");
	         year = sc.nextLine();
	         System.out.println("Enter the division of the student:");
	         division = sc.nextLine();
	         System.out.println("Enter the branch of the student:");
	         branch = sc.nextLine();
	         
	         System.out.println("Are the above entered details correct?");
	         System.out.println("Press 1 for YES");
	         System.out.println("Press 0 for NO");
	         detailsCorrect = sc.nextInt();
	         sc.nextLine();
	         }
	         
	         String query = "INSERT INTO studentdata(name,prn,year,division,branch,attendance) "
	            + "VALUES (?,?,?,?,?,0 );";
	      // create the sql insert preparedstatement
	         PreparedStatement preparedStmt = c.prepareStatement(query);
			preparedStmt.setString (1, name);
	         preparedStmt.setString (2, prn);
	         preparedStmt.setString (3, year);
	         preparedStmt.setString(4, division);
	         preparedStmt.setString(5, branch);

	         // execute the preparedstatement
	         preparedStmt.execute();
	    
	        
	         c.close();
	      } catch (Exception e) {
	         System.out.println("An exception has occured.");
	      }
	      System.out.println("Data scuccessfully recorded!");
	   }
	      public void displayPresent() {
	   
	      try {
	       
	        Connection c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Test",
	            "ashok", "123");
	        

	         Statement stmt = c.createStatement();
	         String studentName = " Student Name";
	         String tprn = " PRN ";
	         String tyear= "  Year ";
	         String tdivision = " Div ";
	         String tbranch = " Branch ";

	         System.out.println( "******************* Present students *******************" );
	         System.out.println( "_______________________________________________________" );
	         System.out.format( "%21s|%10s|%4s|%4s|%6s|\n", studentName, tprn,tyear, tdivision, tbranch );
	         System.out.println( "---------------------+----------+-------+-----+--------|" );
	         System.out.print(" ");
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM studentdata WHERE attendance='1' ORDER BY prn ASC;" );
	         while ( rs.next() ) {
	            String name = rs.getString("name");
	            String  prn = rs.getString("prn");
	            String year  = rs.getString("year");
	            String  division = rs.getString("division");
	            String branch = rs.getString("branch");
	            System.out.format(  "%20s|%10s|%7s|%5s|%8s|\n " ,name  , prn , year ,division , branch );
	            
	         }
	        
	         System.out.println( "____________________|__________|_______|_____|________|" );
	    
	         rs.close();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	    	  System.out.println("An exception has occured.");
	      }
	     
	   }
	      public void resetAttendance() {
		   
		      try {
		   
		         Connection c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Test",
		            "ashok", "123");
		       

		         Statement stmt = c.createStatement();
		         String sql = "UPDATE studentdata SET attendance=0;" ;
		         stmt.execute(sql);
		    
		         stmt.close();
		         c.close();
		      } catch ( Exception e ) {
		    	  System.out.println("An exception has occured.");
		      }
		      System.out.println("The attendance of all the students has been reset.");
		   }
	      public void editDetails() {
		      
		      try {
		   
		         Connection c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Test",
		            "ashok", "123");
		     
		         Scanner sc = new Scanner(System.in);
		         System.out.println("Please enter the PRN of the student:");
		         String pprn = sc.nextLine();
		         System.out.println("Please enter the new details of the student:");
		         System.out.println("Enter the name of the student:");
		         String nname = sc.nextLine();
		         System.out.println("Enter the PRN of the student:");
		         String nprn = sc.nextLine();
		         System.out.println("Enter the year of the student:");
		         String nyear = sc.nextLine();
		         System.out.println("Enter the division of the student:");
		         String ndivision = sc.nextLine();
		         System.out.println("Enter the branch of the student:");
		         String nbranch = sc.nextLine();
		         Statement stmt = c.createStatement();
		         String query = "UPDATE studentdata SET name=?, prn=?, year=?, division=?, branch=? WHERE prn=?;" ;
		 
		      // create the sql insert preparedstatement
		         PreparedStatement preparedStmt = c.prepareStatement(query);
		         preparedStmt.setString (1, nname);
		         preparedStmt.setString (2, nprn);
		         preparedStmt.setString   (3, nyear);
		         preparedStmt.setString(4, ndivision);
		         preparedStmt.setString(5, nbranch);
		   
		         preparedStmt.setString (6, pprn);
		      

		         // execute the preparedstatement
		         preparedStmt.execute();
 
		         stmt.close();
		      
		         c.close();
		      } catch (Exception e) {
		    	  System.out.println("An exception has occured.");
		      }
		      System.out.println("Data scuccessfully recorded!");
		   }
	      public void displayAll() {
	   	   
		      try {
		       
		        Connection c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Test",
		            "ashok", "123");
		        

		         Statement stmt = c.createStatement();
		         String studentName = " Student Name";
		         String tprn = " PRN ";
		         String tyear= "  Year ";
		         String tdivision = " Div ";
		         String tbranch = " Branch ";
		         String tattendance = "Attendance";
		         System.out.println( "************************* Student Record **************************" );
		         System.out.println( "__________________________________________________________________" );
		         System.out.format( "%21s|%10s|%4s|%4s|%6s|%6s|\n", studentName, tprn,tyear, tdivision, tbranch, tattendance );
		         System.out.println( "---------------------+----------+-------+-----+--------|----------|" );
		         System.out.print(" ");
		         ResultSet rs = stmt.executeQuery( "SELECT * FROM studentdata ORDER BY prn ASC;" );
		         while ( rs.next() ) {
		            String name = rs.getString("name");
		            String  prn = rs.getString("prn");
		            String year  = rs.getString("year");
		            String  division = rs.getString("division");
		            String branch = rs.getString("branch");
		            int attendance = rs.getInt("attendance");
		            System.out.format(  "%20s|%10s|%7s|%5s|%8s|%10s|\n " ,name  , prn , year ,division , branch , attendance);
		            
		         }
		        
		         System.out.println( "____________________|__________|_______|_____|________|__________|" );
		         rs.close();
		         stmt.close();
		         c.close();
		      } catch ( Exception e ) {
		    	  System.out.println("An exception has occured.");
		      }
		     
		   }

}