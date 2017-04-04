/*
 * The following code takes String as input from command line arguments.
 * converts each character in to integer as per ASCII value *  
 * code prints the integer value for the same
 * Note: Please give the string input as command line argument while executing the code.
 */

public class StringtoInt {

	public StringtoInt() {}
	
	public static void main(String[] args)
	{	
    
       String sample = args[0];       
       int value=0;
       
       //Check the eligibility of string contain only digits.
       if(sample.matches("\\d+"))
       {	   
       char[] chars = sample.toCharArray();
       for(int i=0;i<chars.length;i++)
          {    	   
    	   int n = (int)chars[i]-48; // converts the char in to integer value as per the ascii code
    	   value  = value*10 + n;  //builds the number from each character.
          
          }
       
       System.out.println("The integer value for given string " + sample +  " is : " + value);
       }
       
       // In case the string contains values other than digits. Ask the user to enter the correct string again..
       else
    	 System.out.println("The given string " + sample + " cannot be parsed to integer value. Please enter correct string.");
    	   
    	   
    	   
		
	}

}
