package test;

import org.jpedal.examples.html.PDFtoHTML5Converter;
import org.jpedal.exception.PdfException;
import org.jpedal.render.output.IDRViewerOptions;
import org.jpedal.render.output.html.HTMLConversionOptions;

import java.io.File;

//import com.sun.java.util.jar.pack.Package.File;

public class test {

	public static void main(String args[])
	{
	HTMLConversionOptions conversionOptions = new HTMLConversionOptions();
	// Set conversion options here e.g. conversionOptions.setCompressImages(true);

	IDRViewerOptions viewerOptions = new IDRViewerOptions();
	// Set viewer options here e.g. viewerOptions.setViewerUI(IDRViewerOptions.ViewerUI.Clean);

	File pdfFile = new File("test.pdf");
	File outputDir = new File("test.html");

	PDFtoHTML5Converter converter = new PDFtoHTML5Converter(pdfFile, outputDir, conversionOptions, viewerOptions);
	
	try {
	    converter.convert();
	} catch (PdfException e) {
	    e.printStackTrace();
	}
}
}
