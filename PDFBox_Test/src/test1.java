import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class test1 {

   public static void main(String args[]) throws IOException {
	   String filePath = "C:\\2018_Book_MathematicalLogic.pdf";
	   String pdfText = FileReader.getFile(filePath, 187, 188);
	   //String filePath = "C:\\2018_Book_PhysicsOfOscillationsAndWaves.pdf";
	   //String pdfText = FileReader.getFile(filePath, 581, 584);
	   //String filePath = "C:\\Computer-Networking-Principles-Bonaventure-1-30-31-OTC1.pdf";
	   //String pdfText = FileReader.getFile(filePath, 277, 282);
	   txtCreate.creatTxtFile("test");
	   txtCreate.writeTxtFile(pdfText);
   }
}