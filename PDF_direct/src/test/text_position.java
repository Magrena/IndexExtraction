package test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

public static void main(String[] args)
        throws Exception {
	
    PDDocument document = null;
    try {
    	BufferedWriter out1 = new BufferedWriter(new FileWriter("test.txt"));
    	
        File input = new File("test.pdf");
        document = PDDocument.load(input);

        text_position printer = new text_position();
        List allPages = document.getDocumentCatalog().getAllPages();

        for (int i = 0; i < allPages.size(); i++) {
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
        	System.out.println(tChar2.get(i));
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
    String tChar = "String[" + text.getXDirAdj() + ","
            + text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale="
            + text.getXScale() + " height=" + text.getHeightDir() + " space="
            + text.getWidthOfSpace() + " width="
            + text.getWidthDirAdj() + "]" + text.getCharacter()+"\n";
    tChar2.add(tChar);
    //System.out.println(tChar);

		
		
//    String REGEX = "[,.\\[\\](:;!?)/]";
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
}