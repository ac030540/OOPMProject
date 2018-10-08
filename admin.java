package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
class admin {
	private static final String String = null;
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
			if(id!=1234 && password!=1234){
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
	      Connection c = null;
	     
	      int detailsCorrect = 0;
	      Statement stmt = null;
	      try {
	    	 Scanner sc = new Scanner(System.in);
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Test","ashok","123");
	         c.setAutoCommit(false);
	         stmt = c.createStatement();
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
	      // create the mysql insert preparedstatement
	         PreparedStatement preparedStmt = c.prepareStatement(query);
			preparedStmt.setString (1, name);
	         preparedStmt.setString (2, prn);
	         preparedStmt.setString (3, year);
	         preparedStmt.setString(4, division);
	         preparedStmt.setString(5, branch);

	         // execute the preparedstatement
	         preparedStmt.execute();
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Data scuccessfully recorded!");
	   }
	      public void display() {
	      Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Test",
	            "ashok", "123");
	         c.setAutoCommit(false);

	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM studentdata;" );
	         while ( rs.next() ) {
	            String name = rs.getString("name");
	            String  prn = rs.getString("prn");
	            String year  = rs.getString("year");
	            String  division = rs.getString("division");
	            String branch = rs.getString("branch");
	            int attendance = rs.getInt("attendance");
	            System.out.println( "NAME = " + name );
	            System.out.println( "PRN = " + prn );
	            System.out.println( "YEAR = " + year );
	            System.out.println( "DIVISION = " + division );
	            System.out.println( "BRANCH = " + branch );
	            System.out.println("ATTENDANCE =" + attendance);
	         }
	         rs.close();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	   }
	      public void resetAttendance() {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Test",
		            "ashok", "123");
		         c.setAutoCommit(false);

		         stmt = c.createStatement();
		         String sql = "UPDATE studentdata SET attendance=0;" ;
		         stmt.executeUpdate(sql);
		         c.commit();
		         stmt.close();
		         c.close();
		      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("The attendance of all the students has been reset.");
		   }
	      public void editDetails() {
		      Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Test",
		            "ashok", "123");
		         c.setAutoCommit(false);
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
		         stmt = c.createStatement();
		         String query = "UPDATE studentdata SET name=?, prn=?, year=?, division=?, branch=? WHERE prn=?;" ;
		 
		      // create the mysql insert preparedstatement
		         PreparedStatement preparedStmt = c.prepareStatement(query);
		         preparedStmt.setString (1, nname);
		         preparedStmt.setString (2, nprn);
		         preparedStmt.setString   (3, nyear);
		         preparedStmt.setString(4, ndivision);
		         preparedStmt.setString(5, nbranch);
		   
		         preparedStmt.setString (6, pprn);
		      

		         // execute the preparedstatement
		         preparedStmt.execute();

		         c.commit();
		         stmt.close();
		         
		         c.close();
		      } catch (Exception e) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Data scuccessfully recorded!");
		   }

}