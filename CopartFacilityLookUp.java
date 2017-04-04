import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CopartFacilityLookUp {

	//distance and the default distance are used for comparing the distances. Initial default distance was set to 0
	public static float distance;
   	public static float defaultDistance=0.0f;
   	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Taking Customer Id input from the customer
		Scanner input1=new Scanner(System.in);
 	   	System.out.println("Please enter the Customer ID:     ");
 	   	String User_ID = input1.next();
 	   	
 	   	//Taking ZipCode input from the customer
 	   	Scanner input2=new Scanner(System.in);
	   	System.out.println("Please enter your zipcode :     ");
	   	String Zip_Code = input1.next();
	   	
	   	//creating an instance of the User defined class Copart Facility
	   	CopartFacility c= new CopartFacility();
	   	
	   	//Initiating the SQL String for executing JDBC program
	   	String VehicleSql="select VEHICLE_TYPE from KARTHIK.customer where CUSTOMER_ID=?"; //selecting the vehicle type for the customer using customer ID 
	    String CopartZipSQL="select ZIP from KARTHIK.COPART";
	   	String CopartYardSQL="select yard from karthik.copart where zip=?";//Finding the yard with the help of the Zipcode obtained from the 'CopartZipSQL' 
	   	
	   	//Initializing ResultSet
	    ResultSet rs1;
	   	ResultSet rs2;
	   	ResultSet rs3;
	   	int count=0; // to change the initial default distance to the distance of the first zip code that was retrieved from the copart database
	   	String newZip=""; // final zip code which is shortest from the user entered zip code
	
	   	try {
	   		
	   		//establishing the connection with the oracle database
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","karthik");
		    
			//Initializing the prepared statements
			PreparedStatement ps1= con.prepareStatement(VehicleSql);
			ps1.setString(1, User_ID);
			rs1=ps1.executeQuery();
			
			PreparedStatement ps2= con.prepareStatement(CopartZipSQL);
			rs2=ps2.executeQuery();
			
			PreparedStatement ps3= con.prepareStatement(CopartYardSQL);
			
			
			System.out.println("SQL executed");
			rs1.next();
			System.out.println(rs1.getString(1));
			
			if(rs1.getString(1).equals("Automobile") && User_ID.equals("A001") ) // Rule1 - for customer with ID A001 and vehicle type is Automobile fetch the yards from all the states
			{
				while(rs2.next()) // loop runs for all the zipcodes of the copart database
				{
					System.out.println("abcabcabcs");
					newZip="";
					newZip+=rs2.getString(1);
					distance=Float.parseFloat(c.findDistance(Zip_Code,newZip));// fetching the distance between the zipcodes from CopartFacility class using the findDistance method and parsing it to float datatype
					if (count==	0)
					{
						defaultDistance=distance; // changing the initial default distance to the distance of the first zip code that was retrieved from the copart database
						count++;
					}
					if (defaultDistance<distance)
					{
						continue; // if default distance is less than distance of the new zipcode then no change in the distance
					}
					else
					{
						defaultDistance=distance; //else current distance will become the default distance
					}
				} //once the loop is terminated the zipcode that is nearest to the customer entered zip code will be remained in the variable 'newZip' 
				
				ps3.setString(1,newZip);
				rs3=ps2.executeQuery(); // fetching the yard details of the new zipcode obtained from the while loop above 
				rs3.next();
				System.out.println("The yard for the customer"+User_ID+" and the ZipCode" +newZip+" is :"+rs3.getString(1)); //yard details printed to the user
			}
	   	
	   	
	   	input1.close();
	   	input2.close();
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//response.sendRedirect("LoginFailed.jsp");
	} catch (Exception e){
		//response.sendRedirect("LoginFailed.jsp");
		e.printStackTrace();
	}

}
}
