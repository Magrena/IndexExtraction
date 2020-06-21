package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class FileReader {
	
	   public static String getFile(String path,int PageB,int PageE) throws IOException {
		   
	      File file = new File(path);  
	      PDDocument document = PDDocument.load(file);
	      PDFTextStripper tStripper = new PDFTextStripper();
	      tStripper.setStartPage(PageB);
	      tStripper.setEndPage(PageE);
	      String pdfText = tStripper.getText(document);
	      
	      /*System.out.println(pdfFileInText);  
	      String lines[] = pdfFileInText.split("\\r?\\n");  
	      for (String line : lines) {
	          System.out.println(line);  
	      } */
	      
	      document.close();
	      return pdfText;
	   }
	   
	   
	   
		public static ArrayList<String> openfile(String filenameTemp)throws IOException
		{	
			File file = new File(filenameTemp);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader in = new BufferedReader(isr); 
			
			ArrayList<String> str = new ArrayList<String>();	//for return
			String list;
			while ((list = in.readLine()) != null) 
			{
				str.add(list);
				
			}
			in.close();
			
			return str;
		}
}