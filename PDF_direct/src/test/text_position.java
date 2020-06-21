package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

public class text_position extends PDFTextStripper {

public StringBuilder tWord = new StringBuilder();
public String seek;
public String[] seekA;

public String a ="Absolute,Additive";

public List wordList = new ArrayList();
public boolean is1stChar = true;
public boolean lineMatch;
public double lastYVal;

public static ArrayList tChar2 = new ArrayList();



public text_position()
        throws IOException {
    super.setSortByPosition(true);
}


//public static void main(String[] args)
//        throws Exception {
//	//position();
//	getXML("test");
//	
//}



public static void position(int page_begin, int page_end)
        throws Exception {
	
    PDDocument document = null;
    try {
    	BufferedWriter out1 = new BufferedWriter(new FileWriter("location.txt"));
    	
        File input = new File("test.pdf");
        document = PDDocument.load(input);

        text_position printer = new text_position();
        List allPages = document.getDocumentCatalog().getAllPages();

        for (int i = page_begin; i < page_end; i++) {
            PDPage page = (PDPage) allPages.get(i);
            PDStream contents = page.getContents();

            if (contents != null) {
            	printer.processStream(page, page.findResources(), page.getContents().getStream());
            	//System.out.println(tChar2);
            	//out1.write(tChar2);
            	
            }
        }
        
        for(int i=0;i<tChar2.size();i++)
        {
        	//System.out.println(tChar2.get(i));
        	String a = (String) tChar2.get(i);
        	out1.write(a);
        }
        out1.close();
        
    } finally {
        if (document != null) {
            //System.out.println(wordList);
            document.close();
            
        }
        
    }
}

@Override
protected void processTextPosition(TextPosition text) {
    String tChar = text.getXDirAdj() + ","
            + text.getYDirAdj() + "," + text.getFontSize() + ","
            + text.getXScale() + "," + text.getHeightDir() + ","
            + text.getWidthOfSpace() + ","
            + text.getWidthDirAdj() + "]" + text.getCharacter()+"\n";
    tChar2.add(tChar);
    //System.out.println(tChar);
}
    


public static String letter(int i)throws IOException
{
	
	ArrayList<String> str2 = new ArrayList<String>();	//for txt
	str2 = FileReader.openfile("location.txt");
	
	float old_x_axis = 0f;
	float old_y_axis = 0f;
	float size_index =0f;
	float size_capital =0f;
	float size_normal = 0f;
	
	
	
//	for(int j=0;j<10;j++)
//	{
//		String retval[]= str2.get(j).split(",");
//		float x_axis = Float.parseFloat(retval[0]);
//		float y_axis = Float.parseFloat(retval[1]);
//		float size = Float.parseFloat(retval[4]);
//		String content[]= str2.get(j).split("]");
//		String text = content[1];
//		if(size>size_index&&size_index==0)
//			size_index = size;
//		if(size<size_index&&size_capital==0&&j>4)
//			size_capital = size;
//		if(size<size_capital&&size_normal==0&&j>4)
//		{
//			size_normal = size;
//			old_x_axis = x_axis;
//		}
//	}
	
	
	String content[]= str2.get(i).split("]");
	String text = content[1];
	
	return text;
		
	
}
public static float y_axis(int i)throws IOException
{
	ArrayList<String> str2 = new ArrayList<String>();	//for txt
	str2 = FileReader.openfile("location.txt");

	String retval[]= str2.get(i).split(",");
	float y_axis = Float.parseFloat(retval[1]);
	
	return y_axis;

}

public static float x_axis(int i)throws IOException
{
	ArrayList<String> str2 = new ArrayList<String>();	//for txt
	str2 = FileReader.openfile("location.txt");

	String retval[]= str2.get(i).split(",");
	float x_axis = Float.parseFloat(retval[0]);
	
	return x_axis;

}


}
    

    
    
    
    
    
    
    
//    char c = tChar.charAt(0);
//    lineMatch = matchCharLine(text);
//    if ((!tChar.matches(REGEX)) && (!Character.isWhitespace(c))) {
//        if ((!is1stChar) && (lineMatch == true)) {
//            appendChar(tChar);
//        } else if (is1stChar == true) {
//            setWordCoord(text, tChar);
//        }
//    } else {
//        endWord();
//    }


/*
protected void appendChar(String tChar) {
    tWord.append(tChar);
    is1stChar = false;
}

protected void setWordCoord(TextPosition text, String tChar) {
    tWord.append("(").append(pageNo).append(")[").append(roundVal(Float.valueOf(text.getXDirAdj()))).append(" : ").append(roundVal(Float.valueOf(text.getYDirAdj()))).append("] ").append(tChar);
    is1stChar = false;
}

protected void endWord() {
    String newWord = tWord.toString().replaceAll("[^\\x00-\\x7F]", "");
    String sWord = newWord.substring(newWord.lastIndexOf(' ') + 1);
    if (!"".equals(sWord)) {
        if (Arrays.asList(seekA).contains(sWord)) {
            wordList.add(newWord);
        } else if ("SHOWMETHEMONEY".equals(seek)) {
            wordList.add(newWord);
        }
    }
    tWord.delete(0, tWord.length());
    is1stChar = true;
}

protected boolean matchCharLine(TextPosition text) {
    Double yVal = roundVal(Float.valueOf(text.getYDirAdj()));
    if (yVal.doubleValue() == lastYVal) {
        return true;
    }
    lastYVal = yVal.doubleValue();
    endWord();
    return false;
}

protected Double roundVal(Float yVal) {
    DecimalFormat rounded = new DecimalFormat("0.0'0'");
    Double yValDub = new Double(rounded.format(yVal));
    return yValDub;
}
*/
