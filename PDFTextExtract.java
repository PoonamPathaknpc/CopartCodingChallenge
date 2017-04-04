/*
 * This program extracts text from the image files jpg and jpeg pdf using the aspose.OCR libraries. 
 * A temporary license needs to be added. Please read the read.md file for it. * 
 * 
 */

import com.aspose.ocr.IRecognizedText;
import com.aspose.ocr.ImageStream;
import com.aspose.imaging.License;
import com.aspose.ocr.OcrEngine;
import com.aspose.ocr.RecognitionBlock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class PDFTextExtract {

	public PDFTextExtract() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)
	{
		
		try{
			
		  OcrEngine ocrmod = new OcrEngine();		
          // Input the Image file
		  String path = new File(".").getCanonicalPath();
		  System.out.println(path + "\\" + args[0]);
		  com.aspose.imaging.License license = new com.aspose.imaging.License();
		  license.setLicense(new java.io.FileInputStream(path + "\\Aspose.Imaging.lic"));
		  // Set the licensing of the imaging functionality.. the license is temporary..
		  com.aspose.ocr.License license1 = new com.aspose.ocr.License();
		  license1.setLicense(new java.io.FileInputStream(path + "\\Aspose.ocr.lic"));
		  if (License.isLicensed()) {
			    System.out.println("License is Set!");
			}		  
		  
          ocrmod.setImage(ImageStream.fromFile(path + "\\" + args[0]));          
          if (ocrmod.process())
          {
		  IRecognizedText sample = ocrmod.getText();          
		  System.out.println("The text is: ");
		  System.out.println(sample);
          }
	      } catch (FileNotFoundException e) {			
		   e.printStackTrace();
	       } catch (IOException e) {			
		   e.printStackTrace();
	     }	
		
		
	}

}
