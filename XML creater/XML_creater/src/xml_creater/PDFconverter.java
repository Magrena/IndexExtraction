package xml_creater;

import org.jpedal.examples.text.ExtractTextInRectangle;

public class PDFconverter {

	public static void main(String args[])
	{
		
		ExtractTextInRectangle extract=new ExtractTextInRectangle("E2.pdf");
		 //extract.setPassword("password");
		 try {
			if (extract.openPDFFile()) {
			     int pageCount=extract.getPageCount();
			     for (int page=1; page<=pageCount; page++) {

			        String text=extract.getTextOnPage(page);
			        System.out.print(text);
			     }
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 extract.closePDFfile();
		
		
		 try {
			ExtractTextInRectangle.writeAllTextToDir("E2.pdf", "aa.txt", -1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
