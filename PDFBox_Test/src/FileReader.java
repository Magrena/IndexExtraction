import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


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
 
}