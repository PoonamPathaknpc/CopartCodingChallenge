//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.Scanner;

public class CopartFacility {
	//String zipcode1="";
	
	
	public String findDistance(String zipcode1, String zipcode2) {
		// TODO Auto-generated method stub

		// Finding distance between two zipcodes using the ZIPCODE API
		final String USER_AGENT = "Mozilla/5.0";
		String output="";
		String units="mile"; 
 	    
 	   	String ZipUrl="https://www.zipcodeapi.com/rest/wuBC8LO1XFIu4BX8FLXGYvTvF6XVr7cYBNAKzpLw4t195YhLKesIKldqsbmUiySU/distance.csv/"+zipcode1+"/"+zipcode2+"/"+units;
		URL obj;
		try {
			obj = new URL(ZipUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(); // creating HTTP connection
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + ZipUrl);
			System.out.println("Response Code : " + responseCode);
			
			InputStream is = con.getInputStream();
			
			int ch;
			StringBuffer sb = new StringBuffer();
			
			while ((ch = is.read()) != -1) {
		        sb.append((char) ch); // appending the output (i.e distance)from the API to the String buffer
		      }
			output=sb.toString(); // converting the string buffer to a String
			//System.out.println(output);
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//input.close();
		return output; //returning the distance to the main method of the COpartFacility class 
	}

}
