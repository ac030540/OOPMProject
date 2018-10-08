package project;
import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		int ch1=-1,mainOption=-1;
		while ( mainOption<1) {
		System.out.println("Welcome to Student attendance marking system.");
		System.out.println("Press 0 to login as Student.");
		System.out.println("Press 1 to login as administrator.");
		Scanner sc = new Scanner(System.in);
		int user = sc.nextInt();
		if( user==0){
			while(ch1<0) {
			student student1 = new student();
			student1.scanPrn();
			
			}
		}
		if( user==1){

			int c=1;
			admin admin1 = new admin();
		    admin1.getIdAndPassword();
		    do {
			   System.out.println("Select an option to continue:");
			   System.out.println("1)Insert data of a student.");
			   System.out.println("2)Edit data of a student.");
			   System.out.println("3)View attendance of students.");
			   System.out.println("4)Reset the attendance column.");
			   int ch = sc.nextInt();
			   switch(ch) {
			   case 1: admin1.insert();
			   		   break;
			   case 2: admin1.editDetails();
			   		   break;
			   case 3: admin1.display();
			           break;
			   case 4: admin1.resetAttendance();
			   		   break;
			   default : System.out.println("Select a valid option.");
			   }
			   System.out.println("Do you want to continue? Press 1 to continue and 0 to exit");
			   c = sc.nextInt();
		    }while(c!=0);
		    
		   
	    }
		}
		




	}
}