import java.util.Scanner;

public class LicenceKeyModifier {
    
	public static void main(String args[])
	{
		System.out.println("Please enter a licence Key string: ");
		Scanner input=new Scanner(System.in);
		String Str = input.next();
		String UpperCaseletter=Str.toUpperCase();
		System.out.println("Please enter an integer");
		Scanner input2=new Scanner(System.in);
		int k = Integer.parseInt(input.next());
		String FormattedLicence=KeyModifier(UpperCaseletter,k);
		System.out.println("Formatted Licence is: "+FormattedLicence);
		input.close();
		input2.close();
	}
	public static String KeyModifier(String licence, int Key) {
        char[] StringArray = licence.toCharArray();
        String FormattedLicence = "";
        StringBuffer Buffer = new StringBuffer(FormattedLicence);
        int length = 0;
        for (int i = StringArray.length-1; i >= 0; i--) {
            if (StringArray[i] - '-' == 0) 
            	continue;
            if (StringArray[i] - 'a' >= 0) 
            	StringArray[i] = (char)(StringArray[i] - 32);
            Buffer.append(StringArray[i]);
            length ++;

            if (length == Key) {
            	Buffer.append('-');
                length = 0;
            }
        }
        if (Buffer.length() > 0 && Buffer.charAt(Buffer.length()-1) - '-' == 0) 
        	Buffer.delete(Buffer.length()-1, Buffer.length());
        Buffer.reverse();
        FormattedLicence = Buffer.toString();
        return FormattedLicence;
    }
}