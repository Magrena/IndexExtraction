package test;
import java.io.IOException;

public class test1 {

   public static void main(String args[]) throws Exception {
	   //String filePath = "C:\\Study is fun\\2018_Book_MathematicalLogic.pdf";
	   //String pdfText = FileReader.getFile(filePath, 187, 188);
	   String filePath = "test.pdf";
	   
	   String pdfText = FileReader.getFile(filePath, 581, 584);   
	   text_position.position(580, 584);
	   
//	   String pdfText = FileReader.getFile(filePath, 396, 407);   
//	   text_position.position(395, 407);

	   txtCreate.creatTxtFile("test");
	   txtCreate.writeTxtFile(pdfText);
	   xmlCreater.getXML("test.txt");
   }
}